package com.example.quizmaster;

public class QuizHistory {
    private String categoryName;
    private String difficulty;
    private int score;
    private int totalQuestions;
    private long timestamp;
    private int percentage;

    // ✅ REQUIRED: No-argument constructor for Firebase
    public QuizHistory() {
        // Firebase needs this empty constructor
    }

    // Regular constructor
    public QuizHistory(String categoryName, String difficulty, int score, int totalQuestions, long timestamp) {
        this.categoryName = categoryName;
        this.difficulty = difficulty;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.timestamp = timestamp;
        this.percentage = calculatePercentage();
    }

    private int calculatePercentage() {
        int maxScore = totalQuestions * getPointsForDifficulty(difficulty);
        return (maxScore > 0) ? (score * 100) / maxScore : 0;
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

    public String getFormattedDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM dd, yyyy hh:mm a", java.util.Locale.getDefault());
        return sdf.format(new java.util.Date(timestamp));
    }

    // Getters
    public String getCategoryName() { return categoryName; }
    public String getDifficulty() { return difficulty; }
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public long getTimestamp() { return timestamp; }
    public int getPercentage() { return percentage; }

    // ✅ REQUIRED: Setters for Firebase
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}