package com.example.andresarango.swiper.dagger;

import android.content.Context;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andresarango on 2/11/17.
 */

@Module
public class GoogleClientModule {

    private final Context mContext;

    public GoogleClientModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public GoogleApiClient provideGoogleApiClient() {
        return new GoogleApiClient.Builder(mContext)
                .addApi(Awareness.API)
                .build();
    }
}
