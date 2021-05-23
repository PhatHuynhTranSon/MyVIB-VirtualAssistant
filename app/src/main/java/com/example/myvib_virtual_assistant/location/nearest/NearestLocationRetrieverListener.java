package com.example.myvib_virtual_assistant.location.nearest;

import com.example.myvib_virtual_assistant.data.models.Location;

import java.util.List;

public interface NearestLocationRetrieverListener {
    void onBankLocationsResult(List<Location> results);
    void onBankLocationsError(Throwable t);
}
