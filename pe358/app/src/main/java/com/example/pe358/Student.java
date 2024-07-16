package com.example.pe358;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String email;
}
