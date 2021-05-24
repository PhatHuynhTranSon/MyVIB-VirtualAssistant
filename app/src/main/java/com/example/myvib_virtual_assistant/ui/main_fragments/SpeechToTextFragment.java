package com.example.myvib_virtual_assistant.ui.main_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizer;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizerListener;
import com.example.myvib_virtual_assistant.speech.SpeechRecognizerBuilder;
import com.example.myvib_virtual_assistant.ui.SpeechToTextFragmentDirections;

public class SpeechToTextFragment extends Fragment implements View.OnClickListener, MySpeechRecognizerListener {
    //Speech listener
    MySpeechRecognizer mSpeechRecognizer;

    //Text for displaying messages
    TextView speechPrompt, speechContent;

    //ImageView to start recognition
    ImageView speechStartImage;

    //Navigation
    NavController mNavController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_speech_to_text, container, false);

        //Initialize view
        speechPrompt = view.findViewById(R.id.speechPrompt);
        speechContent = view.findViewById(R.id.speechContent);
        speechStartImage = view.findViewById(R.id.speechStartImage);

        //Set on click
        speechStartImage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSpeechRecognizer = SpeechRecognizerBuilder.get(getContext());
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        startRecognition();
    }

    public void startRecognition() {
        mSpeechRecognizer.startListening(this);
    }

    public void updateSpeechContent(String content) {
        speechContent.setText(content);
    }

    public void changeSpeechPromptToListening() {
        speechPrompt.setText(R.string.speech_prompt);
    }

    public void changeSpeechPromptToStart() {
        speechPrompt.setText(R.string.speech_guide);
    }

    public void navigateToPrediction(String speech) {
        SpeechToTextFragmentDirections.PassSpeech action = SpeechToTextFragmentDirections.passSpeech(speech);
        mNavController.navigate(action);
    }

    public void displayError() {
        Toast.makeText(getContext(), R.string.speech_error ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReady() {
        changeSpeechPromptToListening();
    }

    @Override
    public void onEnd() {
        changeSpeechPromptToStart();
    }

    @Override
    public void onResult(String result) {
        updateSpeechContent(result);
        navigateToPrediction(result);
    }

    @Override
    public void onError(Throwable throwable) {
        displayError();
    }
}