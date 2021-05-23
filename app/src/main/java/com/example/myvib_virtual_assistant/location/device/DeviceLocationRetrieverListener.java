package com.example.myvib_virtual_assistant.location.device;

public interface DeviceLocationRetrieverListener {
    void onDeviceLocationResult(Coordinate coordinate);
    void onDeviceLocationError(Throwable t);
}
