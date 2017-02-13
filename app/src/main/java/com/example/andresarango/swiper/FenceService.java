package com.example.andresarango.swiper;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.example.andresarango.swiper.activity.AutoCompleteActivity;
import com.example.andresarango.swiper.activity.MainActivity;
import com.example.andresarango.swiper.fences.FenceManager;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

public class FenceService extends Service {


    @Inject
    GoogleApiClient mGoogleApiClient;
    private FenceManager mFenceManager;

    @Override
    public void onCreate() {
        super.onCreate();
        ((MyApplication) getApplication()).getComponent().inject(this);
        startFences();

    }

    private void startFences() {
        mFenceManager = new FenceManager(mGoogleApiClient, MainActivity.PERMISSION_CODE);
        mFenceManager.makeFence();
        registerReceiver(mFenceManager.getmUserFenceReciever(), new IntentFilter(FenceManager.FENCE_RECEIVER_ACTION));

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }


    @Override
    public void onDestroy() {
        unregisterReceiver(mFenceManager.getmUserFenceReciever());
        super.onDestroy();
    }
}
