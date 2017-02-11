package com.example.andresarango.swiper;

import android.app.Application;

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
    }

    public AppComponent getComponent() {
        return component;
    }
}
