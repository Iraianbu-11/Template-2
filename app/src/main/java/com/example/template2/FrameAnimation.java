package com.example.template2;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


public class FrameAnimation extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        imageView = findViewById(R.id.imageView);
        // Start the animation
        imageView.setImageResource(R.drawable.frame_animation);
        ((AnimationDrawable) imageView.getDrawable()).start();
    }
}