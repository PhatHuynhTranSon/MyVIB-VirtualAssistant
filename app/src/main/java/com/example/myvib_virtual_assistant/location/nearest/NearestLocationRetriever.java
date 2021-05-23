package com.example.myvib_virtual_assistant.location.nearest;

public interface NearestLocationRetriever {
    void getNearestLocations(double lat, double lon, NearestLocationRetrieverListener listener);
}
