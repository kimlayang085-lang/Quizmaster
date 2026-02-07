package com.example.quizmaster;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String PREFS_NAME = "QuizMasterPrefs";
    private static final String KEY_CURRENT_USER = "current_user";
    private static final String KEY_QUIZ_HISTORY = "quiz_history";

    private SharedPreferences prefs;
    private Gson gson;

    public UserManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void saveUser(User user) {
        String json = gson.toJson(user);
        prefs.edit().putString(KEY_CURRENT_USER, json).apply();
    }

    public User getCurrentUser() {
        String json = prefs.getString(KEY_CURRENT_USER, null);
        if (json != null) {
            return gson.fromJson(json, User.class);
        }
        return null;
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public void logout() {
        prefs.edit().remove(KEY_CURRENT_USER).apply();
    }

    // Quiz History Methods
    public void saveQuizHistory(QuizHistory quizHistory) {
        List<QuizHistory> historyList = getQuizHistory();
        historyList.add(0, quizHistory); // Add to beginning of list

        // Keep only last 50 quizzes
        if (historyList.size() > 50) {
            historyList = historyList.subList(0, 50);
        }

        String json = gson.toJson(historyList);
        prefs.edit().putString(KEY_QUIZ_HISTORY, json).apply();
    }

    public List<QuizHistory> getQuizHistory() {
        String json = prefs.getString(KEY_QUIZ_HISTORY, null);
        if (json != null) {
            Type type = new TypeToken<List<QuizHistory>>(){}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public void clearQuizHistory() {
        prefs.edit().remove(KEY_QUIZ_HISTORY).apply();
    }
}