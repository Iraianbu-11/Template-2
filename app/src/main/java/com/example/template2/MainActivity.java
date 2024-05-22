package com.example.template2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.example.template2.sqlite.Profile;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        findViewById(R.id.firebase).setOnClickListener(v->{
            startActivity(new Intent(this, Crud.class));
        });

        findViewById(R.id.sqlite).setOnClickListener(v->{
            startActivity(new Intent(this, Profile.class));
        });

        findViewById(R.id.hardware).setOnClickListener(v->{
            startActivity(new Intent(this, HardwareSupport.class));
        });

        findViewById(R.id.sharedPref).setOnClickListener(v->{
            startActivity(new Intent(this, com.example.template2.sharepref.MainActivity.class));
        });


        findViewById(R.id.animations).setOnClickListener(v->{
            startActivity(new Intent(this, FrameAnimation.class));
        });


    }
}