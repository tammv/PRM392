package com.example.pe358;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
}
