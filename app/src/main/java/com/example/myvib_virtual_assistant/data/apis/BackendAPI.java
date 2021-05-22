package com.example.myvib_virtual_assistant.data.apis;

import com.example.myvib_virtual_assistant.data.models.Balance;
import com.example.myvib_virtual_assistant.data.models.Bill;
import com.example.myvib_virtual_assistant.data.models.Chat;
import com.example.myvib_virtual_assistant.data.models.Prediction;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BackendAPI {
    @POST("/predict/en")
    Call<Prediction> getEngPrediction(@Body PredictionRequestBody requestBody);

    @POST("/predict/vi")
    Call<Prediction> getViPrediction(@Body PredictionRequestBody requestBody);

    @POST("/chat/en")
    Call<Chat> getEngChat(@Body PredictionRequestBody requestBody);

    @POST("/chat/vi")
    Call<Chat> getViChat(@Body PredictionRequestBody requestBody);

    @GET("/balance")
    Call<Balance> getBalance();

    @GET("/bills")
    Call<Bill> getBills();
}
