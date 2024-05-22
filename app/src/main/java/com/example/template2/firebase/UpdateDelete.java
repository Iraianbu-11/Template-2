package com.example.template2.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.template2.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateDelete extends AppCompatActivity {
    EditText Name, Last_name, Age, Phone , deluser;
    Button UpdateData;
    DatabaseReference ref;
    Helper member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Name = findViewById(R.id.name);
        Last_name = findViewById(R.id.last_name);
        Age = findViewById(R.id.age);
        Phone = findViewById(R.id.phone);
        UpdateData = findViewById(R.id.btn_insert);
        deluser = findViewById(R.id.delname);
        FirebaseApp.initializeApp(this);
        ref = FirebaseDatabase.getInstance().getReference().child("Member");
        member = new Helper();

        UpdateData.setOnClickListener(v -> {
            updateMemberData();
        });

        findViewById(R.id.btn_delete).setOnClickListener(v->{
            deleteUser();
        });
    }

    private void deleteUser() {
        String name = deluser.getText().toString().trim();
        if (!name.isEmpty()) {
            Query query = ref.orderByChild("name").startAt(name).endAt(name + "\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean found = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Helper memberData = snapshot.getValue(Helper.class);
                        if (memberData != null) {
                            found = true;
                            String memberId = snapshot.getKey();
                            ref.child(memberId).removeValue().addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UpdateDelete.this, "User Deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateDelete.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            // No need to break here, as we're deleting all instances of the member with the given name
                        }
                    }
                    if (!found) {
                        Toast.makeText(UpdateDelete.this, "Member Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UpdateDelete.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }


    private void updateMemberData() {
        String name = Name.getText().toString().trim();
        if (!name.isEmpty()) {
            Query query = ref.orderByChild("name").startAt(name).endAt(name + "\uf8ff");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean found = false;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Helper memberData = snapshot.getValue(Helper.class);
                        if (memberData != null) {
                            found = true;
                            String memberId = snapshot.getKey();
                            int age = Integer.parseInt(Age.getText().toString().trim());
                            long phone = Long.parseLong(Phone.getText().toString().trim());
                            String newName = Name.getText().toString().trim();
                            String newLastName = Last_name.getText().toString().trim();
                            member.setName(newName);
                            member.setLast_name(newLastName);
                            member.setAge(age);
                            member.setPhone(phone);
                            ref.child(memberId).setValue(member).addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UpdateDelete.this, "Data Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(UpdateDelete.this, "Update Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        }
                    }
                    if (!found) {
                        Toast.makeText(UpdateDelete.this, "Member Not Found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(UpdateDelete.this, "Database Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please enter both name and last name", Toast.LENGTH_SHORT).show();
        }
    }




}
