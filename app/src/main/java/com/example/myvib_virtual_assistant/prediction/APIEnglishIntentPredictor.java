package com.example.myvib_virtual_assistant.prediction;

import com.example.myvib_virtual_assistant.data.apis.BackendAPI;
import com.example.myvib_virtual_assistant.data.requests.PredictionRequestBody;

public class APIEnglishIntentPredictor extends APIIntentPredictor {
    public APIEnglishIntentPredictor(BackendAPI mApi) {
        super(mApi);
    }

    @Override
    public void predict(String sentence, IntentPredictorListener listener) {
        mListener = listener;
        mApi.getEngPrediction(new PredictionRequestBody(sentence))
                .enqueue(this);
    }
}
