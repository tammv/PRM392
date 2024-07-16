package com.example.ex16;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Post post);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Post> posts);

    @Update
    void update(Post post);

    @Delete
    void delete(Post post);

    @Query("SELECT * FROM post")
    List<Post> getAllPosts();

    @Query("SELECT * FROM post WHERE id = :postId LIMIT 1")
    Post getPostById(int postId);
}
