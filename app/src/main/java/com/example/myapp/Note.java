package com.example.myapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private int id;
    private Date dateTime;
    private float longitude;
    private float latitude;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
    public Note(int id, float longitude, float latitude) {
        this.id = id;
        this.dateTime = new Date();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Note(float longitude, float latitude) {
        this.id = 0;
        this.dateTime = new Date();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Note(int id, String dateTime, float longitude, float latitude) throws ParseException {
        this.id = id;
        this.dateTime = formatter.parse(dateTime);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return formatter.format(dateTime);
    }

    public void setDateTime(String dateTime) throws ParseException {
        this.dateTime = formatter.parse(dateTime);
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
