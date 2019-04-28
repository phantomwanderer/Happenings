package com.example.happenings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClubExecPage extends AppCompatActivity implements View.OnClickListener {
      Button b1, b2, b3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_exec_page);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
switch(v.getId())
{
    case R.id.button  :
    case R.id.button2 :
    case R.id.button3 : Intent intent = new Intent(this, AddDeleteEditEvents.class);
                        startActivity(intent);
                        break;

}
    }
}
