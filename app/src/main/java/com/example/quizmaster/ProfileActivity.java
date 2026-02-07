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

public class ProfileActivity extends AppCompatActivity {
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        userManager = new UserManager(this);
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
                    // Logout user
                    userManager.logout();

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
                    // Reset user progress
                    User user = userManager.getCurrentUser();
                    if (user != null) {
                        user.setLevel(1);
                        user.setPoints(0);
                        user.setStreak(0);
                        user.setBadges(new java.util.ArrayList<>());
                        userManager.saveUser(user);
                    }

                    // Clear quiz history
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