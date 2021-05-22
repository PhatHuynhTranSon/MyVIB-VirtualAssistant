package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvib_virtual_assistant.R;

public class SplashFragment extends Fragment {
    //Seconds to change to home screen
    private static final int INTERVAL = 3000;

    //Hold instance of NavController
    private NavController mNavController;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
        changeToLanguageScreen();
    }

    public void changeToLanguageScreen() {
        //Create handler and runnable
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mNavController.navigate(R.id.action_splashFragment_to_languageFragment);
            }
        };

        //Run handler
        handler.postDelayed(runnable, INTERVAL);
    }
}