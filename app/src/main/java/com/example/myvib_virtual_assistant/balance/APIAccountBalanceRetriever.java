package com.example.myvib_virtual_assistant.balance;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Balance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIAccountBalanceRetriever implements AccountBalanceRetriever, Callback<Balance> {
    BackendAPI mApi;
    AccountBalanceListener mListener;

    public APIAccountBalanceRetriever(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getBalance(AccountBalanceListener listener) {
        mListener = listener;
        mApi.getBalance()
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<Balance> call, Response<Balance> response) {
        if (response.body() != null) {
            Balance balance = response.body();
            if (mListener != null) mListener.onResult(balance);
        } else {
            if (mListener != null) mListener.onError(new Exception("Response body is null"));
        }
    }

    @Override
    public void onFailure(Call<Balance> call, Throwable t) {
        if (mListener != null) mListener.onError(t);
    }
}
