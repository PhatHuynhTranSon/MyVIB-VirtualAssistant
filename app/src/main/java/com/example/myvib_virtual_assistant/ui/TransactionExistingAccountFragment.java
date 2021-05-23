package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.account.AccountRetriever;
import com.example.myvib_virtual_assistant.account.AccountRetrieverBuilder;
import com.example.myvib_virtual_assistant.account.AccountRetrieverListener;
import com.example.myvib_virtual_assistant.adapter.AccountAdapter;
import com.example.myvib_virtual_assistant.balance.AccountBalanceRetrieverBuilder;
import com.example.myvib_virtual_assistant.data.models.Account;

import java.util.ArrayList;
import java.util.List;


public class TransactionExistingAccountFragment extends Fragment implements AccountAdapter.AccountListener, AccountRetrieverListener {
    //Recycler view
    RecyclerView accountRecyclerView;
    AccountAdapter accountAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    //Account retriever
    AccountRetriever mAccountRetriever;

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
        View view = inflater.inflate(R.layout.fragment_transaction_existing_account, container, false);

        //Views
        accountRecyclerView = view.findViewById(R.id.accountRecycler);
        progressBar = view.findViewById(R.id.accountProgressBar);

        //Hide list and show progress bar
        hideList();
        showProgress();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeNavController(view);
        initializeRecyclerView();
        initializeAccounts();
    }

    private void initializeNavController(View view) {
        mNavController = Navigation.findNavController(view);
    }

    private void initializeRecyclerView() {
        //Set linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        accountRecyclerView.setLayoutManager(layoutManager);

        //Set adapter
        accountAdapter = new AccountAdapter(this, new ArrayList<>());
        accountRecyclerView.setAdapter(accountAdapter);
    }

    private void initializeAccounts() {
        mAccountRetriever = AccountRetrieverBuilder.get();
        mAccountRetriever.getAccounts(this);
    }

    @Override
    public void onClick(Account account) {
        navigateToAdd(account);
    }

    public void navigateToAdd(Account account) {
        //Navigate to add account fragment
        TransactionExistingAccountFragmentDirections.ExistingToAdd action =
                TransactionExistingAccountFragmentDirections.ExistingToAdd();
        action.setAccountId(account.getAccountId());
        action.setBank(account.getBank());

        mNavController.navigate(action);
    }

    @Override
    public void onResult(List<Account> accounts) {
        addAccountsToList(accounts);
        hideProgress();
        showList();
    }

    @Override
    public void onError(Throwable t) {
        displayError();
    }

    private void addAccountsToList(List<Account> accounts) {
        accountAdapter.addAll(accounts);
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.can_not_account, Toast.LENGTH_SHORT).show();
    }

    private void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void hideList() {
        accountRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showProgress() { progressBar.setVisibility(View.VISIBLE); }

    private void showList() {
        accountRecyclerView.setVisibility(View.VISIBLE);
    }
}