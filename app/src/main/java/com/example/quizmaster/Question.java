package com.example.quizmaster;

import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int correctAnswer;
    private String category;
    private String difficulty; // NEW: easy, medium, hard

    // Constructor
    public Question(String question, List<String> options, int correctAnswer, String category, String difficulty) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Setters
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Helper method to check if an answer is correct
    public boolean isCorrect(int selectedAnswer) {
        return selectedAnswer == correctAnswer;
    }

    // Helper method to get the correct answer text
    public String getCorrectAnswerText() {
        if (correctAnswer >= 0 && correctAnswer < options.size()) {
            return options.get(correctAnswer);
        }
        return "";
    }
}