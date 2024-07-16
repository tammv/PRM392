package com.example.pe358;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int INTERNET_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasInternetPermission()) {
            requestInternetPermission();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sqlite) {
            startActivity(new Intent(this, SQLiteActivity.class));
            return true;
        } else if (id == R.id.api) {
            startActivity(new Intent(this, ApiActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private boolean hasInternetPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, INTERNET_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
