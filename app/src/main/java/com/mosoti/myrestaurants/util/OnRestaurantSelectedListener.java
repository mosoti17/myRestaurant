package com.mosoti.myrestaurants.util;

import com.mosoti.myrestaurants.models.Restaurant;

import java.util.ArrayList;

/**
 * Created by mosoti on 9/27/17.
 */

public interface OnRestaurantSelectedListener {
    public void onRestaurantSelected(Integer position, ArrayList<Restaurant> restaurants,String source);
}
