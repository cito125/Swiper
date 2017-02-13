package com.example.andresarango.swiper;

import android.app.Application;
import android.content.Intent;

import com.example.andresarango.swiper.dagger.AppComponent;
import com.example.andresarango.swiper.dagger.DaggerAppComponent;
import com.example.andresarango.swiper.dagger.GoogleClientModule;

/**
 * Created by andresarango on 2/11/17.
 */

public class MyApplication extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .googleClientModule(new GoogleClientModule(getApplicationContext()))
                .build();

        startService(new Intent(MyApplication.this, FenceService.class));


    }

    public AppComponent getComponent() {
        return component;
    }
}
