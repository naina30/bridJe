package com.example.bridje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button events;
    private Button profile;
    private Button location;
    private Button messaging;
    private ImageView attendance, studymaterial, cgpa, examSchedule;
    private Button reorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        events= (Button) findViewById(R.id.feed);
        location=(Button) findViewById(R.id.explore);
        messaging=(Button) findViewById(R.id.chat);
        profile=(Button) findViewById(R.id.profile);
        attendance= findViewById(R.id.attendance);
        studymaterial=findViewById(R.id.studyM);
        examSchedule=findViewById(R.id.examS);
        cgpa=findViewById(R.id.cgpa);
        reorder=findViewById(R.id.reorder);

        events.setOnClickListener(this);
        location.setOnClickListener(this);
        messaging.setOnClickListener(this);
        profile.setOnClickListener(this);
        attendance.setOnClickListener(this);
        studymaterial.setOnClickListener(this);
        examSchedule.setOnClickListener(this);
        cgpa.setOnClickListener(this);
        reorder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feed:
                Intent intent = new Intent(Home_Activity.this, Event_Activity.class);
                startActivity(intent);
                break;
            case R.id.explore:
                Intent intent2 = new Intent(Home_Activity.this, exlpore_Activity.class);
                startActivity(intent2);
                break;
            case R.id.chat:
                Intent intent3 = new Intent(Home_Activity.this, messagingActivity.class);
                startActivity(intent3);
                break;
            case R.id.profile:
                Intent intent1 = new Intent(Home_Activity.this, Home_Activity.class);
                startActivity(intent1);
                break;
            case R.id.attendance:
                Intent intent4 = new Intent(Home_Activity.this, attendance_activity.class);
                startActivity(intent4);
                break;
            case R.id.studyM:
                Intent intent5 = new Intent(Home_Activity.this, Study_Material_Activity.class);
                startActivity(intent5);
                break;
            case R.id.examS:
                Intent intent6 = new Intent(Home_Activity.this, ExamScheduleActivity.class);
                startActivity(intent6);
                break;
            case R.id.cgpa:
                Intent intent7 = new Intent(Home_Activity.this, cgpaActivity.class);
                startActivity(intent7);
                break;
            case R.id.reorder:
                Intent intent8 = new Intent(Home_Activity.this, Home_Activity.class);
                startActivity(intent8);
                break;

        }
    }
}
