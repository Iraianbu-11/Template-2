package com.example.template2.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.template2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Read extends AppCompatActivity {
    ListView listView;
    DatabaseReference ref;
    ArrayAdapter<String> adapter;
    ArrayList<String> memberDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        listView = findViewById(R.id.listView);
        ref = FirebaseDatabase.getInstance().getReference().child("Member");
        memberDetails = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, memberDetails);
        listView.setAdapter(adapter);
        // Read data from database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memberDetails.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Helper member = snapshot.getValue(Helper.class);
                    if (member != null) {
                        String details = "Name: " + member.getName() + "\n"
                                + "Last Name: " + member.getLast_name() + "\n"
                                + "Age: " + member.getAge() + "\n"
                                + "Phone: " + member.getPhone();
                        memberDetails.add(details);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });
    }
}
