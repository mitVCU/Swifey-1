package com.jzheadley.swifey.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.Order;
import com.jzheadley.swifey.ui.OrdersActivity;

import java.util.List;

import timber.log.Timber;

/**
 * Created by mit on 12/8/17.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Item> {
    private OrdersActivity activity;
    private List<Order> items;
    private OrderReviewAdapter mealsOrdered;
    private LinearLayoutManager layoutManager;

    public OrdersAdapter(OrdersActivity activity, List<Order> items) {
        this.activity = activity;
        this.items = items;
        Timber.v("Order Adapter Initialized with restaurants:	%s", items);

    }

    @Override
    public Item onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View row = inflater.inflate(R.layout.order_card, parent, false);

        return new Item(row);
    }

    @Override
    public void onBindViewHolder(Item holder, int position) {
        Order order = items.get(position);
        holder.orderName.setText((CharSequence) order.getOrderDate());
        layoutManager = new LinearLayoutManager(activity);
        holder.rView.setLayoutManager(layoutManager);
        holder.rView.setAdapter(new OrderReviewAdapter(activity, order.getOrderedMeals()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        TextView orderName;
        CardView orderCard;
        RecyclerView rView;

        public Item(View itemView) {
            super(itemView);
            orderCard = itemView.findViewById(R.id.order_card);
            orderName = itemView.findViewById(R.id.order_name);
            rView = itemView.findViewById(R.id.order_meal_recycler_view);
        }
    }
}
