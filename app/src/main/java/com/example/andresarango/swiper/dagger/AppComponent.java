package com.example.andresarango.swiper.dagger;

import com.example.andresarango.swiper.activity.AutoCompleteActivity;
import com.example.andresarango.swiper.FenceService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andresarango on 2/11/17.
 */

@Singleton
@Component(modules = {GoogleClientModule.class})
public interface AppComponent {

    void inject(AutoCompleteActivity autoCompleteActivity);

    void inject(FenceService fenceService);
}
