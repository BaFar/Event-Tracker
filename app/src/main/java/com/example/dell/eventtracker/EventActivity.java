package com.example.dell.eventtracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class EventActivity extends AppCompatActivity  implements AddEventFragment.CreateEvent{

    private ArrayList<Event> cEventList;
    private ArrayList<Event> pEventList;
    private FragmentManager fm= getSupportFragmentManager();
    private FragmentTransaction ft ;
    private DatabaseReference databaseReference;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user= auth.getCurrentUser();

    private BottomNavigationView mBottomNV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        mBottomNV = (BottomNavigationView) findViewById(R.id.navigation);
        String userId = user.getUid();
        cEventList = new ArrayList<Event>();
       // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("EventList");


    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            cEventList = new ArrayList<Event>();
            for(DataSnapshot ds: dataSnapshot.getChildren()){

                Event event = new Event();
                event = ds.getValue(Event.class);
                cEventList.add(event);

            }

            try{
                for(Event event: cEventList){
                    Toast.makeText(EventActivity.this, ""+event.getDestination(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(EventActivity.this, "Size : "+cEventList.size(), Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.d("data","array size 0");
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });


        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("comming events",cEventList);
        AddEventFragment addEventFragment = new AddEventFragment();
        addEventFragment.setArguments(bundle);
        ft = fm.beginTransaction();
        ft.add(R.id.eventFragmentContainer,addEventFragment);
        ft.commit();

        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment=null;

                switch (item.getItemId()){
                    case R.id.comming_event_menu:
                        fragment = new CommingEventFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("comming events",cEventList);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.past_event_menu:
                        fragment = new PastEventFragment();
                        break;
                    case R.id.add_event_menu:
                        fragment = new AddEventFragment();
                        break;
                }


                transaction.replace(R.id.eventFragmentContainer,fragment);
                transaction.commit();
                return true;
            }
        });


    }
    @Override
    public void createEventClicked(String destination, int budget, String fromDate, String toDate) {

        Toast.makeText(this, ""+destination, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ""+fromDate, Toast.LENGTH_SHORT).show();
       String keyValue=databaseReference.push().getKey();





        Event event = new Event(destination,fromDate,toDate, budget);
        event.setKey(keyValue);
        event.addExpense(new Expense("Breakfast",60));

        event.addMoment(new Moment("Dinner with local food",1));
        databaseReference.child(keyValue).setValue(event);

        event.addExpense(new Expense("Dinner",90));
        event.addMoment(new Moment("break fast with local food",1));
        databaseReference.child(keyValue).setValue(event);



    }

    public void logOut(View view) {

        auth.signOut();
        FirebaseUser user =auth.getCurrentUser();
        if (user==null){
            startActivity(new Intent(this,SignUpActivity.class));
        }
        else {
            Toast.makeText(this, "Log out failed", Toast.LENGTH_SHORT).show();
            Log.d("logout","failed");
        }
    }


}

/*
*
        DatabaseReference dbRefE =  FirebaseDatabase.getInstance().getReference().child("EventList").child(keyValue).child("ExpenseList");
        DatabaseReference dbRefM =  FirebaseDatabase.getInstance().getReference().child("EventList").child(keyValue).child("MomentList");
        Expense  expense = new Expense("Breakfast",60);
        Moment moment = new Moment("Breakfast with local food",3);

        dbRefE.setValue(expense);
        dbRefM.setValue(moment);

         GenericTypeIndicator<ArrayList<Event>> indicator = new GenericTypeIndicator<ArrayList<Event>>() { };
            cEventList = new ArrayList<Event>();
                  cEventList = dataSnapshot.getValue(indicator);
            int i = cEventList.get(0).getBudget();
           */