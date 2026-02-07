package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private UserManager userManager;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        userManager = new UserManager(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        User user = userManager.getCurrentUser();

        if (user != null) {
            TextView tvUsername = findViewById(R.id.tvProfileUsername);
            TextView tvEmail = findViewById(R.id.tvProfileEmail);
            TextView tvLevel = findViewById(R.id.tvProfileLevel);
            TextView tvPoints = findViewById(R.id.tvProfilePoints);
            TextView tvStreak = findViewById(R.id.tvProfileStreak);

            tvUsername.setText(user.getUsername());
            tvEmail.setText(user.getEmail());
            tvLevel.setText("Level " + user.getLevel());
            tvPoints.setText(user.getPoints() + " Points");
            tvStreak.setText(user.getStreak() + " Day Streak");
        }

        // Logout button
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> showLogoutDialog());

        // Reset Progress button
        Button btnResetProgress = findViewById(R.id.btnResetProgress);
        btnResetProgress.setOnClickListener(v -> showResetProgressDialog());
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Logout", (dialog, which) -> {
                    // FIXED: Sign out from Firebase
                    if (mAuth.getCurrentUser() != null) {
                        mAuth.signOut();
                    }

                    // FIXED: Clear all local data
                    userManager.logout();
                    userManager.clearQuizHistory();

                    // Go to registration screen
                    Intent intent = new Intent(ProfileActivity.this, RegisterActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showResetProgressDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Progress")
                .setMessage("Are you sure you want to reset all your progress? This will:\n\n• Reset level to 1\n• Reset points to 0\n• Reset streak to 0\n• Clear all quiz history\n\nThis action cannot be undone!")
                .setPositiveButton("Reset", (dialog, which) -> {
                    // Reset user progress locally
                    User user = userManager.getCurrentUser();
                    if (user != null) {
                        user.setLevel(1);
                        user.setPoints(0);
                        user.setStreak(0);
                        user.setBadges(new java.util.ArrayList<>());
                        userManager.saveUser(user);

                        // FIXED: Also reset in Firebase if logged in
                        if (mAuth.getCurrentUser() != null) {
                            String userId = mAuth.getCurrentUser().getUid();

                            // Reset user data in Firebase
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("level", 1);
                            updates.put("points", 0);
                            updates.put("streak", 0);
                            updates.put("badges", new java.util.ArrayList<>());

                            mDatabase.child("users").child(userId).updateChildren(updates);

                            // Clear quiz history in Firebase
                            mDatabase.child("quiz_history").child(userId).removeValue();
                        }
                    }

                    // Clear local quiz history
                    userManager.clearQuizHistory();

                    // Show confirmation
                    Toast.makeText(this, "Progress and history cleared!", Toast.LENGTH_SHORT).show();

                    // Refresh the screen
                    recreate();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}