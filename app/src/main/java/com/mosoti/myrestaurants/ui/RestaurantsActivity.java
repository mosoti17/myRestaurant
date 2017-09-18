package com.mosoti.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mosoti.myrestaurants.R;
import com.mosoti.myrestaurants.adapters.RestaurantListAdapter;
import com.mosoti.myrestaurants.models.Restaurant;
import com.mosoti.myrestaurants.services.YelpService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class RestaurantsActivity extends AppCompatActivity {

    //private SharedPreferences mSharedPreferences;
   //private String mRecentAddress;


    public static final String TAG = RestaurantsActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RestaurantListAdapter mAdapter;



    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Restaurant> mRestaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the restaurants near: " + location);
        getRestaurants(location);


        //mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        //Log.d("Shared Pref Location", mRecentAddress);



        //if (mRecentAddress != null) {
         //   getRestaurants(mRecentAddress);
        //}



    }

    private void getRestaurants(String location) {
        final YelpService yelpService = new YelpService();
        yelpService.findRestaurants(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRestaurants = yelpService.processResults(response);

                RestaurantsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new RestaurantListAdapter(getApplicationContext(), mRestaurants);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RestaurantsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        for (Restaurant restaurant : mRestaurants) {
                            Log.d(TAG, "Name: " + restaurant.getName());
                            Log.d(TAG, "Phone: " + restaurant.getPhone());
                            Log.d(TAG, "Website: " + restaurant.getWebsite());
                            Log.d(TAG, "Image url: " + restaurant.getImageUrl());
                            Log.d(TAG, "Rating: " + Double.toString(restaurant.getRating()));
                            Log.d(TAG, "Address: " + android.text.TextUtils.join(", ", restaurant.getAddress()));
                            Log.d(TAG, "Categories: " + restaurant.getCategories().toString());
                        }

                    }
//            } throws IOException {
//                try {
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                    mRestaurants = yelpService.processResults(response);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
                });

            }
        });
    }



}
