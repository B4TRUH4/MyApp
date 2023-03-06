package com.example.myapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {
    public Frag2() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f2, container, false);
        TableLayout tl = view.findViewById(R.id.story_table);
        for (int i = 0; i < 3; i++) {
            View tr = inflater.inflate(R.layout.table_item, null, false);
            TextView tv = tr.findViewById(R.id.coordinates);
            tv.setText("Coordinates " + i);
            tv = tr.findViewById(R.id.date);
            tv.setText("Date " + i);
            tl.addView(tr);
            final ImageButton button = tr.findViewById(R.id.deleteButton);
            button.setOnClickListener(v -> {
                tl.removeView(tr);
            });
        }
            return view;
    }

}