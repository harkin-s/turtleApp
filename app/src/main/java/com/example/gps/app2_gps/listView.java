package com.example.gps.app2_gps;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.hardware.*;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.*;
import android.text.format.Time;
import java.util.*;


/**
 * Created by Sean on 11-Jan-17.
 */

public class listView extends FragmentActivity {

    private ListView list = null;
    public DatabaseReference mDatabase;
    private String[] data = new String[20];
    private long amt = 0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = (ListView) findViewById(R.id.list);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, data);

        list.setAdapter(adapter);
        //showList();
    }

    public void showList() {

        final DatabaseReference ref = mDatabase.child("counter").getRef();
        ref.keepSynced(true);
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        db.getReference().child("counter").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                amt = dataSnapshot.getChildrenCount();
                String[] vals = new String[(int)amt];
                int i = 0 ;
                for(DataSnapshot posts: dataSnapshot.getChildren()){
                    vals[i] = posts.getValue().toString();

                    i++;
                }

                for(int a =0; a<=20;a++){
                    data[a] = vals[(int)amt];
                    amt--;

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data[1] = "h";



    }

}
