package com.example.myvib_virtual_assistant.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locations {
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
