package com.example.dell.eventtracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DELL on 10/16/2017.
 */

public class Moment implements Parcelable{
    private String momentNote;
    private int imageNO; /*just demo*/

    public Moment(String momentNote, int imageNO) {
        this.momentNote = momentNote;
        this.imageNO = imageNO;
    }

    protected Moment(Parcel in) {
        momentNote = in.readString();
        imageNO = in.readInt();
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
        imageNO = in.readInt();
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
        dest.writeInt(this.imageNO);

    }

    public String getMomentNote() {
        return momentNote;
    }

    public void setMomentNote(String momentNote) {
        this.momentNote = momentNote;
    }

    public int getImageNO() {
        return imageNO;
    }

    public void setImageNO(int imageNO) {
        this.imageNO = imageNO;
    }
}
