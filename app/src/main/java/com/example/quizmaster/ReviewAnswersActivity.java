package com.example.quizmaster;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ReviewAnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_answers);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Review Answers");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<QuizResult> allResults = getIntent().getParcelableArrayListExtra("QUIZ_RESULTS");

        // Filter only wrong answers
        List<QuizResult> wrongAnswers = new ArrayList<>();
        if (allResults != null) {
            for (QuizResult result : allResults) {
                if (!result.isCorrect()) {
                    wrongAnswers.add(result);
                }
            }
        }

        RecyclerView rvReview = findViewById(R.id.rvReview);
        rvReview.setLayoutManager(new LinearLayoutManager(this));
        ReviewAnswersAdapter adapter = new ReviewAnswersAdapter(wrongAnswers);
        rvReview.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}