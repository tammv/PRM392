package com.example.pe_prm392_de160129;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTodos();
    }

    private void loadTodos() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Todo>> call = apiService.getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful()) {
                    List<Todo> todos = response.body();
                    todoAdapter = new TodoAdapter(ApiActivity.this, todos);
                    recyclerView.setAdapter(todoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(ApiActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
