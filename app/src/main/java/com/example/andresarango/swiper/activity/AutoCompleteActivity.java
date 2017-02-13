package com.example.andresarango.swiper.activity;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andresarango.swiper.MyApplication;
import com.example.andresarango.swiper.R;
import com.example.andresarango.swiper.donor.DonorResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import javax.inject.Inject;

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
