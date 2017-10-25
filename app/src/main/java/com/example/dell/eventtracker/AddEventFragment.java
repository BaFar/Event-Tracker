package com.example.dell.eventtracker;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEventFragment extends Fragment implements View.OnClickListener{




    private CreateEvent createEvent;

    private TextView destinationTV,budgetTV,fromDateTV,toDateTV;
    private EditText destinationET,budgetET;
    private Button createBtn,fromDateBtn,toDateBtn;
    private String destination,fdate,tdate;
    private int budget;
    private  double lat,lon;
    private Calendar calendar;
    private int year,month,day;



    public AddEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_add, container, false);


        destinationET = (EditText) view.findViewById(R.id.destinationValue);
        budgetET = (EditText) view.findViewById(R.id.budgetValue);
        fromDateBtn = (Button) view.findViewById(R.id.fromDateValue);
        toDateBtn = (Button) view.findViewById(R.id.toDateValue);
        createBtn = (Button) view.findViewById(R.id.eventCreateBtn);

        fromDateBtn.setOnClickListener(this);
        toDateBtn.setOnClickListener(this);
        createBtn.setOnClickListener(this);

        calendar =  Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        return view;

    }

    @Override
    public void onClick(View v) {

        DatePickerDialog pickerDialog;
        switch (v.getId()){

            case R.id.fromDateValue:
                pickerDialog = new DatePickerDialog(getActivity(),onFormDateSelectedListener,year,month,day);
                pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                pickerDialog.show();

                break;
            case R.id.toDateValue:
                pickerDialog = new DatePickerDialog(getActivity(),onToDateSelectedListener,year,month,day);
                pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                pickerDialog.show();
                break;
            case R.id.eventCreateBtn:

                destination = destinationET.getText().toString();
                budget = Integer.parseInt(budgetET.getText().toString()); /*imply condition*/
                createEvent.createEventClicked(destination,budget,fdate,tdate,lat,lon);

                break;

        }
    }

    private DatePickerDialog.OnDateSetListener onFormDateSelectedListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fdate = dateFormat.format(calendar.getTime());
            fromDateBtn.setText(fdate);

        }
    };
    private DatePickerDialog.OnDateSetListener onToDateSelectedListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year,month,dayOfMonth);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            tdate = dateFormat.format(calendar.getTime());
            toDateBtn.setText(tdate);
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        createEvent = (CreateEvent) context;
    }

    public interface CreateEvent{
         void createEventClicked(String destination, int budget, String fromDate, String toDate,double lattitude,double logitude);
    }
}














/*
* destinationTV = (TextView) view.findViewById(R.id.destinationText);
        budgetTV = (TextView) view.findViewById(R.id.budgetText);
        fromDateTV = (TextView) view.findViewById(R.id.fromDateTV);
        toDateTV = (TextView) view.findViewById(R.id.toDateText);
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */