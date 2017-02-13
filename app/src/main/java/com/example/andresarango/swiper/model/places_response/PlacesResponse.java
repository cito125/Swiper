package com.example.andresarango.swiper.model.places_response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andresarango on 1/30/17.
 */
public class PlacesResponse {
    List<Result> results;
    @SerializedName("next_page_token")
    String nextPageToken;
    String status;

    public List<Result> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }
}
