package com.example.andresarango.swiper;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andresarango.swiper.fences.FenceManager;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE = 123;
    public static int USER_IDENTIFIER = 0;
    private Button mUserButton;
    private Button mDonorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeButtons();

    }


    private void initializeButtons() {
        mUserButton = (Button) findViewById(R.id.main_button_recipient);
        mDonorButton = (Button) findViewById(R.id.main_button_donor);
        mUserButton.setOnClickListener(OnClick());
        mDonorButton.setOnClickListener(OnClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private View.OnClickListener OnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.main_button_donor:
                        setUserIdentifier(0);
                        System.out.println(Integer.toString(0));
                        break;
                    case R.id.main_button_recipient:
                        setUserIdentifier(1);
                        System.out.println(Integer.toString(1));
                }
            }
        };
    }

    public static void setUserIdentifier(int userIdentifier) {
        USER_IDENTIFIER = userIdentifier;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
