package com.example.dell.eventtracker;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DELL on 10/16/2017.
 */

public class Expense  implements Parcelable{


    private String expensePurpose;
    private int expenseAmount;





    public Expense(String expensePurpose, int expenseAmount) {
        this.expensePurpose = expensePurpose;
        this.expenseAmount = expenseAmount;

    }

    public Expense() {
    }

    protected Expense(Parcel in) {
        expensePurpose = in.readString();
        expenseAmount = in.readInt();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public String getExpensePurpose() {
        return expensePurpose;
    }

    public void setExpensePurpose(String expensePurpose) {
        this.expensePurpose = expensePurpose;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.expensePurpose);
        dest.writeInt(this.expenseAmount);

    }
}
