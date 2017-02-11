package com.example.andresarango.swiper.fences;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.andresarango.swiper.AutoCompleteActivity;
import com.example.andresarango.swiper.MainActivity;
import com.example.andresarango.swiper.R;
import com.example.andresarango.swiper.model.places_response.PlacesResponse;
import com.example.andresarango.swiper.model.places_response.Result;
import com.example.andresarango.swiper.network.places.PlacesAPI;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andresarango on 2/1/17.
 */

public class StationManager {

    private final int DONOR_CODE = 0;
    private Context mContext;
    private int mRadius = 150;
    private Set<String> mTypeSet;
    private final Map<String,String> mStationMap;
    private boolean isDonor;

    public StationManager(Context context) {
        mContext = context;
        mStationMap = new HashMap<>();
        setTypeSet();
    }

    private void setTypeSet() {
        mTypeSet = new HashSet<>();
        mTypeSet.add("subway_station");
        mTypeSet.add("train_station");
        mTypeSet.add("transit_station");
    }

    public void checkIntoStations(String location) {
        checkoutOfStations();
        for (String type: mTypeSet) {
            searchByStationType(location, type);
        }

    }

    private void checkoutOfStations() {
        for (String key : mStationMap.keySet()) {
            //remove user from station group using station keys

        }
        mStationMap.clear();

    }

    private void searchByStationType(String location, String type) {
        PlacesAPI.getInstance()
                .getSectionByType(location,mRadius,type).enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                if(response.body().getStatus().equalsIgnoreCase("ok")){
                    List<Result> results = response.body().getResults();
                    //do something with results

                    if(isDonor){
                        startNotification();
                        return;
                    }

                    for (int i = 0; i < results.size(); i++) {
                        mStationMap.put(results.get(i).getPlace_id(),results.get(i).getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });
    }

    private void startNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle("HEADED SOMEWHERE?")
                        .setContentText("Click this notification if you're headed somewhere");

        Intent resultIntent = new Intent(mContext, AutoCompleteActivity.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        mContext,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);


    }

    public boolean isDonor() {
        return DONOR_CODE == MainActivity.USER_IDENTIFIER;
    }

    public void setDonor(boolean donor) {
        isDonor = donor;
    }
}
