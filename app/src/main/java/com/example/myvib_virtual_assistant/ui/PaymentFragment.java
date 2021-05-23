package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myvib_virtual_assistant.R;

public class PaymentFragment extends Fragment implements View.OnKeyListener {
    //Get sentence from arguments
    String sentence;

    //Intent edit text to display payment
    EditText intentEditText;

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
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        //Intent edit text
        intentEditText = view.findViewById(R.id.intentEditText);

        //Set intent edit text on enter
        intentEditText.setOnKeyListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Nav controller
        mNavController = Navigation.findNavController(view);

        //Get sentence
        sentence = PaymentFragmentArgs.fromBundle(getArguments()).getSentence();
        intentEditText.setText(sentence);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //Check if right component
        if (v.getId() == R.id.intentEditText) {
            //Check if Enter is click
            if ((event.getAction() == KeyEvent.ACTION_DOWN)
                    && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                //Navigate to prediction fragment
                navigateToPrediction();
            }
        }
        return false;
    }

    private void navigateToPrediction() {
        PaymentFragmentDirections.BillingToPrediction action =
                PaymentFragmentDirections.BillingToPrediction(getIntentText());
        mNavController.navigate(action);
    }

    private String getIntentText() {
        return intentEditText.getText().toString();
    }
}