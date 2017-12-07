package com.jzheadley.swifey.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.ui.CheckInActivity;
import com.jzheadley.swifey.ui.RestaurantListActivity;

import java.util.List;

import timber.log.Timber;


public class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RestaurantListActivity activity;
    private List<Restaurant> items;

    public RestaurantListAdapter(RestaurantListActivity activity, List<Restaurant> items) {
        this.activity = activity;
        this.items = items;
        Timber.v("Restaurants Adapter Initialized with restaurants:	%s", items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View row = inflater.inflate(R.layout.restaurant_card, parent, false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item) holder).restaurantName.setText(items.get(position).getRestaurantName());
        // ((Item) holder).restaurantDesc.setText(items.get(position).getRestaurantDescription());

        ((Item) holder).restaurantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CheckInActivity.class);
                intent.putExtra("restaurant", items.get(position));
                activity.startActivity(intent);
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        ImageView restaurantImg;
        TextView restaurantName;
        CardView restaurantCard;

        public Item(View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantCard = itemView.findViewById(R.id.restaurant_card);
        }
    }
}
