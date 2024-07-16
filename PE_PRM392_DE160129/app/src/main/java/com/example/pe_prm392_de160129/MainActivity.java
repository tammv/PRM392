package com.example.pe_prm392_de160129;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SparseArray<Runnable> menuActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupMenuActions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumain, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Runnable action = menuActions.get(item.getItemId());
        if (action != null) {
            action.run();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupMenuActions() {
        menuActions = new SparseArray<>();
        menuActions.put(R.id.action_sqlite, () -> startActivity(new Intent(this, SQLiteActivity.class)));
        menuActions.put(R.id.action_api, () -> startActivity(new Intent(this, ApiActivity.class)));
    }
}