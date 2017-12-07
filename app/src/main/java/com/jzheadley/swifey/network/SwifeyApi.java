package com.jzheadley.swifey.network;


import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.models.Meal;
import com.jzheadley.swifey.models.Restaurant;
import com.jzheadley.swifey.models.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SwifeyApi {

    @GET("/api/restaurants/today")
    Observable<List<Restaurant>> getTodaysRestaurants();

    @GET("/api/restaurants/{id}/meals")
    Observable<List<Meal>> getRestaurantMeals(@Path("id") int restaurantId);

    @GET("/api/users/search/{searchString}")
    Observable<List<User>>getSearch(@Path("searchString") String search);

    @POST("/api/users/")
    Observable<User> createUser(@Body User user);

    @POST("/api/checkins/")
    Observable<CheckIn> postCheckIn(@Body CheckIn checkIn);
}
