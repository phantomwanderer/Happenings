package com.example.happenings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.CalendarContract;
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
Button saver, calSync;
String currentdatetime;
int allowEvent = 0;
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
        calSync = findViewById(R.id.button5);
        saver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(e2.getText().toString().equalsIgnoreCase("30/04/2019")) && !(e2.getText().toString().equalsIgnoreCase("01/05/2019")) && !(e2.getText().toString().equalsIgnoreCase("02/05/2019") ) && !(e2.getText().toString().equalsIgnoreCase("03/05/2019")))
                {
                    preference = getSharedPreferences("pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preference.edit();
                edit.putString("Event Name", e1.getText().toString());
                edit.putString("Date", e2.getText().toString());
                edit.putString("Time", e3.getText().toString());
                edit.putString("Description and Venue", e4.getText().toString());
                edit.apply();
                allowEvent = 1;
                Toast.makeText(getApplicationContext(),"Changes Saved",Toast.LENGTH_LONG).show();

                }
                else
                Toast.makeText(getApplicationContext(),"Cannot create Event\nExamination already scheduled",Toast.LENGTH_LONG).show();

            }
        });

        calSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allowEvent == 1)
                {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setType("vnd.android.cursor.item/event");

                    Calendar beginTime = Calendar.getInstance();
                    beginTime.set(2019, 4, 29, Integer.parseInt(e3.getText().toString()), Integer.parseInt(e3.getText().toString()));
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(2019, 4, 29, Integer.parseInt(e3.getText().toString() + 2), Integer.parseInt(e3.getText().toString()) + 60);

                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime.getTimeInMillis());
                    intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym");
                    intent.putExtra(CalendarContract.Events.TITLE,e1.getText().toString() );
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, e4.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, e4.getText().toString());
                    intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

                    startActivity(intent);
                }

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
