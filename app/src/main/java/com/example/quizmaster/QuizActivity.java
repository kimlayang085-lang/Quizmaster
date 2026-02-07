package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity {

    private QuizManager quizManager;
    private UserManager userManager;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private List<Question> questions;
    private List<QuizResult> quizResults = new ArrayList<>();

    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer timer;

    private String difficulty, categoryName;

    private TextView tvQuestion, tvQuestionNumber, tvTimer, tvDifficulty;
    private ProgressBar progressBar;
    private LinearLayout llOptions;

    private boolean answerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizManager = new QuizManager(this);
        userManager = new UserManager(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        String categoryId = getIntent().getStringExtra("CATEGORY_ID");
        difficulty = getIntent().getStringExtra("DIFFICULTY");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoryName + " - " + capitalize(difficulty));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

        questions = quizManager.getQuestionsByCategory(categoryId, difficulty);

        if (questions.isEmpty()) {
            Toast.makeText(this, "No questions available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        tvDifficulty.setText(capitalize(difficulty));
        setDifficultyColor();

        displayQuestion();
    }

    private void initViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvTimer = findViewById(R.id.tvTimer);
        tvDifficulty = findViewById(R.id.tvDifficulty);
        progressBar = findViewById(R.id.progressBar);
        llOptions = findViewById(R.id.llOptions);
    }

    private void setDifficultyColor() {
        int color;
        switch (difficulty.toLowerCase()) {
            case "easy":
                color = ContextCompat.getColor(this, R.color.green);
                break;
            case "medium":
                color = ContextCompat.getColor(this, R.color.orange);
                break;
            default:
                color = ContextCompat.getColor(this, R.color.red);
        }
        tvDifficulty.setTextColor(color);
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            showResults();
            return;
        }

        answerSelected = false;
        llOptions.removeAllViews();

        Question question = questions.get(currentQuestionIndex);

        tvQuestionNumber.setText("Question " + (currentQuestionIndex + 1) + "/" + questions.size());
        tvQuestion.setText(question.getQuestion());
        progressBar.setProgress(((currentQuestionIndex + 1) * 100) / questions.size());

        for (int i = 0; i < question.getOptions().size(); i++) {
            addOption(question, i);
        }

        startTimer();
    }

    private void addOption(Question question, int index) {
        CardView card = new CardView(this);
        card.setRadius(12);
        card.setCardElevation(6);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 24);
        card.setLayoutParams(params);

        TextView tv = new TextView(this);
        tv.setText(question.getOptions().get(index));
        tv.setTextSize(18);
        tv.setPadding(32, 48, 32, 48);
        tv.setGravity(Gravity.CENTER);

        card.addView(tv);

        card.setOnClickListener(v -> {
            if (answerSelected) return;

            answerSelected = true;
            stopTimer();

            boolean correct = index == question.getCorrectAnswer();
            checkAnswer(question, index, correct);
        });

        llOptions.addView(card);
    }

    private void startTimer() {
        timer = new CountDownTimer(15000, 1000) {
            public void onTick(long ms) {
                int sec = (int) (ms / 1000);
                tvTimer.setText(sec + " s");
            }

            public void onFinish() {
                Toast.makeText(QuizActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                currentQuestionIndex++;
                displayQuestion();
            }
        }.start();
    }

    private void stopTimer() {
        if (timer != null) timer.cancel();
    }

    private void checkAnswer(Question q, int selected, boolean correct) {
        String userAns = q.getOptions().get(selected);
        String correctAns = q.getOptions().get(q.getCorrectAnswer());

        quizResults.add(new QuizResult(q.getQuestion(), userAns, correctAns, correct));

        if (correct) {
            int points = getPoints();
            score += points;
            Toast.makeText(this, "Correct! +" + points, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        llOptions.postDelayed(() -> {
            currentQuestionIndex++;
            displayQuestion();
        }, 500);
    }

    private int getPoints() {
        switch (difficulty.toLowerCase()) {
            case "easy": return 5;
            case "medium": return 10;
            default: return 15;
        }
    }

    private void showResults() {
        stopTimer();
        saveProgress();

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL_QUESTIONS", questions.size());
        intent.putExtra("DIFFICULTY", difficulty);
        intent.putExtra("CATEGORY_NAME", categoryName);
        intent.putParcelableArrayListExtra("QUIZ_RESULTS", new ArrayList<>(quizResults));
        startActivity(intent);
        finish();
    }

    private void saveProgress() {
        // Create quiz history object
        QuizHistory history = new QuizHistory(
                categoryName, difficulty, score,
                questions.size(), System.currentTimeMillis()
        );

        // FIXED: Save history to local storage first (immediately available)
        userManager.saveQuizHistory(history);

        // Update user points locally
        User user = userManager.getCurrentUser();
        if (user != null) {
            user.addPoints(score);
            userManager.saveUser(user);
        }

        // FIXED: Only save to Firebase if user is authenticated
        if (mAuth.getCurrentUser() != null && user != null) {
            String userId = mAuth.getCurrentUser().getUid();

            // Save user points and level to Firebase
            Map<String, Object> updates = new HashMap<>();
            updates.put("points", user.getPoints());
            updates.put("level", user.getLevel());

            mDatabase.child("users").child(userId).updateChildren(updates);

            // Save quiz history to Firebase
            mDatabase.child("quiz_history").child(userId)
                    .push().setValue(history);
        }
    }

    private String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}