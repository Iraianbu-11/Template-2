package com.example.template2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText  email;
    EditText pass;
    Button button;
    TextView signup , forgotpass;
    FirebaseAuth auth;
    SharedPreferences sharedPreferences;

//    private  static final String prefName = "myapp";
//    private  static final String keyEmail = "email";
//    private  static final String keyPass = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        button = findViewById(R.id.button);
        signup = findViewById(R.id.signup);
        forgotpass = findViewById(R.id.forgotpass);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        //sharedPreferences = getSharedPreferences(prefName,MODE_PRIVATE);
        forgotpass.setOnClickListener(v->{
            Intent intent = new Intent(Login.this, ForgotPass.class);
            startActivity(intent);
        });
        button.setOnClickListener(v-> {
            checkUser();
//            SharedPreferences.Editor editor = sharedPreferences.edit();
////            editor.putString(keyEmail,email.getText().toString());
////            editor.putString(keyPass,pass.getText().toString());
//            //editor.apply();
        });

        signup.setOnClickListener(v->{
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

    }

    public void checkUser(){
        String usremail = email.getText().toString().trim();
        String usrpass = pass.getText().toString().trim();
        if(usremail.isEmpty()){
            email.setError("Email Empty");
        }

        if(usrpass.isEmpty()){
            pass.setError("Password Empty");
        }
        if(!usremail.isEmpty() && !usrpass.isEmpty()){
            auth.signInWithEmailAndPassword(usremail,usrpass).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(Login.this,MainActivity.class));
                            startActivity(new Intent(Login.this, MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}