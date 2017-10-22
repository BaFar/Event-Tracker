package com.example.dell.eventtracker;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by DELL on 10/21/2017.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
