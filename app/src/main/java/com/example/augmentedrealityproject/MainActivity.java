package com.example.augmentedrealityproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String drawSelection;
    private String[] drawNames;
    private static final String TAG = "COMP3018";
    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button findLocationB = findViewById(R.id.findDrawB);
        Spinner selectLocationS = findViewById(R.id.selectLocationS);

        //Get all the array names of the draw locations
        drawNames = getResources().getStringArray(R.array.draw_names);

        //Initialise the spinner
        selectLocationS.setOnItemSelectedListener(MainActivity.this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_item, drawNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectLocationS.setAdapter(adapter);


        findLocationB.setOnClickListener(view ->{
            Log.d(TAG, "Here is draw selection: " + drawSelection);
            Intent intent = new Intent(MainActivity.this, AugmentedRealityActivity.class);
            intent.putExtra("LOCATION", drawSelection);
            resultLauncher.launch(intent);
        });


        //ResultLauncher for when the app returns to this activity
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Log.d("COMP3018", "Here in result okay");
                    }
                    if(result.getResultCode() == RESULT_CANCELED && result.getData() != null){
                        Log.d("COMP3018", "Here in result cancelled");
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        drawSelection = adapterView.getItemAtPosition(i).toString();
        Log.d(TAG, drawSelection);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        drawSelection = drawNames[0];
    }
}