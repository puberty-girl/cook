package com.example.cook2.Models;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.cook2.Models.RecyclerViewAdapter;
import com.example.cook2.R;
import com.example.cook2.Recipes;
import com.example.cook2.RecipesApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recycler extends AppCompatActivity {

    private static final String TAG = "Recycler";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Integer id_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_type = extras.getInt("id_type");
        }

        Log.d (TAG, "onCreate: started.");

        initImageBitmaps();

    }

    private void initImageBitmaps(){
        Log.d (TAG, "initImageBitmaps: preparing bitmaps.");

        /* mImageUrls.add("ссылка на картинку");
        mNames.add("название");
         */
        mImageUrls.add("https://andychef.ru/wp-content/uploads/2016/10/ma-3.jpg");
        mNames.add("название");
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d (TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter (this, mNames, mImageUrls);
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




                //    Log.d(TAG, "response Id:" + recipe.getId() + " Name:" + recipe.getName() + " Time:" + recipe.getId_type());
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
}