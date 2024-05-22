package com.example.template2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText  email , pass , pass1;
    AppCompatButton button;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pass1 = findViewById(R.id.password1);
        button = findViewById(R.id.button);
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        button.setOnClickListener(v->{
            String usremail = email.getText().toString();
            String usrpass = pass.getText().toString();
            String repass = pass1.getText().toString();
            if(usrpass.equals(repass)){
                auth.createUserWithEmailAndPassword(usremail,usrpass).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SignUp.this, "Verify the Email Address", Toast.LENGTH_SHORT).show();
                                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(SignUp.this,Login.class);
                                                    startActivity(intent);
                                                }
                                            }
                                    );

                                }
                                else{
                                    Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
            else{
                Toast.makeText(this, "Passwords doesn't Match", Toast.LENGTH_SHORT).show();
            }
        });

    }
}