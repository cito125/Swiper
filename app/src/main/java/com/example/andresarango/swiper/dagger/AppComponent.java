package com.example.andresarango.swiper.dagger;

import com.example.andresarango.swiper.AutoCompleteActivity;
import com.example.andresarango.swiper.MainActivity;
import com.example.andresarango.swiper.fences.FenceManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andresarango on 2/11/17.
 */

@Singleton
@Component(modules = { GoogleClientModule.class})
public interface AppComponent {

    void inject(FenceManager fenceManager);

    void inject(AutoCompleteActivity autoCompleteActivity);

    void inject(MainActivity mainActivity);
}
