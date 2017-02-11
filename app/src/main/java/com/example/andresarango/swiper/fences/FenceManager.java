package com.example.andresarango.swiper.fences;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;

/**
 * Created by andresarango on 1/31/17.
 */

public class FenceManager {

    private static final String FENCE_RECEIVER_ACTION = "FENCE_RECEIVER_ACTION";
    private final Context mContext;
    private final UserFenceReciever mUserFenceReciever;
    private final PendingIntent mPendingIntent;
    public static final String FENCE_KEY = "fence key";
    private GoogleApiClient mGoogleApiClient;
    private static int PERMISSION_LOCATION_CODE;
    private double mRadius = 500;
    private StationManager mStationManager;


    public FenceManager(Context context, int locationCode) {
        setPermissionLocationCode(locationCode);
        mContext = context;
        mUserFenceReciever = new UserFenceReciever(this);
        Intent intent = new Intent(FENCE_RECEIVER_ACTION);
        mPendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);
        mStationManager = new StationManager(mContext);
        buildClient();
        requestPermission();
    }

    private void buildClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(Awareness.API)
                .build();
        mGoogleApiClient.connect();
    }

    public void requestPermission() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_LOCATION_CODE);
        }
    }

    public void makeFence() {
        Awareness.SnapshotApi.getLocation(mGoogleApiClient).setResultCallback(new ResultCallback<LocationResult>() {
            @Override
            public void onResult(@NonNull LocationResult locationResult) {
                if (locationResult.getStatus().isSuccess()) {
                    double latitude = locationResult.getLocation().getLatitude();
                    double longitude = locationResult.getLocation().getLongitude();
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("need permission");
                        return;
                    }
                    String location = Double.toString(latitude) + "," + Double.toString(longitude);
                    mStationManager.checkForSubways(location);
                    AwarenessFence awarenessFence = LocationFence.exiting(latitude, longitude, mRadius);
                    updateFences(awarenessFence);
                }
            }
        });
    }

    public void replaceFence() {
        Awareness.FenceApi.updateFences(
                mGoogleApiClient,
                new FenceUpdateRequest.Builder()
                        .removeFence(FENCE_KEY)
                        .build()).setResultCallback(new ResultCallbacks<Status>() {
            @Override
            public void onSuccess(@NonNull Status status) {
                System.out.println("good");
            }

            @Override
            public void onFailure(@NonNull Status status) {
                System.out.println("try again");
            }
        });
        makeFence();
    }

    private void updateFences(AwarenessFence awarenessFence) {
        Awareness.FenceApi.updateFences(
                mGoogleApiClient,
                new FenceUpdateRequest.Builder()
                        .addFence(FENCE_KEY, awarenessFence, mPendingIntent)
                        .build())
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            // code to be decided
                        }
                    }
                });
    }

    public UserFenceReciever getmUserFenceReciever() {
        return mUserFenceReciever;
    }

    public static void setPermissionLocationCode(int permissionLocationCode) {
        PERMISSION_LOCATION_CODE = permissionLocationCode;
    }

    public void setStationFenceManager(StationManager stationManager) {
        mStationManager = stationManager;
    }

}
