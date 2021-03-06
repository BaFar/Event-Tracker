package com.example.dell.eventtracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommingEventFragment extends Fragment {


    private  ArrayList<Event> eventList = new ArrayList<>();
    private ListView commingEventsLV;
    private TextView cEHText;
    private  int noOfEvents=0;
    public CommingEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_comming_event, container, false);

        cEHText = (TextView) v.findViewById(R.id.comming_events_heading_text);
        commingEventsLV = (ListView) v.findViewById(R.id.comming_event_LV);

        eventList = new ArrayList<>();
        eventList = getArguments().getParcelableArrayList("comming events");
        noOfEvents= eventList.size();

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (noOfEvents == 0) {
            cEHText.setText("Your do not have any up comming Event");

        } else {

            EventAdapter eventAdapter = new EventAdapter(getContext(),eventList);
            commingEventsLV.setAdapter(eventAdapter);

            commingEventsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    SingleEventFragment fragment = new SingleEventFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("single event",eventList.get(position));
                    bundle.putInt("event position",position);
                    fragment.setArguments(bundle);
                    ft.replace(R.id.eventFragmentContainer,fragment);
                    ft.commit();

                }
            });




        }
    }
}
