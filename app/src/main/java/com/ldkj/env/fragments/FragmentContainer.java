package com.ldkj.env.fragments;

import android.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 15-4-13.
 */
public class FragmentContainer {

    private static Map<String,Fragment> fragmentMap = new HashMap<>();

    public static void addFragment(String pTag,Fragment pFragment){
        fragmentMap.put(pTag,pFragment);
    }

    public static Fragment getFragment(String pTag){
        return fragmentMap.get(pTag);
    }

    public static void clearFragment(){
        fragmentMap.clear();
    }




}
