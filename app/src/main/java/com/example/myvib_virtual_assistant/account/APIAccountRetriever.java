package com.example.myvib_virtual_assistant.account;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Accounts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIAccountRetriever implements AccountRetriever, Callback<Accounts> {
    //API
    BackendAPI mApi;

    //Listener
    AccountRetrieverListener mListener;

    public APIAccountRetriever(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAccounts(AccountRetrieverListener listener) {
        mListener = listener;
        mApi.getAccounts().enqueue(this);
    }

    @Override
    public void onResponse(Call<Accounts> call, Response<Accounts> response) {
        if (response.body() != null) mListener.onResult(response.body().getAccounts());
        else mListener.onError(new Exception("Response does not have a body"));
    }

    @Override
    public void onFailure(Call<Accounts> call, Throwable t) {
        mListener.onError(t);
    }
}
