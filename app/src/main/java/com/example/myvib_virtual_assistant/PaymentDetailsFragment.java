package com.example.myvib_virtual_assistant;

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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.data.models.Bill;
import com.example.myvib_virtual_assistant.payment.BillingRetriever;
import com.example.myvib_virtual_assistant.payment.BillingRetrieverCreator;
import com.example.myvib_virtual_assistant.payment.BillingRetrieverListener;
import com.example.myvib_virtual_assistant.string.StringUtils;

public class PaymentDetailsFragment extends Fragment implements BillingRetrieverListener, View.OnClickListener {
    //Retriever
    BillingRetriever mBillingRetriever;

    //Navigation
    NavController mNavController;

    //Cache the fields
    private TextView electricityText, waterText, phoneText;
    private Button confirmButton;

    //Progress bar
    ProgressBar paymentProgressBar;

    //CardView
    CardView paymentCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_details, container, false);

        //Find views
        electricityText = view.findViewById(R.id.electricityText);
        waterText = view.findViewById(R.id.waterText);
        phoneText = view.findViewById(R.id.phoneText);
        confirmButton = view.findViewById(R.id.paymentConfirmButton);
        paymentProgressBar = view.findViewById(R.id.paymentProgressBar);
        paymentCardView = view.findViewById(R.id.paymentCardView);

        //Hide CardView and Show progress
        hideCardView();
        showProgress();

        //Set button on click
        confirmButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Retrieve data
        mBillingRetriever = BillingRetrieverCreator.get();
        mBillingRetriever.getBilling(this);

        //Navigation
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onResult(Bill bill) {
        displayBills(bill);
        hideProgress();
        showCardView();
    }

    @Override
    public void onError(Throwable t) {
        displayError();
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.can_not_payment, Toast.LENGTH_SHORT).show();
    }

    private void hideProgress() {
        paymentProgressBar.setVisibility(View.INVISIBLE);
    }

    private void showProgress() {
        paymentProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideCardView() {
        paymentCardView.setVisibility(View.INVISIBLE);
    }

    private void showCardView() {
        paymentCardView.setVisibility(View.VISIBLE);
    }

    private void displayBills(Bill bill) {
        electricityText.setText(StringUtils.formatCurrency(bill.getElectricity()));
        waterText.setText(StringUtils.formatCurrency(bill.getWater()));
        phoneText.setText(StringUtils.formatCurrency(bill.getPhone()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.paymentConfirmButton) {
            navigateToPaymentSuccess();
        }
    }

    private void navigateToPaymentSuccess() {
        mNavController.navigate(R.id.PaymentDetailsToSuccess);
    }
}