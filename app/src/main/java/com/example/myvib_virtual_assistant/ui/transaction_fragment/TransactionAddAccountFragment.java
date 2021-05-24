package com.example.myvib_virtual_assistant.ui.transaction_fragment;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizer;
import com.example.myvib_virtual_assistant.speech.MySpeechRecognizerListener;
import com.example.myvib_virtual_assistant.speech.SpeechRecognizerBuilder;
import com.example.myvib_virtual_assistant.string.StringUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionAddAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionAddAccountFragment extends Fragment implements View.OnClickListener {
    //Views
    //Edit text and button
    EditText editBank, editAccount, editAmount;
    ImageView accountSpeechImage, bankSpeechImage;
    Button confirmButton;

    //Label
    TextView accountLabel, bankLabel;

    //Nav controller
    NavController mNavController;

    //Speech recognizer
    MySpeechRecognizer mSpeechRecognizer;
    MySpeechRecognizerListener mAccountSpeechListener;
    MySpeechRecognizerListener mBankSpeechListener;

    //Hold account string and bank string
    String account, bank;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_add_account, container, false);

        //Initialize views
        editBank = view.findViewById(R.id.editTextBank);
        editAccount = view.findViewById(R.id.editTextAccount);
        editAmount = view.findViewById(R.id.editAmount);
        accountSpeechImage = view.findViewById(R.id.accountSpeechButton);
        bankSpeechImage = view.findViewById(R.id.bankSpeechButton);
        confirmButton = view.findViewById(R.id.confirmButton);
        accountLabel = view.findViewById(R.id.accountLabel);
        bankLabel = view.findViewById(R.id.bankLabel);

        //Initialize listeners
        initializeListeners();

        //Set event
        accountSpeechImage.setOnClickListener(this);
        bankSpeechImage.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //NavController
        mNavController = Navigation.findNavController(view);

        //Speech recognizer
        mSpeechRecognizer = SpeechRecognizerBuilder.get(getContext());

        //Strings
        account = TransactionAddAccountFragmentArgs.fromBundle(getArguments()).getAccountId();
        bank = TransactionAddAccountFragmentArgs.fromBundle(getArguments()).getBank();

        //Display account and bank
        setAccount(account);
        setBank(bank);
    }

    private void initializeListeners() {
        //Account listener
        mAccountSpeechListener = new MySpeechRecognizerListener() {
            @Override
            public void onReady() {
                setAccountSpeechReady();
            }

            @Override
            public void onEnd() {
                setAccountSpeechEnd();
            }

            @Override
            public void onResult(String result) {
                setAccount(result);
            }

            @Override
            public void onError(Throwable throwable) {
                displayError();
            }
        };

        //Bank listener
        mBankSpeechListener = new MySpeechRecognizerListener() {
            @Override
            public void onReady() {
                setBankSpeechReady();
            }

            @Override
            public void onEnd() {
                setBankSpeechEnd();
            }

            @Override
            public void onResult(String result) {
                setBank(result);
            }

            @Override
            public void onError(Throwable throwable) {
                displayError();
            }
        };
    }

    private void setAccountSpeechReady() {
        accountLabel.setText(R.string.say_account);
    }

    private void setBankSpeechReady() {
        bankLabel.setText(R.string.say_bank);
    }

    private void setAccountSpeechEnd() {
        accountLabel.setText(R.string.select_an_account);
    }

    private void setBankSpeechEnd() {
        bankLabel.setText(R.string.select_a_bank);
    }

    private void setAccount(String account) {
        editAccount.setText(StringUtils.formatAccount(account));
    }

    private void setBank(String bank) {
        editBank.setText(bank);
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.can_not_speech, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmButton:
                navigateToConfirm();
                break;
            case R.id.accountSpeechButton:
                startListeningForAccount();
                break;
            case R.id.bankSpeechButton:
                startListeningForBank();
                break;
        }
    }

    private void navigateToConfirm() {
        TransactionAddAccountFragmentDirections.AddToConfirm action = TransactionAddAccountFragmentDirections
                .AddToConfirm(getAccountText(), getBankText(), getAmountText());
        mNavController.navigate(action);
    }

    private void startListeningForAccount() {
        mSpeechRecognizer.startListening(mAccountSpeechListener);
    }

    private void startListeningForBank() {
        mSpeechRecognizer.startListening(mBankSpeechListener);
    }

    private String getAccountText() {
        return editAccount.getText().toString();
    }

    private String getBankText() {
        return editBank.getText().toString();
    }

    private String getAmountText() {
        return editAmount.getText().toString();
    }
}