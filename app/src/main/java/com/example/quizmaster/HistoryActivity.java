package com.example.quizmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private UserManager userManager;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private RecyclerView rvHistory;
    private TextView tvEmptyHistory;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Quiz History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        userManager = new UserManager(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rvHistory = findViewById(R.id.rvHistory);
        tvEmptyHistory = findViewById(R.id.tvEmptyHistory);
        // Note: You'll need to add a ProgressBar to activity_history.xml
        progressBar = findViewById(R.id.progressBar);

        loadHistory();
    }

    private void loadHistory() {
        // FIXED: Check if user is logged in with Firebase
        if (mAuth.getCurrentUser() != null) {
            // Show loading indicator
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
            }
            rvHistory.setVisibility(View.GONE);
            tvEmptyHistory.setVisibility(View.GONE);

            // Load from Firebase
            loadHistoryFromFirebase();
        } else {
            // Not logged in with Firebase, use local data only
            displayHistory(userManager.getQuizHistory());
        }
    }

    private void loadHistoryFromFirebase() {
        String userId = mAuth.getCurrentUser().getUid();
        DatabaseReference historyRef = mDatabase.child("quiz_history").child(userId);

        historyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<QuizHistory> historyList = new ArrayList<>();

                // Parse all history items from Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuizHistory history = snapshot.getValue(QuizHistory.class);
                    if (history != null) {
                        historyList.add(history);
                    }
                }

                // Sort by timestamp (newest first)
                Collections.sort(historyList, new Comparator<QuizHistory>() {
                    @Override
                    public int compare(QuizHistory h1, QuizHistory h2) {
                        return Long.compare(h2.getTimestamp(), h1.getTimestamp());
                    }
                });

                // FIXED: Save to local storage so it's cached
                // Clear old local history first
                userManager.clearQuizHistory();
                // Save each item to local storage
                for (QuizHistory history : historyList) {
                    userManager.saveQuizHistory(history);
                }

                // Hide loading and display
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                displayHistory(historyList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Hide loading
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }

                // Fallback to local data if Firebase fails
                List<QuizHistory> localHistory = userManager.getQuizHistory();
                displayHistory(localHistory);
            }
        });
    }

    private void displayHistory(List<QuizHistory> historyList) {
        if (historyList == null || historyList.isEmpty()) {
            rvHistory.setVisibility(View.GONE);
            tvEmptyHistory.setVisibility(View.VISIBLE);
        } else {
            rvHistory.setVisibility(View.VISIBLE);
            tvEmptyHistory.setVisibility(View.GONE);

            rvHistory.setLayoutManager(new LinearLayoutManager(this));
            HistoryAdapter adapter = new HistoryAdapter(historyList);
            rvHistory.setAdapter(adapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}