package com.example.dell.eventtracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleEventFragment extends Fragment implements View.OnClickListener {

    private Event event;
    private TextView eventNameTV,fromDateTV,toDateTV,budgetTV,currentExpenseTV;
    private Button expenseListBtn,momentListBtn,addExpenseBtn,addMomentBtn;
    private ListView expenseLV,momentLV;
    public SingleEventFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_single_event, container, false);
       event =  getArguments().getParcelable("single event");

        eventNameTV = (TextView) v.findViewById(R.id.event_name);
        fromDateTV = (TextView) v.findViewById(R.id.from_date);
        toDateTV = (TextView) v.findViewById(R.id.to_date);
        budgetTV = (TextView) v.findViewById(R.id.event_est_budget);
        currentExpenseTV = (TextView) v.findViewById(R.id.current_expense);
        expenseListBtn = (Button) v.findViewById(R.id.expense_listBtn);
        momentListBtn = (Button) v.findViewById(R.id.moment_listBtn);
        addExpenseBtn = (Button) v.findViewById(R.id.add_expenseBtn);
        addMomentBtn = (Button) v.findViewById(R.id.add_momentBtn);
        expenseLV = (ListView) v.findViewById(R.id.expense_listview);
        momentLV = (ListView) v.findViewById(R.id.moment_listview);

        expenseListBtn.setOnClickListener(this);
        momentListBtn.setOnClickListener(this);
        addMomentBtn.setOnClickListener(this);
        addExpenseBtn.setOnClickListener(this);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventNameTV.setText(event.getDestination());
        fromDateTV.setText(event.getFromDate());
        toDateTV.setText(event.getToDate());
        budgetTV.setText(String.valueOf(event.getBudget()));
        currentExpenseTV.setText(String.valueOf(event.getTotalExpense()));

        ArrayList<Expense> expenseList= new ArrayList<>();
        ArrayList<Moment> momentList= new ArrayList<>();
        expenseList = event.getExpenseList();
        momentList = event.getMomentList();

        ExpenseAdapter expenseAdapter = new ExpenseAdapter(getContext(),expenseList);
        expenseLV.setAdapter(expenseAdapter);
        MomentAdapter momentAdapter = new MomentAdapter(getContext(),momentList);
        momentLV.setAdapter(momentAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expense_listBtn:

                if (expenseLV.getVisibility()==View.VISIBLE){
                    expenseLV.setVisibility(View.GONE);
                    expenseListBtn.setText("See Expenses");
                }
                else {
                    expenseLV.setVisibility(View.VISIBLE);
                    expenseListBtn.setText("Hide Expenses");
                }

                break;
            case R.id.moment_listBtn:
                if (momentLV.getVisibility()==View.VISIBLE){
                    momentLV.setVisibility(View.GONE);
                    momentListBtn.setText("See Moments");
                }
                else {
                    momentLV.setVisibility(View.VISIBLE);
                    momentListBtn.setText("Hide Moments");
                }
                break;
            case R.id.add_expenseBtn:
                Toast.makeText(getActivity(), "Add Expense Btn clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_momentBtn:
                Toast.makeText(getActivity(), "Add Moment Btn clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(getActivity(), "Other place Clicked", Toast.LENGTH_SHORT).show();
                Log.d("singleEventFrag click","other place clickd");
        }
    }
}
