package com.example.myvib_virtual_assistant.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.adapter.LocationAdapter;
import com.example.myvib_virtual_assistant.data.models.Location;
import com.example.myvib_virtual_assistant.location.NearestLocationRetriever;
import com.example.myvib_virtual_assistant.location.NearestLocationRetrieverBuilder;
import com.example.myvib_virtual_assistant.location.NearestLocationRetrieverListener;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment implements NearestLocationRetrieverListener {
    //Hold sentence
    String sentence;

    //Recycler view
    RecyclerView locationRecyclerView;
    LocationAdapter locationAdapter;

    //Views
    EditText intentEditText;

    //Location Retriever
    NearestLocationRetriever mLocationRetriever;

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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeEditText();
        initializeRecyclerView();
        initializeLocationRetriever();
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

    private void initializeLocationRetriever() {
        //Create
        mLocationRetriever = NearestLocationRetrieverBuilder.get();

        //Start listening
        mLocationRetriever.getNearestLocations(10.756423906195085, 106.64508293833768, this);
    }

    @Override
    public void onResult(List<Location> results) {
        //Display
        addLocationsToRecyclerView(results);
    }

    private void addLocationsToRecyclerView(List<Location> locations) {
        locationAdapter.addAll(locations);
    }

    @Override
    public void onError(Throwable t) {
        //Display error
        displayError();
    }

    private void displayError() {
        Toast.makeText(getContext(), R.string.can_not_get_nearest, Toast.LENGTH_SHORT).show();
    }
}