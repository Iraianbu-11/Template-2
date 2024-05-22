package com.example.template2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.template2.firebase.Create;
import com.example.template2.firebase.UpdateDelete;
import com.example.template2.firebase.Upload;
import com.example.template2.firebase.Read;

public class Crud extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        findViewById(R.id.create).setOnClickListener(v->{
            startActivity(new Intent(this, Create.class));
        });

        findViewById(R.id.updateDel).setOnClickListener(v->{
            startActivity(new Intent(this, UpdateDelete.class));
        });

        findViewById(R.id.upload).setOnClickListener(v->{
            startActivity(new Intent(this, Upload.class));
        });

        findViewById(R.id.read).setOnClickListener(v->{
            startActivity(new Intent(this, Read.class));
        });
    }
}