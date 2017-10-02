package com.mosoti.myrestaurants.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mosoti.myrestaurants.models.Restaurant;
import com.mosoti.myrestaurants.ui.RestaurantDetailFragment;

import java.util.ArrayList;

/**
 * Created by mosoti on 9/12/17.
 */

public class RestaurantPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Restaurant> mRestaurants;
    private String mSource;

    public RestaurantPagerAdapter(FragmentManager fm, ArrayList<Restaurant> restaurants,String source) {
        super(fm);
        mRestaurants = restaurants;
        mSource=source;

    }

    @Override
    public Fragment getItem(int position) {
        return RestaurantDetailFragment.newInstance(mRestaurants,position,mSource);
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }
}
