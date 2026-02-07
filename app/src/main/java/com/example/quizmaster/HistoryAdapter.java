package com.example.quizmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<QuizHistory> historyList;

    public HistoryAdapter(List<QuizHistory> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        QuizHistory history = historyList.get(position);

        holder.tvCategoryName.setText(history.getCategoryName());
        holder.tvDifficulty.setText(capitalize(history.getDifficulty()));
        holder.tvScore.setText(history.getScore() + " pts");
        holder.tvPercentage.setText(history.getPercentage() + "%");
        holder.tvDate.setText(history.getFormattedDate());

        // Set difficulty color
        int color;
        switch (history.getDifficulty().toLowerCase()) {
            case "easy":
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.green);
                break;
            case "medium":
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.orange);
                break;
            case "hard":
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.red);
                break;
            default:
                color = ContextCompat.getColor(holder.itemView.getContext(), R.color.textSecondary);
        }
        holder.tvDifficulty.setTextColor(color);

        // Set performance indicator color
        if (history.getPercentage() >= 80) {
            holder.tvPercentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
        } else if (history.getPercentage() >= 60) {
            holder.tvPercentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.orange));
        } else {
            holder.tvPercentage.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvCategoryName;
        TextView tvDifficulty;
        TextView tvScore;
        TextView tvPercentage;
        TextView tvDate;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cvHistoryItem);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvDifficulty = itemView.findViewById(R.id.tvDifficulty);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvPercentage = itemView.findViewById(R.id.tvPercentage);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}