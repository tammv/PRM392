package com.example.practice;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private String instructor;

    // Getters and setters...
}