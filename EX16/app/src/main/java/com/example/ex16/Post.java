package com.example.ex16;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String body;

    // Constructor cho việc tạo post mới không cần id
    @Ignore
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    // Constructor đầy đủ với id
    public Post(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    // Getters và setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
