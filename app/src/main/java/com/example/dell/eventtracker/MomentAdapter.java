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

public class MomentAdapter extends ArrayAdapter<Moment> {
    private Context context;
    private ArrayList<Moment> momentList;

    public MomentAdapter(@NonNull Context context, ArrayList<Moment> momentList) {
        super(context, R.layout.row,  momentList);
        this.context = context;
        this.momentList = momentList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row,parent,false);

        TextView momentNoteTV = (TextView) convertView.findViewById(R.id.note_text);
        TextView imagePathTV= (TextView) convertView.findViewById(R.id.note_value);
        momentNoteTV.setText(momentList.get(position).getMomentNote());
        imagePathTV.setText(momentList.get(position).getImagePath());
        return convertView;
    }
}
