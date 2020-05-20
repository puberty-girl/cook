package com.example.cook2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import com.example.cook2.RecyclerViewAdapter;

import java.util.ArrayList;

public class Starters extends AppCompatActivity {
    private static final String TAG = "Starters";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salads);
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
