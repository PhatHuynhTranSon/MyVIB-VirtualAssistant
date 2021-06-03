package com.example.myvib_virtual_assistant.data.apis;


import com.example.myvib_virtual_assistant.data.models.Locations;
import com.example.myvib_virtual_assistant.data.requests.LocationRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationBackendAPI {
    @POST("/locations")
    Call<Locations> getNearestLocations(@Body LocationRequestBody body);
}

