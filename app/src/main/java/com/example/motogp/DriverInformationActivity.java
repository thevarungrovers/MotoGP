package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DriverInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        Intent i = getIntent();
        String receivedID = i.getStringExtra("driverID");

        /* code to fetch from api and get driver's details*/
    }
}