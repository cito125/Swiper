package com.example.andresarango.swiper.network.places;


import com.example.andresarango.swiper.model.places_response.PlacesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andresarango on 1/29/17.
 */
public interface PlacesService {
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY

    @GET("maps/api/place/nearbysearch/json")
    Call<PlacesResponse> getPlaces(@Query("location") String location,
                                   @Query("radius") String radius,
                                   @Query("key") String key);

    @GET("maps/api/place/nearbysearch/json")
    Call<PlacesResponse> getPlacesByType(@Query("location") String location,
                                         @Query("radius") String radius,
                                         @Query("type") String type,
                                         @Query("key") String key);


    @GET("maps/api/place/nearbysearch/json")
    Call<PlacesResponse> nextPage(@Query("pagetoken") String pagetoken,
                                  @Query("key") String key);
}
