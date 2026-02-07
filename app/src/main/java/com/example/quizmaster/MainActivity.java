package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserManager userManager;
    private QuizManager quizManager;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private TextView tvUsername, tvLevel, tvPoints, tvStreak;
    private CardView cvDailyQuiz;
    private RecyclerView rvCategories;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = new UserManager(this);
        quizManager = new QuizManager(this);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Check if user is logged in (Firebase or local)
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null && !userManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        initializeViews();
        setupUI();

        // Load user data from Firebase if logged in
        if (firebaseUser != null) {
            loadUserDataFromFirebase();
        } else {
            // FIX: If no Firebase user, show local data immediately
            setupUserUI();
        }
    }

    private void loadUserDataFromFirebase() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference userRef = mDatabase.child("users").child(userId);

        // FIX: Show loading state or cached data first
        setupUserUI(); // Show cached data immediately while Firebase loads

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    // Update UI with fresh data from Firebase
                    tvUsername.setText(user.getUsername() != null ? user.getUsername() : "User");
                    tvLevel.setText("Level " + user.getLevel());
                    tvPoints.setText(user.getPoints() + " pts");
                    tvStreak.setText(user.getStreak() + " day streak 🔥");

                    // Save locally as cache
                    userManager.saveUser(user);
                } else {
                    // FIX: If no user data in Firebase, create a basic profile
                    createUserProfileInFirebase();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Fallback to local data (already displayed)
            }
        });
    }

    // NEW METHOD: Create user profile if it doesn't exist
    private void createUserProfileInFirebase() {
        String userId = mAuth.getCurrentUser().getUid();
        String email = mAuth.getCurrentUser().getEmail();
        String username = email != null ? email.split("@")[0] : "User";

        User newUser = new User(username, email);

        // Save to Firebase
        mDatabase.child("users").child(userId).setValue(newUser);

        // Save locally
        userManager.saveUser(newUser);

        // Update UI
        tvUsername.setText(username);
        tvLevel.setText("Level 1");
        tvPoints.setText("0 pts");
        tvStreak.setText("0 day streak 🔥");
    }

    private void initializeViews() {
        tvUsername = findViewById(R.id.tvUsername);
        tvLevel = findViewById(R.id.tvLevel);
        tvPoints = findViewById(R.id.tvPoints);
        tvStreak = findViewById(R.id.tvStreak);
        cvDailyQuiz = findViewById(R.id.cvDailyQuiz);
        rvCategories = findViewById(R.id.rvCategories);
        bottomNav = findViewById(R.id.bottomNav);
    }

    private void setupUI() {
        setupDailyQuiz();
        setupCategories();
        setupBottomNavigation();
    }

    private void setupUserUI() {
        User user = userManager.getCurrentUser();
        if (user != null) {
            // FIX: Handle null values gracefully
            tvUsername.setText(user.getUsername() != null ? user.getUsername() : "User");
            tvLevel.setText("Level " + user.getLevel());
            tvPoints.setText(user.getPoints() + " pts");
            tvStreak.setText(user.getStreak() + " day streak 🔥");
        } else {
            // FIX: Show default values if no user data exists
            tvUsername.setText("User");
            tvLevel.setText("Level 1");
            tvPoints.setText("0 pts");
            tvStreak.setText("0 day streak 🔥");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh user data when returning to main screen
        if (mAuth.getCurrentUser() != null) {
            loadUserDataFromFirebase();
        } else {
            setupUserUI();
        }
    }

    private void setupDailyQuiz() {
        cvDailyQuiz.setOnClickListener(v -> showDifficultyDialog("daily", "Daily Challenge"));
    }

    private void setupCategories() {
        List<Category> categories = new ArrayList<>();

        // Count questions for each category
        int generalCount = countQuestionsForCategory("general");
        int scienceCount = countQuestionsForCategory("science");
        int historyCount = countQuestionsForCategory("history");
        int geographyCount = countQuestionsForCategory("geography");
        int sportsCount = countQuestionsForCategory("sports");
        int techCount = countQuestionsForCategory("technology");

        // Add categories with actual quiz counts
        categories.add(new Category("General Knowledge", "general", R.drawable.ic_general, generalCount));
        categories.add(new Category("Science", "science", R.drawable.ic_science, scienceCount));
        categories.add(new Category("History", "history", R.drawable.ic_history, historyCount));
        categories.add(new Category("Geography", "geography", R.drawable.ic_geography, geographyCount));
        categories.add(new Category("Sports", "sports", R.drawable.ic_sports, sportsCount));
        categories.add(new Category("Technology", "technology", R.drawable.ic_tech, techCount));

        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            showDifficultyDialog(category.getId(), category.getName());
        });

        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        rvCategories.setAdapter(adapter);
    }

    // Helper method to count questions for a category
    private int countQuestionsForCategory(String categoryId) {
        List<Question> allQuestions = quizManager.getAllQuestions();
        int count = 0;

        for (Question q : allQuestions) {
            if (q.getCategory().equals(categoryId)) {
                count++;
            }
        }

        return count;
    }

    private void showDifficultyDialog(String categoryId, String categoryName) {
        String[] difficulties = {"Easy", "Medium", "Hard"};

        new AlertDialog.Builder(this)
                .setTitle("Select Difficulty")
                .setItems(difficulties, (dialog, which) -> {
                    String difficulty = difficulties[which].toLowerCase();
                    startQuiz(categoryId, categoryName, difficulty);
                })
                .show();
    }

    private void startQuiz(String categoryId, String categoryName, String difficulty) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("CATEGORY_ID", categoryId);
        intent.putExtra("CATEGORY_NAME", categoryName);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                return true;
            } else if (id == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            } else if (id == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }

            return false;
        });
    }
}