package com.jzheadley.swifey.network;

import com.jzheadley.swifey.Models.Restaurant;

import retrofit2.http.GET;

import java.util.List;

import io.reactivex.Observable;

public interface SwifeyApi {

    @GET("/api/restaurants/today")
    Observable<List<Restaurant>> getTodaysRestaurants();

}
