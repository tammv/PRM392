package com.example.ex6;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "State: Created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "State: Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "State: Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "State: Destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "State: Paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "State: Resumed");
    }
}