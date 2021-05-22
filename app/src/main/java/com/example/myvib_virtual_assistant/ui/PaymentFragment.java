package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myvib_virtual_assistant.R;

public class PaymentFragment extends Fragment {
    //Get sentence from arguments
    String sentence;

    //Intent edit text to display payment
    EditText intentEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        //Intent edit text
        intentEditText = view.findViewById(R.id.intentEditText);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Get sentence
        sentence = PaymentFragmentArgs.fromBundle(getArguments()).getSentence();
        intentEditText.setText(sentence);
    }
}