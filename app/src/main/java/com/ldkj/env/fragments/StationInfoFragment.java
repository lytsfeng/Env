package com.ldkj.env.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldkj.env.R;
import com.ldkj.env.models.Station;

/**
 * A simple {@link Fragment} subclass.
 */
public class StationInfoFragment extends Fragment {

    private static final String KEY_PARAM = "key_param";
    private Station station;

    public static StationInfoFragment newInstance(Station station){
        StationInfoFragment _InfoFragment = new StationInfoFragment();
        Bundle _Bundle = new Bundle();
        _Bundle.putParcelable(KEY_PARAM,station);
        _InfoFragment.setArguments(_Bundle);
        return _InfoFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.station = (Station)getArguments().getParcelable(KEY_PARAM);
        }
    }

    public StationInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_station_info, container, false);
    }


}
