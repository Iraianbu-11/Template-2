package com.example.template2.hardware;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.template2.R;

public class Camera extends AppCompatActivity {

    private static final int pic_id = 123;
    Button button;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        button = findViewById(R.id.camera_button);
        image = findViewById(R.id.click_image);

        button.setOnClickListener(v->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);;
            startActivityForResult(intent,pic_id);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == pic_id){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(photo);
        }
    }
}