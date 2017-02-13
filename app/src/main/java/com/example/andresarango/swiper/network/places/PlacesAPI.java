package com.example.andresarango.swiper.network.places;


import com.example.andresarango.swiper.model.places_response.PlacesResponse;
import com.example.andresarango.swiper.network.NetworkServices;

import retrofit2.Call;

/**
 * Created by andresarango on 1/29/17.
 */

public class PlacesAPI {

    private static final String PLACE_API_KEY = "AIzaSyCkNZD25oqeLytGDjSKnQCrjRvrYj2wo_c";
    private String BASE_GOOGLE_PLACES_URL = "https://maps.googleapis.com/";
    private final PlacesService apiService;

    private static volatile PlacesAPI instance;

    public static PlacesAPI getInstance() {
        if (instance == null) {
            synchronized (PlacesAPI.class) {
                if (instance == null) {
                    instance = new PlacesAPI();
                }
            }
        }
        return instance;
    }

    private PlacesAPI() {
        apiService = (new NetworkServices()).getJSONService(BASE_GOOGLE_PLACES_URL, PlacesService.class);
    }

    public Call<PlacesResponse> getSection(String location, Integer radius) {
        return apiService.getPlaces(location, radius.toString(), PLACE_API_KEY);
    }

    public Call<PlacesResponse> getSectionByType(String location, Integer radius, String type) {
        return apiService.getPlacesByType(location, radius.toString(), type, PLACE_API_KEY);
    }

    public Call<PlacesResponse> getNextSectionPage(String token) {
        return apiService.nextPage(token, PLACE_API_KEY);
    }

}
