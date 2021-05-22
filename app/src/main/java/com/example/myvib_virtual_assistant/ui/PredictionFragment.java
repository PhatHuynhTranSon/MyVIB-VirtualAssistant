package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

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
    //Intent predictor
    IntentPredictor mIntentPredictor;

    //Sentence as argument
    String sentence;

    //NavController
    NavController mNavController;

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

        //Init nav controller
        mNavController = Navigation.findNavController(view);

        //Get the sentence
        sentence = PredictionFragmentArgs.fromBundle(getArguments()).getSpeech();

        //Create intent predictor
        mIntentPredictor = IntentPredictorBuilder.create(getContext());

        //Call predictor
        mIntentPredictor.predict(sentence, this);
    }

    @Override
    public void onResult(Intent intent) {
        //Display result
        if (intent == Intent.BALANCE) {
            navigateToAccount();
        } else if (intent == Intent.BILL) {
            navigateToBilling();
        } else if (intent == Intent.CHAT) {
            navigateToChat();
        } else if (intent == Intent.TRANSFER) {
            navigateToTransfer();
        } else if (intent == Intent.UNKNOWN) {
            finish();
            displayNotRecognized();
        }
    }

    private void navigateToAccount() {
        PredictionFragmentDirections.PredictionToAccount action = PredictionFragmentDirections.PredictionToAccount(sentence);
        mNavController.navigate(action);
    }

    private void navigateToBilling() {
        PredictionFragmentDirections.PredictionToPayment action = PredictionFragmentDirections.PredictionToPayment(sentence);
        mNavController.navigate(action);
    }

    private void navigateToChat() {
        NavDirections action = PredictionFragmentDirections.PredictionToCustomerService();
        mNavController.navigate(action);
    }

    private void navigateToTransfer() {
        PredictionFragmentDirections.PredictionToTransaction action = PredictionFragmentDirections.PredictionToTransaction(sentence);
        mNavController.navigate(action);
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