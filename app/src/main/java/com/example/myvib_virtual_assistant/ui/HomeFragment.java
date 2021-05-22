package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myvib_virtual_assistant.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    //Save image view
    ImageView speechImage;

    //Save the cards
    CardView accountCard;
    CardView transactionCard;
    CardView billingCard;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Initialize the views
        speechImage = view.findViewById(R.id.speechImage);
        accountCard = view.findViewById(R.id.accountCard);
        transactionCard = view.findViewById(R.id.transactionCard);
        billingCard = view.findViewById(R.id.billingCard);

        //Set on listener
        speechImage.setOnClickListener(this);
        accountCard.setOnClickListener(this);
        transactionCard.setOnClickListener(this);
        billingCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Nav controller
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.speechImage:
                navigateToSpeechToText();
                break;
            case R.id.accountCard:
                navigateToAccount();
                break;
            case R.id.transactionCard:
                navigateToTransaction();
                break;
            case R.id.billingCard:
                navigateToBilling();
                break;
        }
    }

    private void navigateToSpeechToText() {
        mNavController.navigate(R.id.action_homeFragment2_to_speechToTextFragment);
    }

    private void navigateToAccount() {
        mNavController.navigate(R.id.action_homeFragment2_to_accountFragment2);
    }

    private void navigateToTransaction() {
        mNavController.navigate(R.id.action_homeFragment2_to_transactionFragment);
    }

    private void navigateToBilling() {
        mNavController.navigate(R.id.action_homeFragment2_to_paymentFragment);
    }
}