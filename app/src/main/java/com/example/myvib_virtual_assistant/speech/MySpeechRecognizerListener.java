package com.example.myvib_virtual_assistant.speech;

public interface MySpeechRecognizerListener {
    void onReady();
    void onEnd();
    void onResult(String result);
    void onError(Throwable throwable);
}
