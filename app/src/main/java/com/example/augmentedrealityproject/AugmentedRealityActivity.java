package com.example.augmentedrealityproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.ArCoreApk;


public class AugmentedRealityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_augmented_reality);

        //Get the intent to access the location passed the augmented reality
        Intent intent = getIntent();
        String location = intent.getStringExtra("LOCATION");
        Log.d("COMP3018", "Here is location: "+ location);

        maybeEnableArButton();

    }

    void maybeEnableArButton() {
        Button mArButton = findViewById(R.id.mArButton);
        ArCoreApk.Availability availability = ArCoreApk.getInstance().checkAvailability(this);
            if (availability.isSupported()) {
                Log.d("COMP3018", "AR is supported");
                mArButton.setVisibility(View.VISIBLE);
                mArButton.setEnabled(true);
            } else { // The device is unsupported or unknown.
                Log.d("COMP3018", "AR is not supported");
                mArButton.setVisibility(View.INVISIBLE);
                mArButton.setEnabled(false);
            }
    }
}