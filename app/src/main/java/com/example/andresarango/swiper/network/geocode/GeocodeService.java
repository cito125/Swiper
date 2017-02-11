package com.example.andresarango.swiper.network.geocode;


import com.example.andresarango.swiper.model.geocode_response.GeocodeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andresarango on 2/3/17.
 */
public interface GeocodeService {
    //https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
    @GET("maps/api/geocode/json")
    Call<GeocodeResponse> getGeocodeResponse(
            @Query("latlng") String latlng,
            @Query("key") String key
    );
}
