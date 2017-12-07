package com.jzheadley.swifey.network;

import com.jzheadley.swifey.base.AppModule;
import com.jzheadley.swifey.network.notifications.FirebaseIDService;
import com.jzheadley.swifey.ui.CheckInActivity;
import com.jzheadley.swifey.ui.MainActivity;
import com.jzheadley.swifey.ui.PastCheckInActivity;
import com.jzheadley.swifey.ui.RestaurantListActivity;
import com.jzheadley.swifey.ui.SearchActivity;
import com.jzheadley.swifey.ui.UserDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);

    void inject(RestaurantListActivity restaurantListActivity);

    void inject(UserDetailsActivity userDetailsActivity);

    void inject(CheckInActivity checkInActivity);

    void inject(SearchActivity searchActivity);

    void inject (PastCheckInActivity pastCheckInActivity);

    void inject(FirebaseIDService firebaseIDService);

    // void inject(VideoActivity activity);

}
