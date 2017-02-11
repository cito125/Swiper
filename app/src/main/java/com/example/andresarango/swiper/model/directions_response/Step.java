package com.example.andresarango.swiper.model.directions_response;

/**
 * Created by andresarango on 2/2/17.
 */
public class Step {
    String travel_mode;
    EndLocation end_location;
    Duration duration;
    TransitDetails transit_details;

    public String getTravelMode() {
        return travel_mode;
    }

    public EndLocation getEndLocation() {
        return end_location;
    }

    public Duration getDuration() {
        return duration;
    }

    public TransitDetails getTransit_details() {
        return transit_details;
    }
}
