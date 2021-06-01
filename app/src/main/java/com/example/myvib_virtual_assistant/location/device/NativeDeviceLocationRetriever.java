package com.example.myvib_virtual_assistant.location.device;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class NativeDeviceLocationRetriever implements DeviceLocationRetriever, LocationListener {
    private Context mContext;
    private LocationManager mLocationManager;
    private DeviceLocationRetrieverListener mListener;

    public NativeDeviceLocationRetriever(Context mContext) {
        //Set context
        this.mContext = mContext;

        //Set location manager
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void getLocation(DeviceLocationRetrieverListener listener) {
        //Set listener
        mListener = listener;

        //Request location update
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mListener.onDeviceLocationError(new Exception("Location service not available"));
            return;
        }
        mLocationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, null);
    }

    @Override
    public void onLocationChanged(Location location) {
        //Call listener
        mListener.onDeviceLocationResult(new Coordinate(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //NOTHING
    }

    @Override
    public void onProviderEnabled(String provider) {
        //NOTHING
    }

    @Override
    public void onProviderDisabled(String provider) {
        //NOTHING
    }
}
