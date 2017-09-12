package com.mosoti.myrestaurants.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mosoti.myrestaurants.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
      public static final String TAG =MainActivity.class.getSimpleName();
    @Bind(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        ButterKnife.bind(this);

        Typeface headerFont = Typeface.createFromAsset(getAssets(), "fonts/Capture_it.ttf");
        mAppNameTextView.setTypeface(headerFont);
        Log.d(TAG, "location create");
        mFindRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {
            String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);
            Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }


}

