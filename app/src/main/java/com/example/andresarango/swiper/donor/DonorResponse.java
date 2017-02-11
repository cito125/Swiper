package com.example.andresarango.swiper.donor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.example.andresarango.swiper.model.directions_response.DirectionsResponse;
import com.example.andresarango.swiper.model.directions_response.Leg;
import com.example.andresarango.swiper.model.directions_response.Step;
import com.example.andresarango.swiper.model.geocode_response.GeocodeResponse;
import com.example.andresarango.swiper.network.directions.DirectionsAPI;
import com.example.andresarango.swiper.network.geocode.GeocodeAPI;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.snapshot.LocationResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andresarango on 2/11/17.
 */

public class DonorResponse {

    private static final String TIME_UNTIL_USER_ARRIVES_AT_STATION = "approximate seconds until user arrives";
    private static final String ARRIVAL_STATION = "Name of arrival station";
    private static final String ARRIVAL_STATION_COORDINATES = "lat,lng";
    private static final String ARRIVAL_STATION_PLACE_ID = "place_id of arrival station";

    private final GoogleApiClient mGoogleApiClient;

    private Map<String, String> mInfoMap;


    public DonorResponse(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }

    public void startResponse(final LatLng latLng) {
        final String destination = Double.toString(latLng.latitude) + "," + Double.toString(latLng.longitude);
        if (ActivityCompat.checkSelfPermission(mGoogleApiClient.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Awareness.SnapshotApi.getLocation(mGoogleApiClient).setResultCallback(new ResultCallback<LocationResult>() {
            @Override
            public void onResult(@NonNull LocationResult locationResult) {
                String origin = locationResult.getLocation().getLatitude() + "," + locationResult.getLocation().getLongitude();
                getDirections(origin, destination);
            }
        });

    }

    private void getDirections(String origin, String destination) {
        DirectionsAPI.getInstance()
                .getDirections(origin, destination)
                .enqueue(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        fillInfoMap(response.body());


                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {

                    }
                });
    }

    private void fillInfoMap(DirectionsResponse response) {
        if (response.getStatus().equalsIgnoreCase("ok")) {
            Leg leg = response.getRoutes().get(0).getLegs().get(0);

            String arrivalStationName = "";
            String latLng = "";

            double latitude;
            double longitude;

            int secondsAfterArrivingAtTrainStation = 0;
            int departureTimeUnix = leg.getDeparture_time().getValue();
            int totalTravelSeconds = leg.getDuration().getValue();

            List<Step> stepList = leg.getSteps();
            for (int i = 0; i < stepList.size(); i++) {
                Step step = stepList.get(i);
                String travelMode = step.getTravelMode();
                String type = step.getTransit_details().getLine().getVehicle().getType();
                secondsAfterArrivingAtTrainStation += step.getDuration().getValue();
                if (travelMode.equalsIgnoreCase("TRANSIT") && type.equalsIgnoreCase("subway")) {

                    arrivalStationName = step.getTransit_details().getArrival_stop().getName();
                    latitude = step.getEndLocation().getLat();
                    longitude = step.getEndLocation().getLng();
                    latLng = Double.toString(latitude) + "," + Double.toString(longitude);
                    secondsAfterArrivingAtTrainStation = 0;


                }else{
                    return;
                }
            }

            int secondsUntilUserArrivesAtTrainStation = totalTravelSeconds - secondsAfterArrivingAtTrainStation;
            mInfoMap = new HashMap<>();
            mInfoMap.put(TIME_UNTIL_USER_ARRIVES_AT_STATION, Integer.toString(secondsUntilUserArrivesAtTrainStation));
            mInfoMap.put(ARRIVAL_STATION, arrivalStationName);
            mInfoMap.put(ARRIVAL_STATION_COORDINATES, latLng);
            setPlaceID(latLng);



        }

    }

    public void setPlaceID(String latlng) {
        GeocodeAPI.getInstance()
                .getGeocodeResponse(latlng)
                .enqueue(new Callback<GeocodeResponse>() {
                    @Override
                    public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                        String place_id = response.body().getResults().get(0).getPlace_id();
                        mInfoMap.put(ARRIVAL_STATION_PLACE_ID,place_id);
                        notifyFirebase();
                    }

                    @Override
                    public void onFailure(Call<GeocodeResponse> call, Throwable t) {

                    }
                });
    }

    private void notifyFirebase() {
        //Add logic to notify firebase, info is held in firebase
    }
}
