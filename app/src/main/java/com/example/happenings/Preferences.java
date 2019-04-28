package com.example.happenings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Preferences extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
Spinner s1,s2,s3,s4,s5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        t1=findViewById(R.id.tone);
        t2=findViewById(R.id.ttwo);
        t3=findViewById(R.id.tthree);
        t4=findViewById(R.id.tfour);
        t5=findViewById(R.id.tfive);
        s1=findViewById(R.id.sone);
        s2=findViewById(R.id.stwo);
        s3=findViewById(R.id.sthree);
        s4=findViewById(R.id.sfour);
        s5=findViewById(R.id.sfive);

    }

    public void toast(View view) {

        t1.setText(s1.getSelectedItem().toString());
        t2.setText(s2.getSelectedItem().toString());
        t3.setText(s3.getSelectedItem().toString());
        t4.setText(s4.getSelectedItem().toString());
        t5.setText(s5.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(),"Your Preference is stored successfully",Toast.LENGTH_LONG).show();
    }
}
