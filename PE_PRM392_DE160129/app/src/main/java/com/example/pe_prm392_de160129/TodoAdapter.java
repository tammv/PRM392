package com.example.pe_prm392_de160129;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<Todo> todos;

    public TodoAdapter(Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use parent.getContext() instead of context if context is causing issues
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.tvUserId.setText(String.valueOf(todo.getUserId()));
        holder.tvId.setText(String.valueOf(todo.getId()));
        holder.tvTitle.setText(todo.getTitle());
        holder.rbCompleted.setChecked(todo.isCompleted());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId, tvId, tvTitle;
        RadioButton rbCompleted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rbCompleted = itemView.findViewById(R.id.rbCompleted);
        }
    }
}
