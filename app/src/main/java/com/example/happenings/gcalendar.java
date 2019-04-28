package com.example.happenings;

import android.content.Intent;
import android.provider.CalendarContract;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happenings.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class gcalendar extends AppCompatActivity {
    EditText one,two,three,starthour,startminute,endhour,endminute;
    String a,b,c,d,e,f,g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcalendar);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
starthour=findViewById(R.id.starthour);
startminute=findViewById(R.id.startminute);
endhour=findViewById(R.id.endhour);
endminute=findViewById(R.id.endminute);


    }


    public void hardik(View view) {

        a=one.getText().toString();
        b=two.getText().toString();
        c=three.getText().toString();
d=starthour.getText().toString();
e=startminute.getText().toString();
f=endhour.getText().toString();
g=endminute.getText().toString();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2019, 4, 28, Integer.parseInt(d), Integer.parseInt(e));
        Calendar endTime = Calendar.getInstance();
        endTime.set(2019, 4, 28, Integer.parseInt(f), Integer.parseInt(g));

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym");
        intent.putExtra(CalendarContract.Events.TITLE, a);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, b);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, c);
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }
}



