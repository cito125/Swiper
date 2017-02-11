package com.example.andresarango.swiper.model.directions_response;

import java.util.List;

/**
 * Created by andresarango on 2/2/17.
 */
public class DirectionsResponse {
    String status;
    List<Route> routes;

    public String getStatus() {
        return status;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
