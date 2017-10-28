/*
 * Created by Taran Rai on 28/10/17 1:56 PM
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 28/10/17 1:45 PM
 */

package ca.bcit.lab.sqlite_lab_rai;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteOpenHelper helper = new PotluckDbHelper(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT name, date, time FROM EVENT_MASTER", null);

            cursor.moveToFirst();
            String event1_name = cursor.getString(0);
            String event1_date = cursor.getString(1);
            String event1_time = cursor.getString(2);
            cursor.moveToNext();
            String event2_name = cursor.getString(0);
            String event2_date = cursor.getString(1);
            String event2_time = cursor.getString(2);
            cursor.moveToNext();
            String event3_name = cursor.getString(0);
            String event3_date = cursor.getString(1);
            String event3_time = cursor.getString(2);

            TextView event1_name_tv = (TextView) findViewById(R.id.event1_name);
            event1_name_tv.setText(event1_name);
            TextView event1_date_tv = (TextView) findViewById(R.id.event1_date);
            event1_date_tv.setText(event1_date);
            TextView event1_time_tv = (TextView) findViewById(R.id.event1_time);
            event1_time_tv.setText(event1_time);

            TextView event2_name_tv = (TextView) findViewById(R.id.event2_name);
            event2_name_tv.setText(event2_name);
            TextView event2_date_tv = (TextView) findViewById(R.id.event2_date);
            event2_date_tv.setText(event2_date);
            TextView event2_time_tv = (TextView) findViewById(R.id.event2_time);
            event2_time_tv.setText(event2_time);

            TextView event3_name_tv = (TextView) findViewById(R.id.event3_name);
            event3_name_tv.setText(event3_name);
            TextView event3_date_tv = (TextView) findViewById(R.id.event3_date);
            event3_date_tv.setText(event3_date);
            TextView event3_time_tv = (TextView) findViewById(R.id.event3_time);
            event3_time_tv.setText(event3_time);

        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity/getEventMaster] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }
    }

    /*private Potluck getEventMaster(String potlck) {
        Potluck potluck = null;
        SQLiteOpenHelper helper = new PotluckDbHelper(this);
        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor= db.rawQuery("select * FROM EVENT_MASTER", null);



            /*Cursor cursor = db.query("EVENT_MASTER",
                    new String[] {"Name", "Date", "Time"},
                    "EVENT_MASTER = ?",
                    new String[] {potlck},
                    null, null, null);

            // move to the first record
            if (cursor.moveToFirst()) {
                // get the country details from the cursor
                potluck = new Potluck(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2));


            }
        } catch (SQLiteException sqlex) {
            String msg = "[MainActivity/getEventMaster] DB unavailable";
            msg += "\n\n" + sqlex.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }

        return  potluck;
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null)
            cursor.close();
        if (db != null)
            db.close();
    }
}
