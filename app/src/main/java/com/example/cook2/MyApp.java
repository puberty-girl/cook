package com.example.cook2;

import android.app.Application;

public class MyApp extends Application {

    public void onCreate(){
        super.onCreate();
        SingleRetrofit.initInstance();
    }
}

