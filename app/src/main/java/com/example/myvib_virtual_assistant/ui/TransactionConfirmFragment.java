package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.string.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionConfirmFragment extends Fragment implements View.OnClickListener {
    //Strings
    String account, bank, amount;

    //Views
    Button confirmButton;
    TextView accountText, amountText;

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
        View view = inflater.inflate(R.layout.fragment_transaction_confirm, container, false);

        //Views
        confirmButton = view.findViewById(R.id.confirmButton);
        accountText = view.findViewById(R.id.accountText);
        amountText = view.findViewById(R.id.amountText);

        //Set on click listener
        confirmButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //NavController
        mNavController = Navigation.findNavController(view);

        //Get the text
        account = TransactionConfirmFragmentArgs.fromBundle(getArguments()).getAccountId();
        bank = TransactionConfirmFragmentArgs.fromBundle(getArguments()).getBank();
        amount = TransactionConfirmFragmentArgs.fromBundle(getArguments()).getAmount();

        //Set the text
        setAmount(amount);
        setAccount(account, bank);
    }

    private void setAmount(String amount) {
        amountText.setText(StringUtils.formatCurrency(amount));
    }

    private void setAccount(String account, String bank) {
        accountText.setText(StringUtils.formatBankAndAccount(bank, account));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirmButton) {
            navigateToSuccess();
        }
    }

    private void navigateToSuccess() {
        mNavController.navigate(R.id.ConfirmToSuccess);
    }
}