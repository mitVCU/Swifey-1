package com.jzheadley.swifey.Models;

/**
 * Created by mit on 11/21/17.
 */

public class Restaurant {
    private Double restaurantID;
    private String restaurantName;
    private String restaurantDesc;
    private String restaurantHours;
    private String restaurantLogo;

    public Restaurant(Double restaurantID,
                      String restaurantName,
                      String restaurantDesc,
                      String restaurantHours,
                      String restaurantLogo) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.restaurantDesc = restaurantDesc;
        this.restaurantHours = restaurantHours;
        this.restaurantLogo = restaurantLogo;
    }

    public void setRestaurantID(Double restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantDesc(String restaurantDesc) {
        this.restaurantDesc = restaurantDesc;
    }

    public void setRestaurantHours(String restaurantHours) {this.restaurantHours = restaurantHours;}

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }

    public Double getRestaurantID() {return restaurantID;}

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantDesc() {return restaurantDesc;}

    public String getRestaurantHours() {
        return restaurantHours;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantID=" + restaurantID +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantDesc='" + restaurantDesc + '\'' +
                ", restaurantHours='" + restaurantHours + '\'' +
                ", restaurantLogo='" + restaurantLogo + '\'' +
                '}';
    }
}
