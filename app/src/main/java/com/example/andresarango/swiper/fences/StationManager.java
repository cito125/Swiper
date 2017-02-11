package com.example.andresarango.swiper.fences;


import com.example.andresarango.swiper.MainActivity;
import com.example.andresarango.swiper.model.places_response.PlacesResponse;
import com.example.andresarango.swiper.model.places_response.Result;
import com.example.andresarango.swiper.network.places.PlacesAPI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andresarango on 2/1/17.
 */

public class StationManager {

    private final int DONOR_CODE = 0;
    private int mRadius = 150;
    private Set<String> mTypeSet;
    private boolean isDonor;

    public StationManager() {
        setTypeSet();
    }

    private void setTypeSet() {
        mTypeSet = new HashSet<>();
        mTypeSet.add("subway_station");
        mTypeSet.add("train_station");
        mTypeSet.add("transit_station");
    }

    public void checkForSubways(String location) {
        for (String type: mTypeSet) {
            searchByStationType(location, type);
        }

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
                        results.get(i).getPlace_id();
                        results.get(i).getName();
                    }
                }
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });
    }

    private void startNotification() {

    }

    public boolean isDonor() {

        return DONOR_CODE == MainActivity.USER_IDENTIFIER;
    }

    public void setDonor(boolean donor) {
        isDonor = donor;
    }
}
