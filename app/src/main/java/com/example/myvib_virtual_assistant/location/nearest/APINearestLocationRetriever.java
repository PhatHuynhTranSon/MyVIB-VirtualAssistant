package com.example.myvib_virtual_assistant.location.nearest;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Locations;
import com.example.myvib_virtual_assistant.data.requests.LocationRequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APINearestLocationRetriever implements NearestLocationRetriever, Callback<Locations> {
    BackendAPI mApi;
    NearestLocationRetrieverListener mListener;

    public APINearestLocationRetriever(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getNearestLocations(double lat, double lon, NearestLocationRetrieverListener listener) {
        mListener = listener;
        mApi.getNearestLocations(new LocationRequestBody(lat, lon)).enqueue(this);
    }


    @Override
    public void onResponse(Call<Locations> call, Response<Locations> response) {
        if (response.body() != null) mListener.onBankLocationsResult(response.body().getLocations());
        else mListener.onBankLocationsError(new Exception("Response body is null"));
    }

    @Override
    public void onFailure(Call<Locations> call, Throwable t) {
        mListener.onBankLocationsError(t);
    }
}
