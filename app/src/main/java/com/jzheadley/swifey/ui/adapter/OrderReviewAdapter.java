package com.jzheadley.swifey.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.ui.OrderReviewActivity;

import java.util.List;

public class OrderReviewAdapter extends RecyclerView.Adapter<OrderReviewAdapter.ViewHolder> {
    private OrderReviewActivity activity;
    private List<Meal> meals;

    public OrderReviewAdapter(OrderReviewActivity activity, List<Meal> meals) {
        this.activity = activity;
        this.meals = meals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewTag = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.order_meal_card, parent, false);
        return new ViewHolder(viewTag);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        holder.mealCost.setText(String.format(activity.getResources().getQuantityString(R.plurals.order_cost_tv, meal.getMealCost()), meal.getMealCost()));
        holder.mealDescription.setText(meal.getMealDesc());
        holder.mealName.setText(meal.getMealName());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mealName;
        TextView mealCost;
        TextView mealDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mealName = itemView.findViewById(R.id.meal_name);
            mealCost = itemView.findViewById(R.id.meal_cost);
            mealDescription = itemView.findViewById(R.id.meal_desc);
        }
    }
}
