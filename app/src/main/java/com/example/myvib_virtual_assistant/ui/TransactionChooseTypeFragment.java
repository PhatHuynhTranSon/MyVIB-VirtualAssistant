package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvib_virtual_assistant.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionChooseTypeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionChooseTypeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_choose_type, container, false);
    }
}