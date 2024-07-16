package com.example.pe_prm392_de160129;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
