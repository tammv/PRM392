package com.example.exercise_16;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostDao postDao();
}
