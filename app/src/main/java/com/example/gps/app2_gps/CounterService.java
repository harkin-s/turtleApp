package com.example.gps.app2_gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Sean on 14-Jan-17.
 */

public class CounterService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    private DatabaseReference mDatabase;
    private int iSteps;
    private Time startTime = new Time();
    boolean first = true;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate(){

        startTime.setToNow();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    public void onSensorChanged(SensorEvent event) {
        Time curr = new Time();

        curr.setToNow();
        curr.toMillis(true);
        startTime.toMillis(true);
        if (curr.toMillis(true) - startTime.toMillis(true) >= 3600000 || first == true) {
            if(first){
                iSteps = (int)event.values[0];
            }
            CounterData obj = new CounterData(event.values[0] , curr.format("%d-%m-%Y %H:%M:%S"));
            mDatabase.child("counter").push().setValue(obj);
            startTime.setToNow();
            first = false;

        }

        if (activityRunning) {
            count.setText(String.valueOf(event.values[0]));

        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}