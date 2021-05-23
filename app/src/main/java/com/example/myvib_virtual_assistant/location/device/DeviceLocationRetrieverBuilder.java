package com.example.myvib_virtual_assistant.location.device;

import android.content.Context;

public class DeviceLocationRetrieverBuilder {
    public static DeviceLocationRetriever getOriginal(Context context) {
        return new NativeDeviceLocationRetriever(context);
    }

    public static DeviceLocationRetriever getFused(Context context) {
        return new FusedLocationRetriever(context);
    }
}
