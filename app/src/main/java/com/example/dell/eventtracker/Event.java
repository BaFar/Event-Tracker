package com.example.dell.eventtracker;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DELL on 10/16/2017.
 */

public class Event implements Parcelable {
    private String destination;
    private String fromDate;
    private String toDate;
    private int budget;
    private String key;
    public int totalExpense=0;


    private ArrayList<Expense> expenseList;
    private ArrayList<Moment> momentList;

    public Event(String destination, String fromDate, String toDate, int budget) {
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.budget = budget;
        expenseList = new ArrayList<>();
        momentList = new ArrayList<>();
    }

    public Event() {
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;

    }

    public ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public void addExpense(Expense expense){
        expenseList.add(expense);
        totalExpense = totalExpense+ expense.getExpenseAmount();

    }

    public ArrayList<Moment> getMomentList() {
        return momentList;
    }


    public void addMoment(Moment moment){
        this.momentList.add(moment);
    }

    public  int getTotalExpense() {
        return totalExpense;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected Event(Parcel source){

        destination = source.readString();
        fromDate = source.readString();
        toDate = source.readString();
        budget = source.readInt();
        key = source.readString();
        expenseList = source.createTypedArrayList(Expense.CREATOR);
        momentList = source.createTypedArrayList(Moment.CREATOR);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(destination);
        dest.writeString(fromDate);
        dest.writeString(toDate);
        dest.writeInt(budget);
        dest.writeString(key);
        dest.writeTypedList(expenseList);
        dest.writeTypedList(momentList);
    }
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };


}
