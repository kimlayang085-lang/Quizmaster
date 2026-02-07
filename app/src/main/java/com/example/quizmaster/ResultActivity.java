package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        String difficulty = getIntent().getStringExtra("DIFFICULTY");
        ArrayList<QuizResult> quizResults = getIntent().getParcelableArrayListExtra("QUIZ_RESULTS");

        int maxScore = totalQuestions * getPointsForDifficulty(difficulty);
        int percentage = (maxScore > 0) ? (score * 100) / maxScore : 0;

        // Count wrong answers
        int wrongCount = 0;
        if (quizResults != null) {
            for (QuizResult result : quizResults) {
                if (!result.isCorrect()) {
                    wrongCount++;
                }
            }
        }

        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvPercentage = findViewById(R.id.tvPercentage);
        TextView tvMessage = findViewById(R.id.tvMessage);
        TextView tvDifficultyResult = findViewById(R.id.tvDifficultyResult);
        TextView tvWrongCount = findViewById(R.id.tvWrongCount);
        Button btnReviewAnswers = findViewById(R.id.btnReviewAnswers);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);

        tvScore.setText(score + " / " + maxScore);
        tvPercentage.setText(percentage + "%");

        if (difficulty != null) {
            tvDifficultyResult.setText("Difficulty: " + capitalize(difficulty));
        }

        // Show wrong count
        if (wrongCount > 0) {
            tvWrongCount.setText("You got " + wrongCount + " question" + (wrongCount > 1 ? "s" : "") + " wrong");
            tvWrongCount.setVisibility(View.VISIBLE);
            btnReviewAnswers.setVisibility(View.VISIBLE);
        } else {
            tvWrongCount.setVisibility(View.GONE);
            btnReviewAnswers.setVisibility(View.GONE);
        }

        // Set message based on performance
        if (percentage >= 80) {
            tvMessage.setText("🎉 Excellent! You're a quiz master!");
        } else if (percentage >= 60) {
            tvMessage.setText("👏 Great job! Keep it up!");
        } else if (percentage >= 40) {
            tvMessage.setText("👍 Good effort! Practice makes perfect!");
        } else {
            tvMessage.setText("💪 Don't give up! Try again!");
        }

        // Review Answers Button
        btnReviewAnswers.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ReviewAnswersActivity.class);
            intent.putParcelableArrayListExtra("QUIZ_RESULTS", quizResults);
            startActivity(intent);
        });

        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnPlayAgain.setOnClickListener(v -> {
            finish();
        });
    }

    private int getPointsForDifficulty(String diff) {
        if (diff == null) return 10;
        switch (diff.toLowerCase()) {
            case "easy": return 5;
            case "medium": return 10;
            case "hard": return 15;
            default: return 10;
        }
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}