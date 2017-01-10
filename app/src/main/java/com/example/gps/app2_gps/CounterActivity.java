package com.example.gps.app2_gps;

import android.content.Context;
import android.hardware.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.*;
import android.text.format.Time;
import java.util.*;


public class CounterActivity extends FragmentActivity implements SensorEventListener {


    private SensorManager sensorManager;
    private TextView count;
    private TextView previousCount;
    boolean activityRunning;
    private DatabaseReference mDatabase;
    private Time startTime = new Time();
    ListView listView;
    boolean first = true;
    private ArrayList<String> results = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        count = (TextView) findViewById(R.id.count);
        previousCount = (TextView) findViewById(R.id.previousCount);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        startTime.setToNow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]));
        }
        Time curr = new Time();

        curr.setToNow();
        if ((curr.second - startTime.second) >= 10 || first == true) {

            previousCount.append("Steps: "+String.valueOf(event.values[0]) + " Time: " + startTime.format2445() + "\n" );
            CounterData obj = new CounterData(event.values[0], startTime.format("yyyy-MM-dd"));
            mDatabase.child("counter").push().setValue(obj);
            startTime.setToNow();
            first = false;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}