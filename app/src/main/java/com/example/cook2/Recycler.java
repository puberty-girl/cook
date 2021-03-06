package com.example.cook2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cook2.RecyclerViewAdapter;
import com.example.cook2.R;
import com.example.cook2.Recipes;
import com.example.cook2.RecipesApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recycler extends AppCompatActivity implements RecyclerViewAdapter.OnRecipesListener {

    private static final String TAG = "RecyclerCheck";
    private RecyclerViewAdapter adapter;
    List<Recipes> recipesList;
    private Integer id_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        recipesList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_type = extras.getInt("id_type");
        }

        Log.d (TAG, "onCreate: started.");

        initRecyclerView();
        downloadData(SingleRetrofit.getInstance().createApiSample(), id_type);

    }

    private void initRecyclerView(){
        Log.d (TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void downloadData (RecipesApi recipesApi, int id) {

        Call<List<Recipes>> recipes = recipesApi.recipesbytype(id);

        recipes.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Call <List<Recipes>> call, Response<List<Recipes>> response) {
                if (response.isSuccessful()) {
                    List<Recipes> recipe = response.body();//данные по ссылке
                    recipesList.addAll(recipe);
                    adapter.setItems(recipesList);
                } else {
                    Log.d(TAG, "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Recipes>> call, Throwable t) {
                Log.d(TAG, "failure " + t);
            }
        });

    }


    @Override
    public void onRecipesClick(int position) {
        Log.d(TAG, "postion " + recipesList.get(position).getName());
        Intent intent = new Intent(this, RecipesActivity.class)
                .putExtra("image", recipesList.get(position).getImage())
                .putExtra("title", recipesList.get(position).getName())
                .putExtra("description", recipesList.get(position).getDesciption())
                .putExtra("id", recipesList.get(position).getId());
        startActivity(intent);
    }
}