package com.example.sai.notify_agicse;

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

/**
 * Created by Sai on 26-07-2017.
 */
public class Non_tech extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    NavigationView nv;
    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.non_tech);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.non_tech);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv= (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed(){
        DrawerLayout back= (DrawerLayout) findViewById(R.id.drawerLayout);
        if (back.isDrawerOpen(GravityCompat.START)) {
            back.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
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
                startActivity(i);
                break;
            case R.id.nav_nontech:
                Intent i2 = new Intent(getApplicationContext(),Non_tech.class);
                startActivity(i2);
                break;
            case R.id.nav_about:
                Intent i3 = new Intent(getApplicationContext(),About.class);
                startActivity(i3);
                break;
            case R.id.nav_Dev:
                Intent i4 = new Intent(getApplicationContext(),Dev.class);
                startActivity(i4);
                break;
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
