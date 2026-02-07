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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserManager userManager;
    private QuizManager quizManager;

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

        // Check if user is logged in
        if (!userManager.isLoggedIn()) {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
            return;
        }

        initializeViews();
        setupUI();
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
        User user = userManager.getCurrentUser();

        // Profile section
        if (user != null) {
            tvUsername.setText(user.getUsername());
            tvLevel.setText("Level " + user.getLevel());
            tvPoints.setText(user.getPoints() + " pts");
            tvStreak.setText(user.getStreak() + " day streak 🔥");
        }

        // Daily Quiz
        cvDailyQuiz.setOnClickListener(v ->
                showDifficultyDialog("Daily Challenge", "general")
        );

        // Setup Categories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("General Knowledge", "general", R.drawable.ic_general, 25));
        categories.add(new Category("Science", "science", R.drawable.ic_science, 20));
        categories.add(new Category("History", "history", R.drawable.ic_history, 18));
        categories.add(new Category("Technology", "technology", R.drawable.ic_tech, 15));
        categories.add(new Category("Sports", "sports", R.drawable.ic_sports, 22));
        categories.add(new Category("Geography", "geography", R.drawable.ic_geography, 17));

        rvCategories.setLayoutManager(new GridLayoutManager(this, 2));
        CategoryAdapter adapter = new CategoryAdapter(categories, category ->
                showDifficultyDialog(category.getName(), category.getId())
        );
        rvCategories.setAdapter(adapter);

        // Bottom Navigation
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_history) {
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void showDifficultyDialog(String categoryName, String categoryId) {
        String[] difficulties = {"Easy", "Medium", "Hard"};

        new AlertDialog.Builder(this)
                .setTitle("Select Difficulty")
                .setItems(difficulties, (dialog, which) -> {
                    String difficulty = difficulties[which].toLowerCase();
                    startQuiz(categoryName, categoryId, difficulty);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void startQuiz(String categoryName, String categoryId, String difficulty) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("CATEGORY_NAME", categoryName);
        intent.putExtra("CATEGORY_ID", categoryId);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh user data when returning to main screen
        User user = userManager.getCurrentUser();
        if (user != null) {
            tvUsername.setText(user.getUsername());
            tvLevel.setText("Level " + user.getLevel());
            tvPoints.setText(user.getPoints() + " pts");
            tvStreak.setText(user.getStreak() + " day streak 🔥");
        }
    }
}