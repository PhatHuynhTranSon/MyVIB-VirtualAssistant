package com.example.myvib_virtual_assistant.payment;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Bill;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIBillingRetriever implements BillingRetriever, Callback<Bill> {
    BillingRetrieverListener mListener;
    BackendAPI mApi;

    public APIBillingRetriever(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getBilling(BillingRetrieverListener listener) {
        mListener = listener;
        mApi.getBills().enqueue(this);
    }

    @Override
    public void onResponse(Call<Bill> call, Response<Bill> response) {
        if (response.body() != null) {
            mListener.onResult(response.body());
        } else {
            mListener.onError(new Exception("Response has empty body"));
        }
    }

    @Override
    public void onFailure(Call<Bill> call, Throwable t) {
        mListener.onError(t);
    }
}
