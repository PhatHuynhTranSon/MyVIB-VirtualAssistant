package com.example.myvib_virtual_assistant.speech;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.List;

public class MyNativeSpeechRecognizer implements MySpeechRecognizer, RecognitionListener {
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;

    //Listener
    MySpeechRecognizerListener listener;

    public MyNativeSpeechRecognizer(Context context, String language) {
        //Create and initialize speech recognizer and its intent
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);

        //Set listener
        mSpeechRecognizer.setRecognitionListener(this);
    }

    @Override
    public void startListening(MySpeechRecognizerListener listener) {
        this.listener = listener;
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        if (this.listener != null) this.listener.onReady();
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        if (this.listener != null) this.listener.onEnd();
    }

    @Override
    public void onError(int error) {
        if (this.listener != null) this.listener.onError(new Exception("Error code: " + error));
    }

    @Override
    public void onResults(Bundle results) {
        //Get only the first result
        //Get result
        List<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        //Display to Toast
        if (matches != null && matches.size() > 0) {
            //Get first match
            String firstMatch = matches.get(0);

            //Call listener
            if (this.listener != null) this.listener.onResult(firstMatch);
        }
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
