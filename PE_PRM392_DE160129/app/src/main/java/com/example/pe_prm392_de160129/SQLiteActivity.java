package com.example.pe_prm392_de160129;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    private EditText etId, etName, etEmail;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private StudentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        db = Room.databaseBuilder(getApplicationContext(), StudentDatabase.class, "StudentDB").allowMainThreadQueries().build();

        btnAdd.setOnClickListener(v -> {
            String idStr = etId.getText().toString();
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();

            if (TextUtils.isEmpty(idStr) || TextUtils.isEmpty(name) || TextUtils.isEmpty(email)) {
                Toast.makeText(SQLiteActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idStr);

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setEmail(email);

            db.studentDAO().insert(student);
            loadStudents();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadStudents();
    }

    private void loadStudents() {
        List<Student> students = db.studentDAO().getAllStudents();
        studentAdapter = new StudentAdapter(this, students);
        recyclerView.setAdapter(studentAdapter);
    }
}
