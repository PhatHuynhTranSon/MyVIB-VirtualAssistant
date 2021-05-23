package com.example.myvib_virtual_assistant.location;

import com.example.myvib_virtual_assistant.data.apis.BackendAPIBuilder;

public class NearestLocationRetrieverBuilder {
    public static NearestLocationRetriever get() {
        return new APINearestLocationRetriever(BackendAPIBuilder.get());
    }
}
