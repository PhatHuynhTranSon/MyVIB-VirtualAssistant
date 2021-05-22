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
import com.example.myvib_virtual_assistant.languages.Language;

public class LanguageFragment extends Fragment implements View.OnClickListener {
    //CardViews
    CardView englishSelectionCardView;
    CardView vietnameseSelectionCardView;

    //Language
    Language mLanguages;

    //Navigation
    NavController mNavController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        //Set elements on click
        englishSelectionCardView = view.findViewById(R.id.englishSelection);
        vietnameseSelectionCardView = view.findViewById(R.id.vietnameseSelection);

        //Set on click listeners
        englishSelectionCardView.setOnClickListener(this);
        vietnameseSelectionCardView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLanguages = Language.getInstance(getContext());
        mNavController = Navigation.findNavController(view);
    }

    private void setLanguage(String language) {
        mLanguages.setLanguage(language);
    }

    private void navigateToHomeScreen() {
        mNavController.navigate(R.id.action_languageFragment_to_homeFragment2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.englishSelection:
                setLanguage("en");
                break;
            case R.id.vietnameseSelection:
                setLanguage("vi");
                break;
        }
        navigateToHomeScreen();
    }
}