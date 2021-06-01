package com.example.myvib_virtual_assistant.ui.onboarding_fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.ui.page_transformer.ZoomOutPageTransformer;

public class OnBoardingMainFragment extends Fragment implements View.OnClickListener {
    //Check off complete on boarding
    public static final String COMPLETE_ON_BOARDING_PREF = "ON_BOARDING_PREF";

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;

    //Nav controller
    NavController mNavController;

    //Finish button
    Button finishButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_boarding_main, container, false);

        //Initialize button
        initializeFinishButton(view);

        //Initialize view page
        initializeViewPager(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialize nav controller
        initializeNavController(view);
    }

    private void initializeFinishButton(View view) {
        finishButton = view.findViewById(R.id.on_boarding_finish_button);
        finishButton.setOnClickListener(this);
    }

    private void initializeNavController(View view) {
        mNavController = Navigation.findNavController(view);
    }

    private void initializeViewPager(View view) {
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = view.findViewById(R.id.on_boarding_viewpager);

        //Set zoom page transformer
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View v) {
        //setCompletedOnBoarding();
        navigateToLanguage();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new OnBoardingSymbolFragment();
                case 1:
                    return new OnBoardingBalanceFragment();
                case 2:
                    return new OnBoardingPaymentFragment();
                case 3:
                    return new OnBoardingTransferFragment();
                case 4:
                    return new OnBoardingLocationFragment();
                case 5:
                    return new OnBoardingCustomerFragment();
                default:
                    throw new IndexOutOfBoundsException("No matching fragment");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    private void setCompletedOnBoarding() {
        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        sharedPreferencesEditor.putBoolean(
                COMPLETE_ON_BOARDING_PREF, true);
        sharedPreferencesEditor.apply();
    }

    private void navigateToLanguage() {
        mNavController.navigate(R.id.OnBoardingToLanguage);
    }
}