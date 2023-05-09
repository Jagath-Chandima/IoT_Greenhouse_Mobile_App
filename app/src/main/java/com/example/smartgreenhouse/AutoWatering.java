package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutoWatering extends AppCompatActivity {
    Button wateringOn,wateringOff;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_watering);
        wateringOn=findViewById(R.id.wateringOn);
        wateringOff=findViewById(R.id.wateringOff);

        wateringOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Watering");

                myRef.setValue("on");
            }
        });
        wateringOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Watering");

                myRef.setValue("off");
            }
        });
    }
}