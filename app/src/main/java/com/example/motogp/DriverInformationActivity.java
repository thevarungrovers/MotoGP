package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DriverInformationActivity extends AppCompatActivity {

    TextView txtFirstName, txtLastName, txtDOB, txtCountry, txtTeamName;
    String picURL, helmetURL, bikeURL;

    ImageView imageDriver, imageHelmet, imageBike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_information);

        txtFirstName = findViewById(R.id.txtName);
        txtLastName = findViewById(R.id.txtSurname);
        txtDOB = findViewById(R.id.txtDOB);
        txtCountry = findViewById(R.id.txtCountryName);
        txtTeamName = findViewById(R.id.txtTeamName);
        imageDriver = findViewById(R.id.imgDriverImage);
        imageBike = findViewById(R.id.imgBike);
        imageHelmet = findViewById(R.id.imgHelmet);

        Intent intent = getIntent();

        txtFirstName.setText(intent.getStringExtra("driverFirstName"));
        txtLastName.setText(intent.getStringExtra("driverLastName"));
        txtDOB.setText(intent.getStringExtra("driverDOB"));
        txtCountry.setText(intent.getStringExtra("driverCountry"));
        txtTeamName.setText(intent.getStringExtra("driverTeamName"));
        picURL = intent.getStringExtra("driverPicURL");
        helmetURL = intent.getStringExtra("driverHelmetURL");
        bikeURL = intent.getStringExtra("driverBikeURL");

        Picasso.get().load(picURL).into(imageDriver);
        Picasso.get().load(helmetURL).into(imageHelmet);
        Picasso.get().load(bikeURL).into(imageBike);

    }
}