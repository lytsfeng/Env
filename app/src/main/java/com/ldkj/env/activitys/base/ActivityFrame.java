package com.ldkj.env.activitys.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.ldkj.env.R;
import com.ldkj.env.fragments.MapFragment;
import com.ldkj.env.fragments.OnFragmentInteractionListener;

/**
 * Created by john on 15-4-10.
 */
public class ActivityFrame extends ActivityBase {


    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();

    }



    protected  void openFragment(int layoutId,Fragment fragment,boolean isBack){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame, new MapFragment());
        if(isBack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
