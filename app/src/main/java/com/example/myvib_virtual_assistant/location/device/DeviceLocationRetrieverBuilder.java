package com.example.myvib_virtual_assistant.location.device;

import android.content.Context;

public class DeviceLocationRetrieverBuilder {
    public static DeviceLocationRetriever get(Context context) {
        return new NativeDeviceLocationRetriever(context);
    }
}
