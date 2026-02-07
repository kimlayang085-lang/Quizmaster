package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private QuizManager quizManager;
    private UserManager userManager;
    private List<Question> questions;
    private List<QuizResult> quizResults; // Store all answers
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer timer;
    private String difficulty;
    private String categoryName;

    private TextView tvQuestion, tvQuestionNumber, tvTimer, tvDifficulty;
    private ProgressBar progressBar;
    private LinearLayout llOptions;
    private boolean answerSelected = false;
    private int selectedOptionIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizManager = new QuizManager(this);
        userManager = new UserManager(this);
        quizResults = new ArrayList<>();

        categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        String categoryId = getIntent().getStringExtra("CATEGORY_ID");
        difficulty = getIntent().getStringExtra("DIFFICULTY");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoryName + " - " + capitalize(difficulty));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initializeViews();

        questions = quizManager.getQuestionsByCategory(categoryId, difficulty);

        if (questions.isEmpty()) {
            Toast.makeText(this, "No questions available for this difficulty", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvDifficulty.setText(capitalize(difficulty));
        setDifficultyColor(difficulty);

        displayQuestion();
    }

    private void initializeViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTimer = findViewById(R.id.tvTimer);
        tvDifficulty = findViewById(R.id.tvDifficulty);
        progressBar = findViewById(R.id.progressBar);
        llOptions = findViewById(R.id.llOptions);
    }

    private void setDifficultyColor(String diff) {
        int color;
        switch (diff.toLowerCase()) {
            case "easy":
                color = ContextCompat.getColor(this, R.color.green);
                break;
            case "medium":
                color = ContextCompat.getColor(this, R.color.orange);
                break;
            case "hard":
                color = ContextCompat.getColor(this, R.color.red);
                break;
            default:
                color = ContextCompat.getColor(this, R.color.textSecondary);
        }
        tvDifficulty.setTextColor(color);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            showResults();
            return;
        }

        answerSelected = false; // Reset flag for new question
        selectedOptionIndex = -1; // Reset selected option

        Question question = questions.get(currentQuestionIndex);

        tvQuestionNumber.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
        tvQuestion.setText(question.getQuestion());
        progressBar.setProgress(((currentQuestionIndex + 1) * 100) / questions.size());

        llOptions.removeAllViews();
        List<String> options = question.getOptions();

        for (int i = 0; i < options.size(); i++) {
            final int optionIndex = i;

            // Create a CardView for each option
            androidx.cardview.widget.CardView cardView = new androidx.cardview.widget.CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(0, 0, 0, 24); // Bottom margin between boxes
            cardView.setLayoutParams(cardParams);
            cardView.setCardElevation(4);
            cardView.setRadius(12);
            cardView.setCardBackgroundColor(0xFFFFFFFF);

            // Create TextView for option text
            TextView optionText = new TextView(this);
            optionText.setText(options.get(i));
            optionText.setTextSize(18);
            optionText.setTextColor(0xFF212121);
            optionText.setPadding(32, 48, 32, 48); // Big padding for large boxes
            optionText.setGravity(android.view.Gravity.CENTER);

            cardView.addView(optionText);

            // Set click listener
            cardView.setOnClickListener(v -> {
                if (!answerSelected) {
                    answerSelected = true;
                    selectedOptionIndex = optionIndex;

                    // Highlight selected option
                    cardView.setCardBackgroundColor(0xFFFF6B35);
                    optionText.setTextColor(0xFFFFFFFF);

                    // Submit answer after a short delay
                    llOptions.postDelayed(() -> checkAnswer(), 100);
                }
            });

            llOptions.addView(cardView);
        }

        startTimer();
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int timeLeft = (int) (millisUntilFinished / 1000);
                tvTimer.setText(timeLeft + " s");

                if (timeLeft <= 5) {
                    tvTimer.setTextColor(ContextCompat.getColor(QuizActivity.this, R.color.red));
                } else {
                    tvTimer.setTextColor(ContextCompat.getColor(QuizActivity.this, R.color.green));
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();

                // Save as incorrect with no answer
                Question question = questions.get(currentQuestionIndex);
                QuizResult result = new QuizResult(
                        question.getQuestion(),
                        "No answer",
                        question.getOptions().get(question.getCorrectAnswer()),
                        false
                );
                quizResults.add(result);

                currentQuestionIndex++;
                displayQuestion();
            }
        }.start();
    }

    private void checkAnswer() {
        if (timer != null) {
            timer.cancel();
        }

        if (selectedOptionIndex == -1) {
            // This shouldn't happen with automatic selection, but handle it gracefully
            return;
        }

        Question question = questions.get(currentQuestionIndex);
        String userAnswer = question.getOptions().get(selectedOptionIndex);
        String correctAnswer = question.getOptions().get(question.getCorrectAnswer());
        boolean isCorrect = selectedOptionIndex == question.getCorrectAnswer();

        // Save result
        QuizResult result = new QuizResult(
                question.getQuestion(),
                userAnswer,
                correctAnswer,
                isCorrect
        );
        quizResults.add(result);

        if (isCorrect) {
            int points = getPointsForDifficulty(difficulty);
            score += points;
            Toast.makeText(this, "✓ Correct! +" + points + " points", Toast.LENGTH_SHORT).show();
        } else {
            // DON'T show correct answer - just say wrong
            Toast.makeText(this, "✗ Wrong answer", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;

        // Disable further selections while transitioning
        llOptions.setEnabled(false);
        for (int i = 0; i < llOptions.getChildCount(); i++) {
            llOptions.getChildAt(i).setEnabled(false);
        }

        llOptions.postDelayed(() -> {
            llOptions.setEnabled(true);
            displayQuestion();
        }, 400);
    }

    private int getPointsForDifficulty(String diff) {
        switch (diff.toLowerCase()) {
            case "easy": return 5;
            case "medium": return 10;
            case "hard": return 15;
            default: return 10;
        }
    }

    private void showResults() {
        if (timer != null) {
            timer.cancel();
        }

        User user = userManager.getCurrentUser();
        if (user != null) {
            user.setPoints(user.getPoints() + score);
            user.setLevel((user.getPoints() / 100) + 1);
            userManager.saveUser(user);
        }

        QuizHistory quizHistory = new QuizHistory(
                categoryName != null ? categoryName : "Quiz",
                difficulty,
                score,
                questions.size(),
                System.currentTimeMillis()
        );
        userManager.saveQuizHistory(quizHistory);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putParcelableArrayListExtra("QUIZ_RESULTS", new ArrayList<>(quizResults));
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}