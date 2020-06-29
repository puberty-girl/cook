package com.example.cook2;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    //TODO Recipes
    private  static String TAG = "RecipesActivityCheck";
    Recipes recipe;
    private  String title, description, imageString;
    TextView myText;
    TextView myText2;
    ToggleButton toogleButton;
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        myText = findViewById(R.id.myTxt);
        myText2 = findViewById(R.id.myTxt2);
        image=findViewById(R.id.image);
        toogleButton = findViewById(R.id.favourite);

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

        toogleButton.setOnCheckedChangeListener(this);

      /*  toogleButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }



    /*
    public class ToggleButtonDemoActivity extends Activity implements
            CompoundButton.OnCheckedChangeListener {

        ToggleButton toogleButton;
        TextView tvInfo;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);

            toogleButton = (ToggleButton) findViewById(R.id.favourite);


            toogleButton.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked)
                //добавляем в бд
            else
                //добавляем в бд
    }*/
}
