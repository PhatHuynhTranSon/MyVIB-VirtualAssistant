package com.example.myvib_virtual_assistant.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvib_virtual_assistant.R;
import com.example.myvib_virtual_assistant.data.models.Location;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private List<Location> locations;

    public LocationAdapter(List<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.layout_location, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get location
        Location location = locations.get(position);

        //Get resource
        Resources resources = holder.view.getResources();

        //Populate field
        holder.openingText.setText(location.getIsOpen());
        holder.nameText.setText(location.getName());
        holder.addressText.setText(location.getAddress());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Hold
        public TextView nameText;
        public TextView openingText;
        public TextView addressText;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            openingText = view.findViewById(R.id.openingText);
            addressText = view.findViewById(R.id.addressText);
            nameText = view.findViewById(R.id.nameText);
        }
    }

    public void addAll(List<Location> locations) {
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }
}
