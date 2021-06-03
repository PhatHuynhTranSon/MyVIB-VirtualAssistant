package com.example.myvib_virtual_assistant.data.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationBackendAPIBuilder {
    public static final String URL = "https://vib-competition-location.herokuapp.com/";

    private static <T> T builder(Class<T> endpoint) {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoint);
    }

    public static LocationBackendAPI get() {
        return builder(LocationBackendAPI.class);
    }
}
