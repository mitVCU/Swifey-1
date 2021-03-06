package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.User;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {
    @Inject
    SwifeyApi api;

    private Button search;
    private String searchText;
    private EditText query;
    private List<User> users;
    private SearchPresenter presenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        presenter = new SearchPresenter(api, this);
        users = new ArrayList<>();
        query = findViewById(R.id.search_bar);
        mRecyclerView = findViewById(R.id.follower_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        search = findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.v("searchText: %s", searchText);
                presenter.getSearch(query.getText().toString());
            }
        });

    }

    public void setUsers(List<User> users) {
        this.users = users;
        Timber.v("user to follow%s", users);
        mAdapter = new SearchAdapter(this, presenter, users);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
