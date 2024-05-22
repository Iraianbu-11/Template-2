package com.example.template2.hardware;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.template2.R;
public class Sensor extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        button = findViewById(R.id.sensorButton);
        button.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
                    super.onLongPress(e);
                }

                @Override
                public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    Toast.makeText(getApplicationContext(), "Double Tap", Toast.LENGTH_SHORT).show();
                    return super.onDoubleTap(e);
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });
    }
}