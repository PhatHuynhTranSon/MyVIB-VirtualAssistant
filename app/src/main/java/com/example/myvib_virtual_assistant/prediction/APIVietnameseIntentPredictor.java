package com.example.myvib_virtual_assistant.prediction;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

public class APIVietnameseIntentPredictor extends APIIntentPredictor {
    public APIVietnameseIntentPredictor(BackendAPI mApi) {
        super(mApi);
    }

    @Override
    public void predict(String sentence, IntentPredictorListener listener) {
        mListener = listener;
        mApi.getViPrediction(new PredictionRequestBody(sentence))
                .enqueue(this);
    }
}
