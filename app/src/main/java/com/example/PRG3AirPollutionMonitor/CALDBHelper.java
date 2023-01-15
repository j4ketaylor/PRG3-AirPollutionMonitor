package com.example.PRG3AirPollutionMonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

// Database helper class for the Calendar Activity, contains methods for creating and accessing the database.

public class CALDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CALData.db";
    public static final String TABLE_NAME = "CALEventTable";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "EVENT_NAME";
    public static final String COL_2 = "EVENT_DATE";
    public static final String COL_3 = "EVENT_TIME";

    public CALDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase CALdb) {
        CALdb.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, EVENT_NAME TEXT, EVENT_DATE TEXT, EVENT_TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase CALdb, int i, int i1) {
        CALdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(CALdb);

    }

    public boolean insertData(String eventName, LocalDate eventDate, LocalTime eventTime) {
        SQLiteDatabase CALdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, eventName);
        contentValues.put(COL_2, eventDate.toString());
        contentValues.put(COL_3, eventTime.toString());
        long result = CALdb.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor accessData() {
        SQLiteDatabase CALdb = this.getWritableDatabase();
        return CALdb.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

}
