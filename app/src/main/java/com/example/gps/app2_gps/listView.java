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

import com.google.firebase.database.*;
import java.util.*;


/**
 * Created by Sean on 11-Jan-17.
 */

public class listView extends FragmentActivity {


    public DatabaseReference mDatabase;
    private long amt = 0;
    private TextView text;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        text = (TextView) findViewById(R.id.textData);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        showList();
    }

    public void showList() {

        final DatabaseReference ref = mDatabase.child("counter").getRef();
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                amt = dataSnapshot.getChildrenCount();
                String[] vals = new String[(int)amt];
                int i = 0 ;
                for(DataSnapshot posts: dataSnapshot.getChildren()){
                    vals[i] = posts.getValue().toString();

                    i++;
                }
                //Shows the last 100 entries in the database
                for(int a =1; a<=101;a++){
                    String t = vals[(int)amt-a].substring(6,23);
                    String s = vals[(int)amt-a].substring(26,39);

                     text.append("Time: "+t +" "+ s + "\n");
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}
