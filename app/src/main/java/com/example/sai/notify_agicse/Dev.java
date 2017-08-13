package com.example.sai.notify_agicse;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Sai on 26-07-2017.
 */

public class Dev extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView nv;
    private FirebaseAuth firebaseAuth;
    private static long back_pressed;
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dev);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv= (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed(){
        DrawerLayout back= (DrawerLayout) findViewById(R.id.dev);
        if (back.isDrawerOpen(GravityCompat.START)) {
            back.closeDrawer(GravityCompat.START);
        }
        else if  (back_pressed + 2000 > System.currentTimeMillis()) {super.onBackPressed();}
        else
            Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id=item.getItemId();
        displaySelectedScreen(id);

        return true;
    }


    private void displaySelectedScreen(int id){
        switch(id)
        {
            case R.id.nav_tech:
                Intent i = new Intent(getApplicationContext(),Tech.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);finish();
                break;
            case R.id.nav_nontech:
                Intent i2 = new Intent(getApplicationContext(),Non_tech.class);
                i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i2);finish();
                break;
            case R.id.nav_about:
                Intent i3 = new Intent(getApplicationContext(),About.class);
                i3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i3);finish();
                break;
            case R.id.nav_Dev:
                Intent i4 = new Intent(getApplicationContext(),Dev.class);
                i4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i4);finish();
                break;

            case R.id.nav_logout:
                firebaseAuth.signOut();
                if (firebaseAuth.getCurrentUser() == null)
                {
                    startActivity(new Intent(this, Login.class));
                    finish();
                }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
        {
            return mToggle.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}

