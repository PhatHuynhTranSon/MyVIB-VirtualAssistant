package com.example.myvib_virtual_assistant.location.device;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class FusedLocationRetriever implements DeviceLocationRetriever {
    //Location provider and context
    private FusedLocationProviderClient mLocationProvider;
    private Context mContext;

    //Listener
    DeviceLocationRetrieverListener mListener;

    public FusedLocationRetriever(Context mContext) {
        //Set context
        this.mContext = mContext;
        //Set location provider
        mLocationProvider = LocationServices.getFusedLocationProviderClient(mContext);
    }

    @Override
    public void getLocation(DeviceLocationRetrieverListener listener) {
        //Set listener

        mListener = listener;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
             mListener.onDeviceLocationError(new Exception("Location service is not yet enabled."));
            return;
        }
        mLocationProvider.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        mListener.onDeviceLocationResult(
                                new Coordinate(location.getLatitude(), location.getLongitude())
                        );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mListener.onDeviceLocationError(e);
                    }
                });
    }
}
