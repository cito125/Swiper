package com.example.andresarango.swiper.model.places_response;

import java.util.List;

/**
 * Created by andresarango on 1/31/17.
 */
public class Result {
    Geometry geometry;
    String place_id;
    List<String> types;
    String name;

    public String getName() {
        return name;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getPlace_id() {
        return place_id;
    }

    public List<String> getTypes() {
        return types;
    }
}
