package com.example.myvib_virtual_assistant.ui.main_fragments;

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
import com.example.myvib_virtual_assistant.languages.Language;
import com.example.myvib_virtual_assistant.ui.HomeFragmentDirections;

public class HomeFragment extends Fragment implements View.OnClickListener {
    //Save image view
    ImageView speechImage;

    //Save the cards
    CardView accountCard;
    CardView transactionCard;
    CardView billingCard;

    //NavController
    NavController mNavController;

    //Language
    String language;

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

        //Get language
        language = Language.getInstance(getContext()).getLanguage();
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
        HomeFragmentDirections.HomeToAccount action = HomeFragmentDirections.HomeToAccount(getAccountSentence());
        mNavController.navigate(action);
    }

    private void navigateToTransaction() {
        HomeFragmentDirections.HomeToTransaction action = HomeFragmentDirections.HomeToTransaction(getTransactionSentence());
        mNavController.navigate(action);
    }

    private void navigateToBilling() {
        HomeFragmentDirections.HomeToPayment action = HomeFragmentDirections.HomeToPayment(getBillingSentence());
        mNavController.navigate(action);
    }

    private String getAccountSentence() {
        return getString(R.string.account);
    }

    private String getTransactionSentence() {
        return getString(R.string.transfer);
    }

    private String getBillingSentence() {
        return getString(R.string.billing);
    }
}