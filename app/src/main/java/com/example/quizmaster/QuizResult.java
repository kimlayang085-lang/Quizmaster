package com.example.quizmaster;

import android.os.Parcel;
import android.os.Parcelable;

public class QuizResult implements Parcelable {
    private String question;
    private String userAnswer;
    private String correctAnswer;
    private boolean isCorrect;

    public QuizResult(String question, String userAnswer, String correctAnswer, boolean isCorrect) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.isCorrect = isCorrect;
    }

    protected QuizResult(Parcel in) {
        question = in.readString();
        userAnswer = in.readString();
        correctAnswer = in.readString();
        isCorrect = in.readByte() != 0;
    }

    public static final Creator<QuizResult> CREATOR = new Creator<QuizResult>() {
        @Override
        public QuizResult createFromParcel(Parcel in) {
            return new QuizResult(in);
        }

        @Override
        public QuizResult[] newArray(int size) {
            return new QuizResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(userAnswer);
        dest.writeString(correctAnswer);
        dest.writeByte((byte) (isCorrect ? 1 : 0));
    }

    // Getters
    public String getQuestion() { return question; }
    public String getUserAnswer() { return userAnswer; }
    public String getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect() { return isCorrect; }
}