package com.example.ex16;

import static android.content.ContentValues.TAG;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.ex16.PostAdapter;
import com.example.ex16.BlueToothReceiver;
import com.example.ex16.WifiReceiver;
import com.example.ex16.ApiClient;
import com.example.ex16.ApiService;
import com.example.ex16.AddPostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private PostDAO postDao;
    private PostAdapter postAdapter;
    private WifiReceiver wifiReceiver = new WifiReceiver();
    private BlueToothReceiver bluetoothReceiver = new BlueToothReceiver();

    private static final int REQUEST_CODE_EDIT_POST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PostDatabase db = Room.databaseBuilder(getApplicationContext(), PostDatabase.class, "post-db").allowMainThreadQueries().build();
        postDao = db.postDao();

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
            startActivityForResult(intent, REQUEST_CODE_EDIT_POST);
        });

        loadPostsFromApi();
    }

    private void loadPostsFromApi() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    postDao.insertAll(posts);
                    loadPostsFromDatabase();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load posts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadPostsFromDatabase() {
        List<Post> posts = postDao.getAllPosts();
        postAdapter = new PostAdapter(this, posts, new PostAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                Post post = postAdapter.getPostAt(position);
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                intent.putExtra("post_id", post.getId());
                startActivityForResult(intent, REQUEST_CODE_EDIT_POST);
            }

            @Override
            public void onDeleteClick(int position) {
                Post post = postAdapter.getPostAt(position);
                postDao.delete(post);
                postAdapter.removePost(position);
                Toast.makeText(MainActivity.this, "Post deleted", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPostsFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT_POST && resultCode == RESULT_OK) {
            loadPostsFromDatabase();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check and register Wifi Receiver
        try {
            IntentFilter wifiFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            registerReceiver(wifiReceiver, wifiFilter);
            Log.d(TAG, "Wifi - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Wifi - Failed to register receiver", e);
        }

        // Check and register Bluetooth Receiver
        try {
            IntentFilter bluetoothFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(bluetoothReceiver, bluetoothFilter);
            Log.d(TAG, "Bluetooth - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Bluetooth - Failed to register receiver", e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unregister Wifi Receiver
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
            Log.d(TAG, "Wifi - Receiver unregistered");
        }
        // Unregister Bluetooth Receiver
        if (bluetoothReceiver != null) {
            unregisterReceiver(bluetoothReceiver);
            Log.d(TAG, "Bluetooth - Receiver unregistered");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
