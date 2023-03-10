package com.example.myapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    public Frag1() {
        // Required empty public constructor
    }
    public MapView map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DBHelper database = new DBHelper(getContext());
        MapKitFactory.setApiKey("5ca7e353-d728-409c-a11b-ea4195141541");
        View view = inflater.inflate(R.layout.fragment_f1, container, false);
        MapKitFactory.initialize(view.getContext());
        map = view.findViewById(R.id.mapview);
        map.getMap().move(
                new CameraPosition(new Point(56.0184, 92.8672), 10.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
        final Button button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Note note = new Note((float) Math.random()*100, (float) Math.random()*100);
            database.create(note);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        map.onStart();
    }

    @Override
    public void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        map.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }
}