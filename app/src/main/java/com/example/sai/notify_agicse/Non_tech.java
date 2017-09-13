package com.example.sai.notify_agicse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    TextView tv1, tv2, tv3, info_event1, info_event2, info_event3;
    NavigationView nv;
    private DatabaseReference databaseReference, info, events, ref;
    private FirebaseDatabase firebaseDatabase;
    private StorageReference ev1, ev2, ev3;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_tech);
        ref = FirebaseDatabase.getInstance().getReference("Users");
        tv1 = (TextView) findViewById(R.id.tv_event1);
        tv2 = (TextView) findViewById(R.id.tv_event2);
        tv3 = (TextView) findViewById(R.id.tv_event3);
        info_event1 = (TextView) findViewById(R.id.info_event1);
        info_event2 = (TextView) findViewById(R.id.info_event2);
        info_event3 = (TextView) findViewById(R.id.info_event3);
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
                String eve1 = dataSnapshot.child("Non_tech").child("Event1").getValue().toString();
                tv1.setText(eve1 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve2 = dataSnapshot.child("Non_tech").child("Event2").getValue().toString();
                tv2.setText(eve2 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve3 = dataSnapshot.child("Non_tech").child("Event3").getValue().toString();
                tv3.setText(eve3 + " :");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Events_info");
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve1_info = dataSnapshot.child("Event4").getValue().toString();
                info_event1.setText(eve1_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve2_info = dataSnapshot.child("Event5").getValue().toString();
                info_event2.setText(eve2_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String eve3_info = dataSnapshot.child("Event6").getValue().toString();
                info_event3.setText(eve3_info);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ev1 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/aagama.jpg");
        ev2 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/sports_bout.jpg");
        ev3 = FirebaseStorage.getInstance().getReferenceFromUrl("gs://notify-agicse.appspot.com/Non_Technical/rasaayanika.jpg");
        ImageView e1 = (ImageView) findViewById(R.id.event1_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev1)
                .placeholder(R.drawable.photoshop)
                .into(e1);
        ImageView e2 = (ImageView) findViewById(R.id.event2_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev2)
                .placeholder(R.drawable.photoshop)
                .into(e2);
        ImageView e3 = (ImageView) findViewById(R.id.event3_view);
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(ev3)
                .placeholder(R.drawable.photoshop)
                .into(e3);
        events = firebaseDatabase.getReferenceFromUrl("https://notify-agicse.firebaseio.com/Users");
        Button b1 = (Button) findViewById(R.id.b_event1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event4").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        Button b2 = (Button) findViewById(R.id.b_event2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event5").setValue("yes");
                Toast.makeText(getApplicationContext(), "You will be Notified", Toast.LENGTH_LONG).show();
            }
        });
        Button b3 = (Button) findViewById(R.id.b_event3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                events.child(id).child("event6").setValue("yes");
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
