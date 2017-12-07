package com.jzheadley.swifey.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.jzheadley.swifey.R;

import java.util.Arrays;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "MainActivity";
    private static final int RC_USER_DETAILS = 200;
    private Button temp;
    private View rootView;
    private Button temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temp = findViewById(R.id.button);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                startActivity(intent);
            }
        });

        temp2 = findViewById(R.id.search);
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        rootView = findViewById(R.id.root);
    }

    @Override
    protected void onStart() {
        super.onStart();
        determineLoginStatus();
    }

    private void determineLoginStatus() {
        Timber.d("determineLoginStatus: determining login status");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false, true)
                            .setAvailableProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @MainThread
    private void showSnackbar(@StringRes int errorMessageRes) {
        Snackbar.make(rootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_SIGN_IN:
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (resultCode == RESULT_OK) {
                    startActivityForResult(new Intent(this, UserDetailsActivity.class), RC_USER_DETAILS);
                } else {
                    if (response == null) {
                        showSnackbar(R.string.sign_in_cancelled);
                    }

                    if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackbar(R.string.no_internet_connection);
                        return;
                    }

                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackbar(R.string.unknown_error);
                        return;
                    }
                }
                break;
            case RC_USER_DETAILS:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                // User user = data.getParcelableExtra("userDetails");
                break;
        }
    }
}
