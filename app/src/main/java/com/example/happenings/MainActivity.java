package com.example.happenings;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String MY_ACCOUNT_NAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//calendar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startService(new Intent(this,serv.class));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        String yesterdayAsString = dateFormat.format(calendar.getTime());
       // Toast.makeText(getApplicationContext(),yesterdayAsString, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_preferences)
        {
Intent intent=new Intent(MainActivity.this,Preferences.class);
startActivity(intent);

        }

        else if (id == R.id.nav_events)
        {
           Intent intent=new Intent(MainActivity.this,EventListActivity.class);
            startActivity(intent);

        }

        else if (id == R.id.nav_login)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.googlecalendar)
        {
            Intent intent=new Intent(this,gcalendar.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
       // startService(new Intent(this,serv.class));

        super.onDestroy();
    }


























public void calendar() {


    String[] projection =
            new String[]{
                    CalendarContract.Calendars._ID,
                    CalendarContract.Calendars.NAME,
                    CalendarContract.Calendars.ACCOUNT_NAME,
                    CalendarContract.Calendars.ACCOUNT_TYPE};
    Cursor calCursor =
            getContentResolver().
                    query(CalendarContract.Calendars.CONTENT_URI,
                            projection,
                            CalendarContract.Calendars.VISIBLE + " = 1",
                            null,
                            CalendarContract.Calendars._ID + " ASC");
    if (calCursor.moveToFirst()) {
        do {
            long id = calCursor.getLong(0);
            String displayName = calCursor.getString(1);
            // ...
        } while (calCursor.moveToNext());
    }
    ContentValues values = new ContentValues();
    values.put(
            CalendarContract.Calendars.ACCOUNT_NAME,
            MY_ACCOUNT_NAME);
    values.put(
            CalendarContract.Calendars.ACCOUNT_TYPE,
            CalendarContract.ACCOUNT_TYPE_LOCAL);
    values.put(
            CalendarContract.Calendars.NAME,
            "GrokkingAndroid Calendar");
    values.put(
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
            "GrokkingAndroid Calendar");
    values.put(
            CalendarContract.Calendars.CALENDAR_COLOR,
            0xffff0000);
    values.put(
            CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL,
            CalendarContract.Calendars.CAL_ACCESS_OWNER);
    values.put(
            CalendarContract.Calendars.OWNER_ACCOUNT,
            "some.account@googlemail.com");
    values.put(
            CalendarContract.Calendars.CALENDAR_TIME_ZONE,
            "Europe/Berlin");
    values.put(
            CalendarContract.Calendars.SYNC_EVENTS,
            1);
    Uri.Builder builder =
            CalendarContract.Calendars.CONTENT_URI.buildUpon();
    builder.appendQueryParameter(
            CalendarContract.Calendars.ACCOUNT_NAME,
            "com.grokkingandroid");
    builder.appendQueryParameter(
            CalendarContract.Calendars.ACCOUNT_TYPE,
            CalendarContract.ACCOUNT_TYPE_LOCAL);
    builder.appendQueryParameter(
            CalendarContract.CALLER_IS_SYNCADAPTER,
            "true");
    Uri uri =
            getContentResolver().insert(builder.build(), values);



    long calId = getCalendarId();
    if (calId == -1) {
        // no calendar account; react meaningfully
        return;
    }
    GregorianCalendar cal = new GregorianCalendar(2012, 11, 14);
    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    cal.set(Calendar.HOUR, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    long start = cal.getTimeInMillis();
    ContentValues values2 = new ContentValues();
    values2.put(Events.DTSTART, start);
    values2.put(Events.DTEND, start);
    values2.put(Events.RRULE,
            "FREQ=DAILY;COUNT=20;BYDAY=MO,TU,WE,TH,FR;WKST=MO");
    values2.put(Events.TITLE, "Some title");
    values2.put(Events.EVENT_LOCATION, "Münster");
    values2.put(Events.CALENDAR_ID, calId);
    values2.put(Events.EVENT_TIMEZONE, "Europe/Berlin");
    values2.put(Events.DESCRIPTION,
            "The agenda or some description of the event");
// reasonable defaults exist:
    values2.put(Events.ACCESS_LEVEL, Events.ACCESS_PRIVATE);
    values2.put(Events.SELF_ATTENDEE_STATUS,
            Events.STATUS_CONFIRMED);
    values2.put(Events.ALL_DAY, 1);
    values2.put(Events.ORGANIZER, "some.mail@some.address.com");
    values2.put(Events.GUESTS_CAN_INVITE_OTHERS, 1);
    values2.put(Events.GUESTS_CAN_MODIFY, 1);
    values2.put(Events.AVAILABILITY, Events.AVAILABILITY_BUSY);
    Uri uri2 =
            getContentResolver().
                    insert(Events.CONTENT_URI, values2);
    long eventId = new Long(uri2.getLastPathSegment());

    long selectedEventId =187 ; // the event-id;
            String[] proj =  new String[]{
                    Events._ID,
                    Events.DTSTART,
                    Events.DTEND,
                    Events.RRULE,
                    Events.TITLE};
    Cursor cursor =
            getContentResolver().
                    query(
                            Events.CONTENT_URI,
                            proj,
                            Events._ID + " = ? ",
                            new String[]{Long.toString(selectedEventId)},
                            null);
    if (cursor.moveToFirst()) {
        // read event data
    }

    long begin =35;// starting time in milliseconds
    long end =234 ;// ending time in milliseconds
            String[] proj1 =
            new String[]{
                    Instances._ID,
                    Instances.BEGIN,
                    Instances.END,
                    Instances.EVENT_ID};
    Cursor cursor1 =
            Instances.query(getContentResolver(), proj1, begin, end);
    if (cursor1.getCount() > 0) {
        // deal with conflict
    }

    String[] selArgs =
            new String[]{Long.toString(selectedEventId)};
    int deleted =
            getContentResolver().
                    delete(
                            Events.CONTENT_URI,
                            Events._ID + " =? ",
                            selArgs);

    ContentValues values1 = new ContentValues();
    values1.put(Events.TITLE, "Some new title");
    values1.put(Events.EVENT_LOCATION, "A new location");
    String[] selArgs1 =
            new String[]{Long.toString(selectedEventId)};
    int updated =
            getContentResolver().
                    update(
                            Events.CONTENT_URI,
                            values1,
                            Events._ID + " =? ",
                            selArgs1);


    values1.clear();
    values1.put(CalendarContract.Attendees.EVENT_ID, eventId);
    values1.put(CalendarContract.Attendees.ATTENDEE_TYPE, CalendarContract.Attendees.TYPE_REQUIRED);
    values1.put(CalendarContract.Attendees.ATTENDEE_NAME, "Douglas Adams");
    values1.put(CalendarContract.Attendees.ATTENDEE_EMAIL, "d.adams@zaphod-b.com");
    getContentResolver().insert(CalendarContract.Attendees.CONTENT_URI, values1);
// adding a reminder:
    values1.clear();
    values1.put(CalendarContract.Reminders.EVENT_ID, eventId);
    values1.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
    values1.put(CalendarContract.Reminders.MINUTES, 30);
    getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values1);


}
    private long getCalendarId () {
        String[] projection = new String[]{CalendarContract.Calendars._ID};
        String selection =
                CalendarContract.Calendars.ACCOUNT_NAME +
                        " = ? AND " +
                        CalendarContract.Calendars.ACCOUNT_TYPE +
                        " = ? ";
        // use the same values as above:
        String[] selArgs =
                new String[]{
                        MY_ACCOUNT_NAME,
                        CalendarContract.ACCOUNT_TYPE_LOCAL};
        Cursor cursor =
                getContentResolver().
                        query(
                                CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                selection,
                                selArgs,
                                null);
        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }
        return -1;
    }

}
