package com.example.myvib_virtual_assistant.location;

public interface NearestLocationRetriever {
    void getNearestLocations(double lat, double lon, NearestLocationRetrieverListener listener);
}
