package com.jzheadley.swifey.network;


import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.models.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

import io.reactivex.Observable;

public interface SwifeyApi {

    @GET("/api/restaurants/today")
    Observable<List<Restaurant>> getTodaysRestaurants();

    @POST("/api/users/")
    Observable<User> createUser(@Body User user);

}
