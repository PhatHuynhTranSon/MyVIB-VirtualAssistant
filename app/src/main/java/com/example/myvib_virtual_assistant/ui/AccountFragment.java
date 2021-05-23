package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.KeyEvent;
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


public class AccountFragment extends Fragment implements AccountBalanceListener, View.OnKeyListener {
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        //Initialize text views and edit text
        balanceText = view.findViewById(R.id.electricityText);
        savingText = view.findViewById(R.id.waterText);
        creditCardText = view.findViewById(R.id.phoneText);
        intentEditText = view.findViewById(R.id.intentEditText);

        //Initialize card view and progress bar
        progressBar = view.findViewById(R.id.balanceProgressBar);
        cardView = view.findViewById(R.id.summaryCard);

        //Set intent edit text on enter
        intentEditText.setOnKeyListener(this);

        //Set the progress bar to Visible and CardView to invisible
        hideCardView();
        showProgress();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Nav controller
        mNavController = Navigation.findNavController(view);

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
        cardView.setVisibility(View.INVISIBLE);
    }

    private void showProgress() { progressBar.setVisibility(View.VISIBLE); }

    private void showCardView() {
        cardView.setVisibility(View.VISIBLE);
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
        AccountFragmentDirections.AccountToPrediction action = AccountFragmentDirections
                .AccountToPrediction(getIntentText());
        mNavController.navigate(action);
    }

    private String getIntentText() {
        return intentEditText.getText().toString();
    }
}