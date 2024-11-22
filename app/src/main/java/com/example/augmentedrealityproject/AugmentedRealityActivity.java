package com.example.augmentedrealityproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class AugmentedRealityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augmented_reality);

        //Get the intent to access the location passed the augmented reality
        Intent intent = getIntent();
        String location = intent.getStringExtra("LOCATION");
        Log.d("COMP3018", "Here is location: "+ location);
    }
}