package com.ldkj.env.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john on 15-4-10.
 */
public class Station implements Parcelable {
    public String sName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sName);
    }

    public Station() {
    }

    private Station(Parcel in) {
        this.sName = in.readString();
    }

    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        public Station createFromParcel(Parcel source) {
            return new Station(source);
        }

        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}
