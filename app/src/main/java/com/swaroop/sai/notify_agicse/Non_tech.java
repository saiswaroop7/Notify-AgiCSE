package com.swaroop.sai.notify_agicse;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Sai on 26-07-2017.
 */
public class Non_tech extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static long back_pressed;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    String id, userid;
    TextView tv5, tv6, tv7, tv8, info_event5, info_event6, info_event7, info_event8;
    NavigationView nv;
    private DatabaseReference databaseReference, info, events, ref;
    private FirebaseDatabase firebaseDatabase;
    private StorageReference ev5, ev6, ev7, ev8;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_tech);
        ref = FirebaseDatabase.getInstance().getReference("Users");
        tv5 = (TextView) findViewById(R.id.tv_event5);
        tv6 = (TextView) findViewById(R.id.tv_event6);
        tv7 = (TextView) findViewById(R.id.tv_event7);
        tv8 = (TextView) findViewById(R.id.tv_event8);
        info_event5 = (TextView) findViewById(R.id.info_event5);
        info_event6 = (TextView) findViewById(R.id.info_event6);
        info_event7 = (TextView) findViewById(R.id.info_event7);
        info_event8 = (TextView) findViewById(R.id.info_event8);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id = firebaseAuth.getInstance().getCurrentUser().getUid();
                userid = dataSnapshot.child(id).child("Name").getValue().toString();
                TextView textView = (TextView) findViewById(R.id.header_tv);
                textView.setText("Welcome, " + userid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Events");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve5 = dataSnapshot.child("Non_tech").child("Event5").getValue().toString();
                tv5.setText(eve5 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve6 = dataSnapshot.child("Non_tech").child("Event6").getValue().toString();
                tv6.setText(eve6 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve7 = dataSnapshot.child("Non_tech").child("Event7").getValue().toString();
                tv7.setText(eve7 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve8 = dataSnapshot.child("Non_tech").child("Event8").getValue().toString();
                tv8.setText(eve8 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Events_info");
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve5_info = dataSnapshot.child("Event5").getValue().toString();
                info_event5.setText(eve5_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve6_info = dataSnapshot.child("Event6").getValue().toString();
                info_event6.setText(eve6_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve7_info = dataSnapshot.child("Event7").getValue().toString();
                info_event7.setText(eve7_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve8_info = dataSnapshot.child("Event8").getValue().toString();
                info_event8.setText(eve8_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ev5 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/event5.jpg");
        ev6 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/event6.jpg");
        ev7 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/event7.jpg");
        ev8 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/event8.jpg");
        ImageView e5 = (ImageView) findViewById(R.id.event5_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev5)
                .placeholder(R.drawable.photoshop)
                .into(e5);
        ImageView e6 = (ImageView) findViewById(R.id.event6_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev6)
                .placeholder(R.drawable.photoshop)
                .into(e6);
        ImageView e7 = (ImageView) findViewById(R.id.event7_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev7)
                .placeholder(R.drawable.photoshop)
                .into(e7);
        ImageView e8 = (ImageView) findViewById(R.id.event8_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev8)
                .placeholder(R.drawable.photoshop)
                .into(e8);
        events = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Users");
        Button b5 = (Button) findViewById(R.id.b_event5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event5").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        Button b6 = (Button) findViewById(R.id.b_event6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event6").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        Button b7 = (Button) findViewById(R.id.b_event7);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event7").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        Button b8 = (Button) findViewById(R.id.b_event8);
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event8").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.non_tech);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout back = (DrawerLayout) findViewById(R.id.non_tech);
        if (back.isDrawerOpen(GravityCompat.START)) {
            back.closeDrawer(GravityCompat.START);
        } else if (back_pressed + 2000 > System.currentTimeMillis()) {
            firebaseAuth.signOut();
            super.onBackPressed();
        } else
            Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);

        return true;
    }


    private void displaySelectedScreen(int id) {
        switch (id) {

            case R.id.nav_home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.nav_tech:
                startActivity(new Intent(this, Tech.class));
                finish();
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, About.class));
                finish();
                break;
            case R.id.nav_settings:
                startActivity(new Intent(this, Settings.class));
                finish();
                break;
            case R.id.nav_Dev:
                startActivity(new Intent(this, Dev.class));
                finish();
                break;
            case R.id.nav_logout:
                firebaseAuth.signOut();
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(this, Login.class));
                    finish();
                }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.non_tech);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return mToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
