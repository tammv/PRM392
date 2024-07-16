package com.example.practice;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {
    private CourseDao courseDao;
    private List<Course> allCourses;

    public CourseRepository(Application application) {
        CourseDatabase database = Room.databaseBuilder(application, CourseDatabase.class, "course_database")
                .fallbackToDestructiveMigration()
                .build();
        courseDao = database.courseDao();
        allCourses = courseDao.getAllCourses();
    }

    public void insert(Course course) {
        // Insert in background thread
    }

    public void update(Course course) {
        // Update in background thread
    }

    public void delete(Course course) {
        // Delete in background thread
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }
}
