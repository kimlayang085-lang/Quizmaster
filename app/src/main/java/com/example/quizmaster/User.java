package com.example.quizmaster;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private int level;
    private int points;
    private int streak;
    private List<String> badges;

    // Default constructor required for Firebase
    public User() {
    }

    // Constructor
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.level = 1;
        this.points = 0;
        this.streak = 0;
        this.badges = new ArrayList<>();
    }

    // Constructor with points and level
    public User(String username, String email, int points, int level) {
        this.username = username;
        this.email = email;
        this.points = points;
        this.level = level;
        this.streak = 0;
        this.badges = new ArrayList<>();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }

    public int getPoints() {
        return points;
    }

    public int getStreak() {
        return streak;
    }

    public List<String> getBadges() {
        return badges;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    // Helper method to add a badge
    public void addBadge(String badge) {
        if (badges == null) {
            badges = new ArrayList<>();
        }
        if (!badges.contains(badge)) {
            badges.add(badge);
        }
    }

    // Helper method to add points
    public void addPoints(int pointsToAdd) {
        this.points += pointsToAdd;
        // Update level based on points (every 100 points = 1 level)
        this.level = (this.points / 100) + 1;
    }

    // Helper method to increment streak
    public void incrementStreak() {
        this.streak++;
    }

    // Helper method to reset streak
    public void resetStreak() {
        this.streak = 0;
    }
}