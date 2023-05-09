package com.example.smartgreenhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AutoDoor extends AppCompatActivity {
    Button doorOpen,doorClose;
    TextView doorHistory;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_door);

        doorOpen=findViewById(R.id.doorOpen);
        doorClose=findViewById(R.id.doorClose);
        doorHistory=findViewById(R.id.doorHistory);
        dref=FirebaseDatabase.getInstance().getReference().child("Greenhouse01");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String temp =datasnapshot.child("Door").getValue().toString();
                doorHistory.setText(temp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        doorOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Door");

                myRef.setValue(1);
            }
        });
        doorClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Greenhouse01/Door");

                myRef.setValue(0);
            }
        });
    }
}