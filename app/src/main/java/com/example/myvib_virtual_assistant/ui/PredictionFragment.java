package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.prediction.Intent;
import com.example.myvib_virtual_assistant.prediction.IntentPredictor;
import com.example.myvib_virtual_assistant.prediction.IntentPredictorBuilder;
import com.example.myvib_virtual_assistant.prediction.IntentPredictorListener;

public class PredictionFragment extends Fragment implements IntentPredictorListener {
    IntentPredictor mIntentPredictor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prediction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get the sentence
        String sentence = PredictionFragmentArgs.fromBundle(getArguments()).getSpeech();

        //Create intent predictor
        mIntentPredictor = IntentPredictorBuilder.create(getContext());

        //Call predictor
        mIntentPredictor.predict(sentence, this);
    }

    @Override
    public void onResult(Intent intent) {
        //Display result
        if (intent == Intent.BALANCE) {
            Toast.makeText(getContext(), "BALANCE", Toast.LENGTH_SHORT).show();
        } else if (intent == Intent.BILL) {

        } else if (intent == Intent.CHAT) {

        } else if (intent == Intent.TRANSFER) {

        } else if (intent == Intent.UNKNOWN) {
            finish();
            displayNotRecognized();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        finish();
        displayError();
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.prediction_error, Toast.LENGTH_SHORT).show();
    }

    private void displayNotRecognized() {
        Toast.makeText(getContext(), R.string.can_not_predict, Toast.LENGTH_SHORT).show();
    }

    private void finish() {
        getActivity().onBackPressed();
    }
}