package com.example.myvib_virtual_assistant.prediction;

public interface IntentPredictor {
    void predict(String sentence, IntentPredictorListener listener);
}
