package com.example.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "storyDb";
    public static final String TABLE_STORY = "story";
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "dateTime";
    public static final String KEY_LONG = "coord_long";
    public static final String KEY_LAT = "coord_lat";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_STORY +
                "(" + KEY_ID + " INTEGER PRIMARY KEY autoincrement, "
                + KEY_LONG + " real," + KEY_LAT + " real," +
                KEY_DATE + " text " +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_STORY);
        onCreate(sqLiteDatabase);
    }

    public void create(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE, note.getDateTime());
        contentValues.put(KEY_LONG, note.getLongitude());
        contentValues.put(KEY_LAT, note.getLatitude());
        sqLiteDatabase.insert(TABLE_STORY, null, contentValues);
    }

    public void delete(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_STORY, KEY_ID + " =? ",
                new String[]{String.valueOf(note.getId())});
    }

    public ArrayList<Note> read() throws ParseException {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor csr = sqLiteDatabase.query(TABLE_STORY, null, null, null,
                null, null, null);
//        System.out.println(csr.getColumnIndex(KEY_ID));
        ArrayList<Note> array = new ArrayList<>();
        if (csr.moveToFirst()) {
            int idIndex = csr.getColumnIndex(KEY_ID);
            int dateIndex = csr.getColumnIndex(KEY_DATE);
            int latIndex = csr.getColumnIndex(KEY_LAT);
            int longIndex = csr.getColumnIndex(KEY_LONG);
            do {
                Note note = new Note(csr.getInt(idIndex), csr.getString(dateIndex),
                        csr.getFloat(latIndex), csr.getFloat(longIndex));
                array.add(note);
            }
            while (csr.moveToNext());
        }
        return array;
    }

    public void update(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DATE, note.getDateTime());
        contentValues.put(KEY_LONG, note.getLongitude());
        contentValues.put(KEY_LAT, note.getLatitude());

        sqLiteDatabase.update(TABLE_STORY, contentValues, KEY_ID + " =? ",
                new String[]{String.valueOf(note.getId())});
    }
}
