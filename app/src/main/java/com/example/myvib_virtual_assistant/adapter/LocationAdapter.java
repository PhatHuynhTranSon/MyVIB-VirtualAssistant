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
import com.example.myvib_virtual_assistant.string.StringUtils;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    //Listener
    public interface LocationListener {
        void onClick(Location location);
    }
    LocationListener mListener;

    //Location list
    private List<Location> locations;

    public LocationAdapter(LocationListener listener, List<Location> locations) {
        this.mListener = listener;
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
        String status = resources.getString(mapLocationStatus(location.getIsOpen()));

        //Populate field
        holder.openingText.setText(status);
        holder.nameText.setText(location.getName());
        holder.addressText.setText(location.getAddress());
    }

    private int mapLocationStatus(String status) {
        return StringUtils.mapLocationStatus(status);
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
        public CardView locationCardView;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            openingText = view.findViewById(R.id.openingText);
            addressText = view.findViewById(R.id.addressText);
            nameText = view.findViewById(R.id.nameText);
            locationCardView = view.findViewById(R.id.locationCardView);

            //Set on click listener
            locationCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Get position
                    int position = getAdapterPosition();
                    //Listener
                    if (mListener != null) mListener.onClick(locations.get(position));
                }
            });
        }
    }

    public void addAll(List<Location> locations) {
        this.locations.addAll(locations);
        notifyDataSetChanged();
    }
}
