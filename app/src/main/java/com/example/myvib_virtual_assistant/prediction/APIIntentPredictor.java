package com.example.myvib_virtual_assistant.prediction;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.models.Prediction;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class APIIntentPredictor implements IntentPredictor, Callback<Prediction> {
    BackendAPI mApi;
    IntentPredictorListener mListener;

    public APIIntentPredictor(BackendAPI mApi) {
        this.mApi = mApi;
    }

    @Override
    public void onResponse(Call<Prediction> call, Response<Prediction> response) {
        if (response.body() != null) {
            //Get intent
            Prediction prediction = response.body();
            Intent intent = IntentMapper.mapIntent(prediction.getPrediction());

            //Call listener
            mListener.onResult(intent);
        } else {
            mListener.onError(new Exception("Response body is null"));
        }
    }

    @Override
    public void onFailure(Call<Prediction> call, Throwable t) {
        mListener.onError(t);
    }
}
