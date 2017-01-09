package com.example.gps.app2_gps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.gps.app2_gps.R.id.button;


/**
 * Created by Sean on 07-Jan-17.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, GPSService.class);
        startService(intent);

    }

    public void openMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openCounter(View view){
        Intent intent = new Intent(this, CounterActivity.class);
        startActivity(intent);
    }



}
