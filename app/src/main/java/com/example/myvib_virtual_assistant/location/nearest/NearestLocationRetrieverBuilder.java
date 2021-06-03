package com.example.myvib_virtual_assistant.location.nearest;

import com.example.myvib_virtual_assistant.data.apis.LocationBackendAPIBuilder;

public class NearestLocationRetrieverBuilder {
    public static NearestLocationRetriever get() {
        return new APINearestLocationRetriever(LocationBackendAPIBuilder.get());
    }
}
