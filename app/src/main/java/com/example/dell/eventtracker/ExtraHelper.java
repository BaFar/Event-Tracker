package com.example.dell.eventtracker;

import android.app.DatePickerDialog;
import android.os.SystemClock;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by DELL on 10/23/2017.
 */

public class ExtraHelper {


    public static String getCurrentTime(){
        String currentTime=null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = df.format(c.getTime());
// Now formattedDate have current date/time

        return currentTime;
    }
}
