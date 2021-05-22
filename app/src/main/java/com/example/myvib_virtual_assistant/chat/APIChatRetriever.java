package com.example.myvib_virtual_assistant.chat;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Chat;
import com.example.myvib_virtual_assistant.data.models.Prediction;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class APIChatRetriever implements ChatRetriever, Callback<Chat> {
    BackendAPI mApi;
    ChatRetrieverListener mListener;

    public APIChatRetriever(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void onResponse(Call<Chat> call, Response<Chat> response) {
        if (response.body() != null) mListener.onResult(response.body());
        else mListener.onError(new Exception("Response does not have a body"));
    }

    @Override
    public void onFailure(Call<Chat> call, Throwable t) {
        mListener.onError(t);
    }
}
