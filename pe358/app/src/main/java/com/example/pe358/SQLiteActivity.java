package com.example.pe358;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity {
    private StudentDatabase db;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private EditText editTextId, editTextName, editTextEmail;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonAdd = findViewById(R.id.buttonAdd);

        db = Room.databaseBuilder(getApplicationContext(), StudentDatabase.class, "StudentDB").allowMainThreadQueries().build();

        List<Student> students = db.studentDao().getAll();
        adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String idStr = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        if (TextUtils.isEmpty(idStr) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isDigitsOnly(idStr)) {
            Toast.makeText(this, "ID must be a number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            Toast.makeText(this, "Name must contain only letters and spaces", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);

        // Kiểm tra trùng ID
        if (db.studentDao().countById(id) > 0) {
            Toast.makeText(this, "ID already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        // Kiểm tra trùng Email
        if (db.studentDao().countByEmail(email) > 0) {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student();
        student.id = id;
        student.name = name;
        student.email = email;

        db.studentDao().insertAll(student);

        List<Student> students = db.studentDao().getAll();
        adapter = new StudentAdapter(students);
        recyclerView.setAdapter(adapter);

        Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
    }
}
