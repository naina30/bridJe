package com.example.bridje;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class FireApp extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        if(FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }

}
