package com.example.myvib_virtual_assistant.prediction;

public interface IntentPredictorListener {
    void onResult(Intent intent);
    void onError(Throwable throwable);
}
