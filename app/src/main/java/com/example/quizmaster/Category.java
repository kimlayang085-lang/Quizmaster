package com.example.quizmaster;

public class Category {
    private String name;
    private String id;
    private int icon;
    private int quizCount;

    // Constructor - MUST accept 4 parameters
    public Category(String name, String id, int icon, int quizCount) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.quizCount = quizCount;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public int getQuizCount() {
        return quizCount;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setQuizCount(int quizCount) {
        this.quizCount = quizCount;
    }
}