package com.example.andresarango.swiper.model.directions_response;

import java.util.List;

/**
 * Created by andresarango on 2/2/17.
 */
public class Leg {
    List<Step> steps;
    Duration duration;
    DepartureTime departure_time;

    public List<Step> getSteps() {
        return steps;
    }

    public Duration getDuration() {
        return duration;
    }

    public DepartureTime getDeparture_time() {
        return departure_time;
    }
}
