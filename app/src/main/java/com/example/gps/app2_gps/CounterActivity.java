package com.example.gps.app2_gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.*;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.*;
import android.text.format.Time;
import java.util.*;


public class CounterActivity extends FragmentActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        startService(new Intent(getApplicationContext(),CounterService.class));

    }

    public void openList(View view) {
        Intent intent = new Intent(this, listView.class);
        startActivity(intent);
    }



}