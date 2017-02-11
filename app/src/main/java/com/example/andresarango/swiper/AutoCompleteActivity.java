package com.example.andresarango.swiper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andresarango.swiper.donor.DonorResponse;
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
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoCompleteActivity extends AppCompatActivity {

    private PlaceAutocompleteFragment mAutocompleteFragment;
    @Inject
    GoogleApiClient mGoogleApiClient;
    private DonorResponse mDonorResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ((MyApplication) getApplication()).getComponent().inject(this);
        mDonorResponse = new DonorResponse(mGoogleApiClient);
        initializeAutoCompleteFragment();
    }

    private void initializeAutoCompleteFragment() {
        mAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        mAutocompleteFragment.setFilter(typeFilter);
        mAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mDonorResponse.startResponse(place.getLatLng());
            }

            @Override
            public void onError(Status status) {

            }
        });
    }


}
