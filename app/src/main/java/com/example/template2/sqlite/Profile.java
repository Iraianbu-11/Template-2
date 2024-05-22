package com.example.template2.sqlite;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.template2.R;

public class Profile extends AppCompatActivity {
    EditText name, email,phone;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        name = findViewById(R.id.usrname);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        insert = findViewById(R.id.create_btn);
        update = findViewById(R.id.update_btn);
        delete = findViewById(R.id.delete_btn);
        view = findViewById(R.id.read_btn);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = name.getText().toString();
                String usremail = email.getText().toString();
                String usrphone = phone.getText().toString();

                Boolean checkinsertdata = DB.insertData(usrname, usremail, usrphone);
                if(checkinsertdata==true){
                    Toast.makeText(Profile.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                simpleNotification();
                }
                else {
                    Toast.makeText(Profile.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show(); }
            }        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usrname = name.getText().toString();
                String usremail = email.getText().toString();
                String usrphone = phone.getText().toString();

                Boolean checkupdatedata = DB.updateData(usrname, usremail, usrphone);
                if(checkupdatedata==true) {
                    simpleNotification();
                    Toast.makeText(Profile.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Profile.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show(); }
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = name.getText().toString();
                Boolean checkudeletedata = DB.deleteUser(usrname);
                if(checkudeletedata==true) {
                    Toast.makeText(Profile.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                    simpleNotification();
                }
                else {
                    Toast.makeText(Profile.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = name.getText().toString();
                Cursor res = DB.getUsers();
                if(res.getCount()==0){
                    Toast.makeText(Profile.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Email: "+res.getString(2)+"\n");
                    buffer.append("Phone: "+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                builder.setCancelable(true);
                builder.setTitle("Users Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }

    private void simpleNotification() {
        String channelId = "simple_channel";
        String channelName = "Simple Channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel for simple notifications");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_baseline_phone_android_24)
                .setContentTitle("SQLite")
                .setContentText("Operation Sucess")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true); // Automatically dismiss the notification when clicked

        notificationManager.notify(1, mbuilder.build());
    }

}
