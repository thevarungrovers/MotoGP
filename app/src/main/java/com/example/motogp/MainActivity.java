package com.example.motogp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView txtUsername;
    Button btnSearchDriver, btnListAllDrivers, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        btnSearchDriver = findViewById(R.id.btnSearchDriver);
        btnListAllDrivers = findViewById(R.id.btnListAllDrivers);
        btnLogout = findViewById(R.id.btnLogout);

        Intent intent = getIntent();
        String received_username = intent.getStringExtra("username");
        txtUsername.setText("Hello " + received_username);

//        Search Driver | Button
        btnSearchDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SearchDriverActivity.class);
                startActivity(i);
            }
        });

//        List All Drivers | Button
        btnListAllDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListAllDriversActivity.class);
                startActivity(i);
            }
        });

//        log out | Button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Log out Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}