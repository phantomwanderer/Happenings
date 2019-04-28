package com.example.happenings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
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
EditText e1,e2,e3,e4;
Button saver;
String currentdatetime;
SharedPreferences preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete_edit_events);
        e1=findViewById(R.id.editText2);
        e2=findViewById(R.id.editText3);
        e3=findViewById(R.id.editText4);
        e4=findViewById(R.id.editText5);
        saver=findViewById(R.id.button4);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference = getSharedPreferences("pref", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preference.edit();
                edit.putString("Event Name", e1.getText().toString());
                edit.putString("Date", e2.getText().toString());
                edit.putString("Time", e3.getText().toString());
                edit.putString("Description and Venue", e4.getText().toString());
                edit.apply();
                Toast.makeText(getApplicationContext(),"Changes Saved",Toast.LENGTH_LONG).show();
            }
        });






       /* currentdatetime= DateFormat.getDateTimeInstance().format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String yesterdayAsString = dateFormat.format(calendar.getTime());
        Toast.makeText(getApplicationContext(),yesterdayAsString, Toast.LENGTH_LONG).show();*/

    }
}
