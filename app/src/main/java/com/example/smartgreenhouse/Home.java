package com.example.smartgreenhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView dateView,temperatureView,humidityView,soilView,lightView;;
    FloatingActionButton floatingActionButton,refilling,watering,autoDoor;
    FirebaseAuth firebaseAuth;
    ImageView temphis,Humhis,soilHis,lightHis;
    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dateView=findViewById(R.id.dateView);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        refilling=findViewById(R.id.refilling);
        watering=findViewById(R.id.watering);
        autoDoor=findViewById(R.id.autoDoor);
        dateView=findViewById(R.id.dateView);
        drawerLayout=findViewById(R.id.drawer_layout);
        temperatureView=findViewById(R.id.temperatureView);
        humidityView=findViewById(R.id.humidityView);
        soilView=findViewById(R.id.soilView);
        lightView=findViewById(R.id.lightView);
        temphis=findViewById(R.id.temphis);
        Humhis=findViewById(R.id.Humhis);
        soilHis=findViewById(R.id.soilHis);
        lightHis=findViewById(R.id.lightHis);

        dref= FirebaseDatabase.getInstance().getReference().child("Greenhouse01");
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String temp =datasnapshot.child("temperature").getValue().toString();
                temperatureView.setText(temp);

                String hum =datasnapshot.child("humidity").getValue().toString();
                humidityView.setText(hum);

                String soil =datasnapshot.child("soil").getValue().toString();
                soilView.setText(soil);

                String light =datasnapshot.child("light").getValue().toString();
                lightView.setText(light);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Calendar calendar= Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        dateView.setText(currentDate);
        firebaseAuth=FirebaseAuth.getInstance();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Note.class);
                startActivity(intent);
            }
        });

        refilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, AutoRefilling.class);
                startActivity(intent);
            }
        });

        watering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, AutoWatering.class);
                startActivity(intent);
            }
        });

        autoDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, AutoDoor.class);
                startActivity(intent);
            }
        });
        temphis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, TemperatureHis.class);
                startActivity(intent);
            }
        });
        Humhis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, HumidityHis.class);
                startActivity(intent);
            }
        });
        soilHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, SoilmeasureHis.class);
                startActivity(intent);
            }
        });
        lightHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, LightingHis.class);
                startActivity(intent);
            }
        });
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickProfile(View view){
        redirectActivity(this,Profile.class);
    }
    public void ClickReports(View view){
        redirectActivity(this,Reports.class);
    }
    public void ClickNotification(View view){
        redirectActivity(this,Notification.class);
    }
    public void ClickManual(View view){
        redirectActivity(this,Manual.class);
    }
    public void ClickLogout(View view){
        logout(this);
    }

    private static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();

            }
        });
        builder.show();
    }


    private static void redirectActivity(Activity activity,Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}