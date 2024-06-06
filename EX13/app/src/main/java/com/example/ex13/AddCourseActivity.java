package com.example.ex13;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
public class AddCourseActivity extends AppCompatActivity {
    private EditText inputId, inputName, inputDescription;
    private Button buttonSave;
    private CourseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        inputId = findViewById(R.id.input_id);

        inputName = findViewById(R.id.input_name);
        inputDescription = findViewById(R.id.input_description);
        buttonSave = findViewById(R.id.button_save);
// Get Database
        db = CourseDatabase.getInstance(this);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Get input data
                final String id = inputId.getText().toString();
                final String name = inputName.getText().toString();
                final String description =
                        inputDescription.getText().toString();
// Course
                final Course course = new Course(id, name, description);

new Thread(new Runnable() {
@Override
public void run() {
        db.courseDao().insert(course);
        runOnUiThread(new Runnable() {
@Override
public void run() {
        finish();
        }
        });
        }
        }).start();
        }
        });
        }
        }