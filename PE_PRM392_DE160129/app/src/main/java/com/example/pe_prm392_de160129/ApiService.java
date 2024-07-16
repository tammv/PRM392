package com.example.pe_prm392_de160129;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("todos")
    Call<List<Todo>> getTodos();
}
