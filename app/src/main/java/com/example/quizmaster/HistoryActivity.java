package com.example.quizmaster;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private UserManager userManager;
    private RecyclerView rvHistory;
    private TextView tvEmptyHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Quiz History");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        userManager = new UserManager(this);

        rvHistory = findViewById(R.id.rvHistory);
        tvEmptyHistory = findViewById(R.id.tvEmptyHistory);

        loadHistory();
    }

    private void loadHistory() {
        List<QuizHistory> historyList = userManager.getQuizHistory();

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