package com.example.andresarango.swiper.network.directions;


import com.example.andresarango.swiper.model.directions_response.DirectionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andresarango on 2/2/17.
 */

public interface DirectionsService {

    //https://maps.googleapis.com/maps/api/directions/json?origin=Chicago,IL&destination=Los+Angeles,CA&waypoints=Joplin,MO|Oklahoma+City,OK&key=YOUR_API_KEY
    final String JSON_PATH = "/maps/api/directions/json";

    @GET(JSON_PATH)
    Call<DirectionsResponse> getDirections(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key
    );

    @GET(JSON_PATH)
    Call<DirectionsResponse> getDirectionsSpecifyingMode(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("mode") String mode,
            @Query("key") String directionsApiKey);
}
