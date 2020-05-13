package com.example.cook2.Models;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.cook2.Models.RecyclerViewAdapter;
import com.example.cook2.R;

import java.util.ArrayList;

public class Recycler extends AppCompatActivity {

    private static final String TAG = "Recycler";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
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

}