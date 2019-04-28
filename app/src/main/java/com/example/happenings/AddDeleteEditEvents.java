package com.example.happenings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDeleteEditEvents extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    Button saver;
    String currentdatetime, title, date, desc_venue;
    SharedPreferences preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete_edit_events);
        e1 = findViewById(R.id.editText2);
        e2 = findViewById(R.id.editText3);
        e3 = findViewById(R.id.editText4);
        e4 = findViewById(R.id.editText5);
        saver = findViewById(R.id.button4);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preference.edit();
                title = e1.getText().toString();
                date = e2.getText().toString();
                desc_venue = e4.getText().toString();
                edit.putString("Event Name", title);
                edit.putString("Date", date);
                edit.putString("Time", e3.getText().toString());
                edit.putString("Description and Venue", desc_venue);
                edit.apply();
                Toast.makeText(getApplicationContext(), "Changes Saved", Toast.LENGTH_LONG).show();
            }
        });

        Button button5= (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendarEvent = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", calendarEvent.getTimeInMillis());
                intent.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                intent.putExtra("title", title);
                intent.putExtra("allDay", false);
                intent.putExtra("rule", "FREQ=YEARLY");
                startActivity(intent);

            }
        });

    }
}
