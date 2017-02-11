package com.example.andresarango.swiper.model.geocode_response;

import java.util.List;

/**
 * Created by andresarango on 2/3/17.
 */
public class GeocodeResponse {
    List<Result> results;
    String status;

    public String getStatus() {
        return status;
    }

    public List<Result> getResults() {
        return results;
    }
}
