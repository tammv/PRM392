package com.example.pe358;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private JSONArray todos;

    public TodoAdapter(JSONArray todos) {
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        try {
            JSONObject todo = todos.getJSONObject(position);
            holder.userId.setText("UserId: " + todo.getInt("userId"));
            holder.id.setText("Id: " + todo.getInt("id"));
            holder.title.setText("Title: " + todo.getString("title"));
            holder.completed.setChecked(todo.getBoolean("completed"));
        } catch (Exception e) {
            // Handle exception
        }
    }

    @Override
    public int getItemCount() {
        return todos.length();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView userId, id, title;
        RadioButton completed;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            completed = itemView.findViewById(R.id.completed);
        }
    }
}
