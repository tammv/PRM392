package com.example.pe358;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.idTextView.setText(String.valueOf(student.id));
        holder.nameTextView.setText(student.name);
        holder.emailTextView.setText(student.email);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, nameTextView, emailTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.student_id);
            nameTextView = itemView.findViewById(R.id.student_name);
            emailTextView = itemView.findViewById(R.id.student_email);
        }
    }
}
