package com.jzheadley.swifey.network;


import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.models.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

import io.reactivex.Observable;

public interface SwifeyApi {

    @GET("/api/restaurants/today")
    Observable<List<Restaurant>> getTodaysRestaurants();

    @GET("/api/restaurants/{id}/meals")
    Observable<List<Meal>> getRestaurantMeals(@Path("id") int restaurantId);

    @GET("/api/users/search/{searchString}")
    Observable<List<User>>getSearch(@Path("searchString") String search);

    @POST("/api/users/")
    Observable<Void> createUser(@Body User user);

    @POST("/api/checkins/")
    Observable<CheckIn> postCheckIn(@Body CheckIn checkIn);

    @POST("/{id}/messagingId/{messagingId}")
    Observable<Void> setUserMessagingId(@Path("id") String userId, @Path("messagingID") String messagingId);
}
