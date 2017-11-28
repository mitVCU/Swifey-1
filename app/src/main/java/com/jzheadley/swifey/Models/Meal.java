package com.jzheadley.swifey.Models;

/**
 * Created by mit on 11/26/17.
 */

public class Meal {
    private int mealId;
    private String mealName;
    private String mealDesc;
    private int mealCost;
    //// TODO: 11/26/17 get meal image url 

    public Meal(int mealId, String mealName, String mealDesc, int mealCost) {

        this.mealId = mealId;
        this.mealName = mealName;
        this.mealDesc = mealDesc;
        this.mealCost = mealCost;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setMealDesc(String mealDesc) {
        this.mealDesc = mealDesc;
    }

    public void setMealCost(int mealCost) {
        this.mealCost = mealCost;
    }

    public int getMealId() {

        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getMealDesc() {
        return mealDesc;
    }

    public int getMealCost() {
        return mealCost;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", mealDesc='" + mealDesc + '\'' +
                ", mealCost=" + mealCost +
                '}';
    }
}
