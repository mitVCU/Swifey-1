package com.jzheadley.swifey.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzheadley.swifey.MealActivity;
import com.jzheadley.swifey.Models.Restaurant;
import com.jzheadley.swifey.R;

import java.util.ArrayList;

/**
 * Created by mit on 11/20/17.
 */

public class RestaurantListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Restaurant> items;
    public RestaurantListAdapter(Context context, ArrayList<Restaurant> items){
        this.context = context;
        this.items = items;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.restaurant_card,parent,false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).restaurantName.setText(items.get(position).getRestaurantName());
        ((Item)holder).restaurantDesc.setText(items.get(position).getRestaurantDesc());

        ((Item)holder).restaurantCard.setOnClickListener(new View.OnClickListener(){
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

    public class Item extends RecyclerView.ViewHolder{
        ImageView restaurantIMG;
        TextView restaurantName;
        TextView restaurantDesc;
        CardView restaurantCard;
        public Item(View itemView) {
            super(itemView);
            restaurantIMG = itemView.findViewById(R.id.restaurant_image);
            restaurantName=itemView.findViewById(R.id.restaurant_name);
            restaurantDesc = itemView.findViewById(R.id.restaurant_description);
            restaurantCard = itemView.findViewById(R.id.restaurant_card);
        }
    }
}
