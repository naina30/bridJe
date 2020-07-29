package com.example.bridje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class attendance_activity extends AppCompatActivity implements View.OnClickListener {

    private Button events;
    private Button profile;
    private Button location;
    private Button messaging;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_activity);
        events= (Button) findViewById(R.id.feed);
        location=(Button) findViewById(R.id.explore);
        messaging=(Button) findViewById(R.id.chat);
        profile=(Button) findViewById(R.id.profile);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feed:
                Intent intent = new Intent(attendance_activity.this, Event_Activity.class);
                startActivity(intent);
                break;
            case R.id.explore:
                Intent intent2 = new Intent(attendance_activity.this, exlpore_Activity.class);
                startActivity(intent2);
                break;
            case R.id.chat:
                Intent intent3 = new Intent(attendance_activity.this, messagingActivity.class);
                startActivity(intent3);
                break;
            case R.id.profile:
                Intent intent1 = new Intent(attendance_activity.this, Home_Activity.class);
                startActivity(intent1);
                break;
        }
    }
}
