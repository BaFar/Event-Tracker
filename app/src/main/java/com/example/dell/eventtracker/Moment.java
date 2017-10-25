package com.example.dell.eventtracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DELL on 10/16/2017.
 */

public class Moment implements Parcelable{
    private String momentNote;
    private String imagePath;
    private String momentTime;

    public Moment(String momentNote, String imagePath,String momentTime) {
        this.momentNote = momentNote;
        this.imagePath = imagePath;
        this.momentTime = momentTime;
    }

    protected Moment(Parcel in) {
        momentNote = in.readString();
        imagePath = in.readString();
        momentTime = in.readString();
    }

    public static final Creator<Moment> CREATOR = new Creator<Moment>() {
        @Override
        public Moment createFromParcel(Parcel in) {
            return new Moment(in);
        }

        @Override
        public Moment[] newArray(int size) {
            return new Moment[size];
        }
    };

    protected void Moment(Parcel in){
        momentNote = in.readString();
        imagePath = in.readString();
        momentTime = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Moment() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.momentNote);
        dest.writeString(this.imagePath);
        dest.writeString(this.momentTime);
    }

    public String getMomentNote() {
        return momentNote;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getMomentTime() {
        return momentTime;
    }
}
