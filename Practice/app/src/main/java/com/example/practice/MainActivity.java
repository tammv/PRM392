package com.example.practice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        FloatingActionButton fab = findViewById(R.id.fab);

        // Initialize ViewModel
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                // Update UI
            }
        });

        fab.setOnClickListener(v -> {
            // Open dialog or new activity to add a new course
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {case R.id.action_add_course:
                // Add a new course
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
