package com.example.andresarango.swiper.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.andresarango.swiper.FenceService;
import com.example.andresarango.swiper.MyApplication;
import com.example.andresarango.swiper.R;

/**
 * Created by andresarango on 2/13/17.
 */

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE = 123;
    public static int USER_IDENTIFIER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
        startService(new Intent(MainActivity.this, FenceService.class));
        initializeButtons();

    }


    private void initializeButtons() {
        Button userButton = (Button) findViewById(R.id.main_button_recipient);
        Button donorButton = (Button) findViewById(R.id.main_button_donor);
        userButton.setOnClickListener(OnClick());
        donorButton.setOnClickListener(OnClick());
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

    private void requestPermission() {
        int locationPermission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        boolean locationPermissionIsNotGranted = locationPermission != PackageManager.PERMISSION_GRANTED;
        boolean APILevelIsTwentyThreeOrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        if(locationPermissionIsNotGranted && APILevelIsTwentyThreeOrHigher){
            marshamallowRequestPermission();
        }
        if (locationPermissionIsNotGranted) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_CODE);
        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    private void marshamallowRequestPermission() {
        boolean userHasAlreadyRejectedPermission = !shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);
        if(userHasAlreadyRejectedPermission){
            showMessageOKCancel("We need your location to connect you with users that need swipes. Is accessing your location too much trouble?",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                    PERMISSION_CODE);
                        }
                    });
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("NO", onClickListener)
                .setNegativeButton("YES", null)
                .create()
                .show();
    }



}
