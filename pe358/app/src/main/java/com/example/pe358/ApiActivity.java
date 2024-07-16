package com.example.pe358;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter adapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);
        loadTodos();
    }

    private void loadTodos() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                adapter = new TodoAdapter(response);
                recyclerView.setAdapter(adapter);
            }
        }, error -> {
            // Handle error
        });

        requestQueue.add(jsonArrayRequest);
    }
}