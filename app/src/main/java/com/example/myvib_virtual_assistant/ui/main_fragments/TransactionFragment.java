package com.example.myvib_virtual_assistant.ui.main_fragments;

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
import com.example.myvib_virtual_assistant.ui.TransactionFragmentArgs;
import com.example.myvib_virtual_assistant.ui.TransactionFragmentDirections;

public class TransactionFragment extends Fragment implements View.OnKeyListener {
    //Sentence
    String sentence;

    //Text view
    EditText intentEditText;

    //NavController
    NavController mNavController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        //Find intent edit text view
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

        //Get the sentence
        sentence = TransactionFragmentArgs.fromBundle(getArguments()).getSentence();

        //Display sentence
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
        TransactionFragmentDirections.TransactionToPrediction action =
                TransactionFragmentDirections.TransactionToPrediction(getIntentText());
        mNavController.navigate(action);
    }

    private String getIntentText() {
        return intentEditText.getText().toString();
    }
}