package com.example.cook2;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesActivity extends AppCompatActivity {

    //TODO Recipes
    private  static String TAG = "RecipesActivityCheck";
    Recipes recipe;
    private  String title, description, imageString;
    TextView myText;
    TextView myText2;
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        myText = findViewById(R.id.myTxt);
        myText = findViewById(R.id.myTxt2);
        image=findViewById(R.id.image);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            imageString = extras.getString("image");
            description = extras.getString("description");
        }
        myText.setText(title);
        Glide.with(getApplicationContext()).asBitmap().load(imageString)//откуда идет
                .into(image);
        myText2.setText(description);
        Glide.with(getApplicationContext()).asBitmap().load(imageString)//откуда идет
                .into(image);
    }
}
