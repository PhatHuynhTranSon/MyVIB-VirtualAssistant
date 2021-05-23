package com.example.myvib_virtual_assistant.data.requests;

public class LocationRequestBody {
    public double lat, lon;

    public LocationRequestBody(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
