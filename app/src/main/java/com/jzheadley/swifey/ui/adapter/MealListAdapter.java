package com.jzheadley.swifey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.Meal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Meal> meals;

    private HashSet<Meal> selectedMeals;

    public MealListAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
        this.selectedMeals = new HashSet<>();
    }

    public ArrayList<Meal> getSelectedMeals() {
        return new ArrayList<>(selectedMeals);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.meal_card, parent, false);

        return new Item(row);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item) holder).mealName.setText(meals.get(position).getMealName());
        ((Item) holder).mealDesc.setText(meals.get(position).getMealDesc());
        ((Item) holder).mealCost.setText(meals.get(position).getMealCost() + " Swipes");
        ((Item) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    selectedMeals.add(meals.get(position));
                } else {
                    selectedMeals.remove(meals.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        TextView mealName;
        TextView mealCost;
        TextView mealDesc;
        CardView mealCard;
        CheckBox checkBox;

        public Item(View itemView) {
            super(itemView);
            //  mealIMG = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            mealCost = itemView.findViewById(R.id.meal_cost);
            mealDesc = itemView.findViewById(R.id.meal_desc);
            mealCard = itemView.findViewById(R.id.meal_card);
            checkBox = itemView.findViewById(R.id.meal_select_cb);
        }
    }
}
