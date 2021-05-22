package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.balance.AccountBalanceListener;
import com.example.myvib_virtual_assistant.balance.AccountBalanceRetriever;
import com.example.myvib_virtual_assistant.balance.AccountBalanceRetrieverBuilder;
import com.example.myvib_virtual_assistant.data.models.Balance;
import com.example.myvib_virtual_assistant.string.StringUtils;


public class AccountFragment extends Fragment implements AccountBalanceListener {
    //Arguments
    String sentence;

    //Account retriever
    AccountBalanceRetriever mAccountRetriever;

    //TextViews and Edit Text
    EditText intentEditText;
    TextView balanceText, savingText, creditCardText;

    //Progress bar
    ProgressBar progressBar;

    //CardView
    CardView cardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        //Initialize text views and edit text
        balanceText = view.findViewById(R.id.electricityText);
        savingText = view.findViewById(R.id.waterText);
        creditCardText = view.findViewById(R.id.phoneText);
        intentEditText = view.findViewById(R.id.intentEditText);

        //Initialize card view and progress bar
        progressBar = view.findViewById(R.id.balanceProgressBar);
        cardView = view.findViewById(R.id.summaryCard);

        //Set the progress bar to Visible and CardView to invisible
        hideCardView();
        showProgress();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Account retriever
        mAccountRetriever = AccountBalanceRetrieverBuilder.create();

        //Retrieve
        mAccountRetriever.getBalance(this);

        //Get the sentence and display
        sentence = AccountFragmentArgs.fromBundle(getArguments()).getSentence();
        intentEditText.setText(sentence);
    }

    @Override
    public void onResult(Balance balance) {
        populateFields(balance);
        hideProgress();
        showCardView();
    }

    @Override
    public void onError(Throwable throwable) {
        finish();
        displayError();
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.can_not_account, Toast.LENGTH_SHORT).show();
    }

    private void finish() {
        getActivity().onBackPressed();
    }

    private void populateFields(Balance balance) {
        balanceText.setText(StringUtils.formatCurrency(balance.getBalance()));
        savingText.setText(StringUtils.formatCurrency(balance.getSaving()));
        creditCardText.setText(StringUtils.formatCurrency(balance.getCredit()));
    }

    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void hideCardView() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showProgress() {
        cardView.setVisibility(View.INVISIBLE);
    }

    private void showCardView() {
        cardView.setVisibility(View.VISIBLE);
    }
}