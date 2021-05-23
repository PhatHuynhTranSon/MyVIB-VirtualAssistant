package com.example.myvib_virtual_assistant.location;

import com.example.myvib_virtual_assistant.data.models.Location;

import java.util.List;

public interface NearestLocationRetrieverListener {
    void onResult(List<Location> results);
    void onError(Throwable t);
}
