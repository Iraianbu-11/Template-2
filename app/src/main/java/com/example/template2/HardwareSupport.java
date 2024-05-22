package com.example.template2;
import com.example.template2.hardware.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HardwareSupport extends AppCompatActivity {
    Button bluetooth;
    Button wifi;
    Button sensor;
    Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware_support);
        bluetooth = findViewById(R.id.bluetooth);
        wifi = findViewById(R.id.wifi);
        sensor = findViewById(R.id.sensor);
        camera = findViewById(R.id.camera);
        bluetooth.setOnClickListener(v -> {
            Intent intent = new Intent(this,Bluetooth.class);
            startActivity(intent);
        });

        wifi.setOnClickListener(v -> {
            Intent intent = new Intent(this,Wifi.class);
            startActivity(intent);
        });

        camera.setOnClickListener(v -> {
            Intent intent = new Intent(this,Camera.class);
            startActivity(intent);
        });

        sensor.setOnClickListener(v -> {
            Intent intent = new Intent(this,Sensor.class);
            startActivity(intent);
        });

        findViewById(R.id.gesture).setOnClickListener(v->{
            startActivity(new Intent(this,Gesture.class));
        });


    }
}