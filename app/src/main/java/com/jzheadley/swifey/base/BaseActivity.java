package com.jzheadley.swifey.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * extend this anytime you make a new activity.
 * Create a presenter that implements @{@link BasePresenter} as well
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setupActivityComponent();
    }

    // protected abstract void setupActivityComponent();


}
