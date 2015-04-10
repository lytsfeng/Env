package com.ldkj.env.activitys;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ldkj.env.R;
import com.ldkj.env.activitys.base.ActivityFrame;
import com.ldkj.env.fragments.MapFragment;
import com.ldkj.env.fragments.OnFragmentInteractionListener;
import com.ldkj.env.fragments.StationInfoFragment;
import com.ldkj.env.models.Station;
import com.ldkj.env.services.LocationService;


public class MainActivity extends ActivityFrame implements OnFragmentInteractionListener {


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame,new MapFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        stopService(new Intent(this, LocationService.class));
        super.onPause();
    }

    @Override
    protected void onResume() {
        startService(new Intent(this, LocationService.class));
        super.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
