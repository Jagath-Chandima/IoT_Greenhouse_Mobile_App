package com.example.smartgreenhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutoRefilling extends AppCompatActivity {
    Button pumpOn,pumpOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_refilling);

        pumpOn=findViewById(R.id.pumpOn);
        pumpOff=findViewById(R.id.pumpOff);

        pumpOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Refill-pump");

                myRef.setValue(1);
            }
        });
        pumpOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Refill-pump");

                myRef.setValue(0);
            }
        });
    }
}