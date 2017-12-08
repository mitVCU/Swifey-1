package com.jzheadley.swifey.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.base.BaseApplication;
import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.network.SwifeyApi;
import com.jzheadley.swifey.ui.adapter.PastCheckInAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class PastCheckInActivity extends AppCompatActivity {
    @Inject
    SwifeyApi api;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CheckIn> checkIns;
    private PastCheckInPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_check_in);
        ((BaseApplication) getApplication()).getNetComponent().inject(this);
        presenter = new PastCheckInPresenter(api, this);
        checkIns = new ArrayList<>();
        mRecyclerView = findViewById(R.id.check_in_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PastCheckInAdapter(this, checkIns);
        presenter.getCheckInById();

    }

    public void setCheckIns(List<CheckIn> checkIns) {
        this.checkIns = checkIns;
        Timber.v("these are your old checkins "+ checkIns.toString());
        mAdapter = new PastCheckInAdapter(getApplicationContext(), checkIns);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
