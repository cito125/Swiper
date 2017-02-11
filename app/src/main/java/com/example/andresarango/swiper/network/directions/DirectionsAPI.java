package com.example.andresarango.swiper.network.directions;


import com.example.andresarango.swiper.model.directions_response.DirectionsResponse;
import com.example.andresarango.swiper.network.NetworkServices;

import retrofit2.Call;

/**
 * Created by andresarango on 2/2/17.
 */

public class DirectionsAPI {

    private static final String DIRECTIONS_BASE_URL = "https://maps.googleapis.com/";
    ;
    private static final String DIRECTIONS_API_KEY = "AIzaSyCkNZD25oqeLytGDjSKnQCrjRvrYj2wo_c";
    private final DirectionsService apiService;
    private static volatile DirectionsAPI instance;

    private DirectionsAPI() {
        apiService = (new NetworkServices()).getJSONService(DIRECTIONS_BASE_URL, DirectionsService.class);
    }

    public static DirectionsAPI getInstance() {
        if (instance == null) {
            synchronized (DirectionsAPI.class) {
                if (instance == null) {
                    instance = new DirectionsAPI();
                }
            }
        }
        return instance;
    }

    public Call<DirectionsResponse> getDirections(String origin, String destination) {
        return apiService.getDirections(origin, destination, DIRECTIONS_API_KEY);
    }

    public Call<DirectionsResponse> getDirectionsSpecifyingModeOfTransportation(String origin_place_id
            , String destination_place_id, String mode) {
        String origin = "place_id:" + origin_place_id;
        String destination = "place_id:" + destination_place_id;
        return apiService.getDirectionsSpecifyingMode(origin, destination, mode, DIRECTIONS_API_KEY);
    }


}
