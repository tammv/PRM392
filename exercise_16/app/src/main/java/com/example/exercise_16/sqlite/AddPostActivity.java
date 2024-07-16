package com.example.exercise_16.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.exercise_16.Post;
import com.example.exercise_16.PostDao;
import com.example.exercise_16.PostDatabase;
import com.example.exercise_16.R;

public class AddPostActivity extends AppCompatActivity {

    private EditText etTitle, etBody;
    private Button btnSave;
    private PostDao postDao;
    private int postId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSave = findViewById(R.id.btnSave);

        PostDatabase db = Room.databaseBuilder(getApplicationContext(), PostDatabase.class, "post-db").allowMainThreadQueries().build();
        postDao = db.postDao();

        Intent intent = getIntent();
        if (intent.hasExtra("post_id")) {
            postId = intent.getIntExtra("post_id", -1);
            Post post = postDao.getPostById(postId);
            etTitle.setText(post.getTitle());
            etBody.setText(post.getBody());
        }

        btnSave.setOnClickListener(view -> {
            String title = etTitle.getText().toString().trim();
            String body = etBody.getText().toString().trim();

            if (!title.isEmpty() && !body.isEmpty()) {
                if (postId == -1) {
                    Post post = new Post(title, body);
                    postDao.insert(post);
                    Toast.makeText(AddPostActivity.this, "Post added", Toast.LENGTH_SHORT).show();
                } else {
                    Post post = new Post(postId, title, body);
                    postDao.update(post);
                    Toast.makeText(AddPostActivity.this, "Post updated", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(AddPostActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
