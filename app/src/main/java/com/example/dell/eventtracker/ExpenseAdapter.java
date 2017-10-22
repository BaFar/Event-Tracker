package com.example.dell.eventtracker;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 10/22/2017.
 */

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    private Context context;
    private ArrayList<Expense>expenseList;

    public ExpenseAdapter(@NonNull Context context, ArrayList<Expense>expenseList) {
        super(context, R.layout.row, expenseList);
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row,parent,false);

        TextView purposeTV = (TextView) convertView.findViewById(R.id.note_text);
        TextView expenseAmountTV= (TextView) convertView.findViewById(R.id.note_value);
        purposeTV.setText(expenseList.get(position).getExpensePurpose());
        expenseAmountTV.setText(String.valueOf(expenseList.get(position).getExpenseAmount()));
        return convertView;
    }
}
