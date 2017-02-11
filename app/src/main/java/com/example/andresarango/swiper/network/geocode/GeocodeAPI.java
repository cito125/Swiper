package com.example.andresarango.swiper.network.geocode;


import com.example.andresarango.swiper.model.geocode_response.GeocodeResponse;
import com.example.andresarango.swiper.network.NetworkServices;

import retrofit2.Call;

/**
 * Created by andresarango on 2/3/17.
 */

public class GeocodeAPI {


    private static final String BASE_URL = "https://maps.googleapis.com/";
    private static final String API_KEY = "AIzaSyCkNZD25oqeLytGDjSKnQCrjRvrYj2wo_c";
    private final GeocodeService apiService;
    private static volatile GeocodeAPI instance;

    public static GeocodeAPI getInstance(){
        if(instance == null){
            synchronized (GeocodeAPI.class){
                if(instance == null){
                    instance = new GeocodeAPI();
                }
            }
        }
        return instance;
    }

    private GeocodeAPI(){
        apiService = (new NetworkServices()).getJSONService(BASE_URL, GeocodeService.class);
    }
    
    public Call<GeocodeResponse> getGeocodeResponse(String latitudeLongitude){
        return apiService.getGeocodeResponse(latitudeLongitude,API_KEY);
    }
    
}

