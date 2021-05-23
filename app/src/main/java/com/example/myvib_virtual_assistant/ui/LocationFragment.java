package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.adapter.LocationAdapter;
import com.example.myvib_virtual_assistant.data.models.Location;
import com.example.myvib_virtual_assistant.location.device.Coordinate;
import com.example.myvib_virtual_assistant.location.device.DeviceLocationRetriever;
import com.example.myvib_virtual_assistant.location.device.DeviceLocationRetrieverBuilder;
import com.example.myvib_virtual_assistant.location.device.DeviceLocationRetrieverListener;
import com.example.myvib_virtual_assistant.location.nearest.NearestLocationRetriever;
import com.example.myvib_virtual_assistant.location.nearest.NearestLocationRetrieverBuilder;
import com.example.myvib_virtual_assistant.location.nearest.NearestLocationRetrieverListener;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment implements NearestLocationRetrieverListener, DeviceLocationRetrieverListener {
    //Hold sentence
    String sentence;

    //Recycler view
    RecyclerView locationRecyclerView;
    LocationAdapter locationAdapter;

    //Progress bar
    ProgressBar progressBar;

    //Views
    EditText intentEditText;

    //Bank Location Retriever
    NearestLocationRetriever mBankLocationRetriever;

    //Device location retriever
    DeviceLocationRetriever mDeviceLocationRetriever;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location, container, false);

        //Populate test
        intentEditText = view.findViewById(R.id.intentEditText);
        locationRecyclerView = view.findViewById(R.id.locationRecyclerView);
        progressBar = view.findViewById(R.id.locationProgressBar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Show loading bar
        showProgress();
        hideRecyclerView();

        initializeEditText();
        initializeRecyclerView();
        initializeBankLocationRetriever();
        initializeDeviceLocationRetriever();
    }

    private void initializeEditText() {
        sentence = LocationFragmentArgs.fromBundle(getArguments()).getSentence();
        setIntentEditText(sentence);
    }

    private void setIntentEditText(String sentence) {
        intentEditText.setText(sentence);
    }

    private void initializeRecyclerView() {
        //Create empty array list and adapter
        List<Location> initialLocations = new ArrayList<>();
        locationAdapter = new LocationAdapter(initialLocations);

        //Set up layout manager
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set adapter
        locationRecyclerView.setAdapter(locationAdapter);
    }

    private void initializeBankLocationRetriever() {
        //Create
        mBankLocationRetriever = NearestLocationRetrieverBuilder.get();
    }

    private void initializeDeviceLocationRetriever() {
        //Create
        mDeviceLocationRetriever = DeviceLocationRetrieverBuilder.getFused(getContext());
        //Get location
        mDeviceLocationRetriever.getLocation(this);
    }

    @Override
    public void onBankLocationsResult(List<Location> results) {
        //Log
        Log.i("ADDDDD", String.valueOf(results.size()));

        //Hide loading bar
        showRecyclerView();
        hideProgress();

        //Display
        addLocationsToRecyclerView(results);
    }

    private void addLocationsToRecyclerView(List<Location> locations) {
        locationAdapter.addAll(locations);
    }

    @Override
    public void onBankLocationsError(Throwable t) {
        //Display error
        displayBankLocationsError();
    }

    private void displayBankLocationsError() {
        Toast.makeText(getContext(), R.string.can_not_get_nearest, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeviceLocationResult(Coordinate coordinate) {
        //DISPLAY
        Log.i("LOC", String.valueOf(coordinate.getLat()));

        //Get latitude, longitude and call bank locations
        mBankLocationRetriever.getNearestLocations(
                coordinate.getLat(),
                coordinate.getLon(),
                this
        );
    }

    @Override
    public void onDeviceLocationError(Throwable t) {
        displayDeviceLocationError();
    }

    private void displayDeviceLocationError() {
        Toast.makeText(getContext(), R.string.can_not_get_location, Toast.LENGTH_SHORT).show();
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideRecyclerView() {
        locationRecyclerView.setVisibility(View.INVISIBLE);
    }

    public void showRecyclerView() {
        locationRecyclerView.setVisibility(View.VISIBLE);
    }
}