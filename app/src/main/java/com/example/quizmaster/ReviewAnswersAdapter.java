package com.example.quizmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReviewAnswersAdapter extends RecyclerView.Adapter<ReviewAnswersAdapter.ViewHolder> {

    private List<QuizResult> wrongAnswers;

    public ReviewAnswersAdapter(List<QuizResult> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizResult result = wrongAnswers.get(position);

        holder.tvQuestionNum.setText("Question " + (position + 1));
        holder.tvQuestion.setText(result.getQuestion());
        holder.tvYourAnswer.setText("Your Answer: " + result.getUserAnswer());
        holder.tvCorrectAnswer.setText("Correct Answer: " + result.getCorrectAnswer());
    }

    @Override
    public int getItemCount() {
        return wrongAnswers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestionNum, tvQuestion, tvYourAnswer, tvCorrectAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionNum = itemView.findViewById(R.id.tvQuestionNum);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvYourAnswer = itemView.findViewById(R.id.tvYourAnswer);
            tvCorrectAnswer = itemView.findViewById(R.id.tvCorrectAnswer);
        }
    }
}