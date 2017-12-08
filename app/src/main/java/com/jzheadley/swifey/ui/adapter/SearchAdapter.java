package com.jzheadley.swifey.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.Following;
import com.jzheadley.swifey.models.User;
import com.jzheadley.swifey.ui.SearchActivity;
import com.jzheadley.swifey.ui.SearchPresenter;

import java.util.List;

import timber.log.Timber;

/**
 * Created by mit on 12/7/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SearchActivity activity;
    private SearchPresenter presenter;
    private List<User> items;

    public SearchAdapter(SearchActivity activity, SearchPresenter presenter, List<User> items) {
        this.activity = activity;
        this.presenter = presenter;
        this.items = items;
        Timber.v("search adapter Initialized with userss:	%s", items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View row = inflater.inflate(R.layout.follow_card, parent, false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item) holder).userName.setText(items.get(position).getFirstName() + items.get(position).getLastName());
        ((Item) holder).follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.postFollowing(new Following(FirebaseAuth.getInstance().getCurrentUser().getUid(),
                        new User(items.get(position).getUserId(), null, null, null, null, null, null, null, null, null, null)));
            }

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        ImageView userImg;
        TextView userName;
        CardView followCard;
        Button follow;

        public Item(View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            followCard = itemView.findViewById(R.id.follow_card);
            follow = itemView.findViewById(R.id.followBtn);
        }
    }
}
