package com.example.myvib_virtual_assistant.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.myvib_virtual_assistant.R;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up Myriad fonts
        setUpFont();

        //Request permission
        requestPermissions();
    }

    //Set up font
    private void setUpFont() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("myriad-pro.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    //Request permission to listen to audio
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String requiredPermission = Manifest.permission.RECORD_AUDIO;
            String fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
            String coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION;

            // If the user previously denied this permission then show a message explaining why
            // this permission is needed
            if (checkCallingOrSelfPermission(requiredPermission) != PackageManager.PERMISSION_GRANTED ||
                checkCallingOrSelfPermission(fineLocationPermission) != PackageManager.PERMISSION_GRANTED ||
                checkCallingOrSelfPermission(coarseLocationPermission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        requiredPermission,
                        fineLocationPermission,
                        coarseLocationPermission},
                        101);
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}