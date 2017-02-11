package com.example.andresarango.swiper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.SnapshotApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

public class AutoCompleteActivity extends AppCompatActivity {

    private PlaceAutocompleteFragment mAutocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
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
                getDirections(place.getLatLng());
            }

            @Override
            public void onError(Status status) {

            }
        });
    }

    private void getDirections(LatLng latLng) {
        String destination = Double.toString(latLng.latitude) + "," + Double.toString(latLng.longitude);
        //MAKE GOOGLE CLIENT SINGLETON WITH DAGGER

    }
}
