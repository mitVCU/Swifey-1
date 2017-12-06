package com.jzheadley.swifey.ui.adapter;

import android.content.Context;
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
import com.jzheadley.swifey.ui.MealActivity;

import java.util.List;

import timber.log.Timber;


public class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Restaurant> items;

    public RestaurantListAdapter(Context context, List<Restaurant> items) {
        this.context = context;
        this.items = items;
        Timber.v("Restaurants Adapter Initialized with restaurants:	%s", items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.restaurant_card, parent, false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Item) holder).restaurantName.setText(items.get(position).getRestaurantName());
        // ((Item) holder).restaurantDesc.setText(items.get(position).getRestaurantDescription());

        ((Item) holder).restaurantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MealActivity.class);
                context.startActivity(intent);
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
        TextView restaurantDesc;
        CardView restaurantCard;

        public Item(View itemView) {
            super(itemView);
            restaurantImg = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantDesc = itemView.findViewById(R.id.restaurant_description);
            restaurantCard = itemView.findViewById(R.id.restaurant_card);
        }
    }
}
