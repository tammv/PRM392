package com.example.pe358;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT COUNT(*) FROM student WHERE id = :id")
    int countById(int id);

    @Query("SELECT COUNT(*) FROM student WHERE email = :email")
    int countByEmail(String email);

    @Insert
    void insertAll(Student... students);
}
