package com.example.pe_prm392_de160129;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void insert(Student student);

    @Query("SELECT * FROM student")
    List<Student> getAllStudents();
}
