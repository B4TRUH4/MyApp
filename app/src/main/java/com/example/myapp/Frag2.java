package com.example.myapp;

import android.os.Bundle;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {
    public Frag2() {
        // Required empty public constructor
    }

    public View view;
    public LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_f2, container, false);
        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            createTable();
        }
    }


    public void createTable() {

        DBHelper database = new DBHelper(getContext());
        TableLayout tl = view.findViewById(R.id.story_table);
        tl.removeAllViews();
        View header = inflater.inflate(R.layout.table_header, null, false);
        tl.addView(header);
        ArrayList<Note> array;
        try {
            array = database.read();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (Note elem : array) {
            View tr = inflater.inflate(R.layout.table_item, null, false);
            TextView tv = tr.findViewById(R.id.coordinates);
            tv.setText(elem.getLongitude() + "  " + elem.getLatitude());
            tv = tr.findViewById(R.id.date);
            tv.setText(elem.getDateTime());
            tl.addView(tr);
            final ImageButton button = tr.findViewById(R.id.deleteButton);
            button.setId(elem.getId());
            button.setOnClickListener(v -> {
                tl.removeView(tr);
                database.delete(elem);
            });
        }
    }
}