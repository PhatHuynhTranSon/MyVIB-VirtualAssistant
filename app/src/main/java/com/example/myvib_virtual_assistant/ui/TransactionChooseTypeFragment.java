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

import com.example.myvib_virtual_assistant.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionChooseTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionChooseTypeFragment extends Fragment implements View.OnClickListener {
    //Selection
    CardView existingCardView, newCardView;

    //Nav Controller
    NavController mNavController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_choose_type, container, false);

        //Get option
        existingCardView = view.findViewById(R.id.existingCardView);
        newCardView = view.findViewById(R.id.newCardView);

        //Set listener
        existingCardView.setOnClickListener(this);
        newCardView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.existingCardView) {
            navigateToExistingAccount();
        } else if (v.getId() == R.id.newCardView) {
            navigateToAddAccount();
        }
    }

    private void navigateToExistingAccount() {
        mNavController.navigate(R.id.ChooseToExisting);
    }

    private void navigateToAddAccount() {
        mNavController.navigate(R.id.ChooseToAdd);
    }
}