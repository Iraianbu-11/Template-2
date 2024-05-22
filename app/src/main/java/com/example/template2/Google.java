package com.example.template2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class Google extends AppCompatActivity {
    private static final String TAG = "GoogleSignInActivity";
    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "onActivityResult called");
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            Log.d(TAG, "Result OK and data is not null");
                            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                            try {
                                GoogleSignInAccount signInAccount = accountTask.getResult(ApiException.class);
                                if (signInAccount != null) {
                                    Log.d(TAG, "Google SignIn Account retrieved");
                                    Log.d(TAG, "Google SignIn ID Token: " + signInAccount.getIdToken());
                                    AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
                                    auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "signInWithCredential:success");
                                                Toast.makeText(Google.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Google.this, MainActivity.class));
                                                finish(); // To prevent returning to the sign-in screen
                                            } else {
                                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                                Toast.makeText(Google.this, "Failed to sign in: " + task.getException(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Log.w(TAG, "Google SignIn Account is null");
                                }
                            } catch (ApiException e) {
                                Log.w(TAG, "Google sign-in failed", e);
                            }
                        } else {
                            Log.w(TAG, "Result data is null");
                        }
                    } else {
                        Log.w(TAG, "Result not OK, result code: " + result.getResultCode());
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        Log.d(TAG, "Initializing Firebase");
        FirebaseApp.initializeApp(this);

        Log.d(TAG, "Setting up GoogleSignInOptions");
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, options);

        Log.d(TAG, "Initializing FirebaseAuth");
        auth = FirebaseAuth.getInstance();

        SignInButton signInButton = findViewById(R.id.googleBtn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "SignInButton clicked");
                Intent signInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(signInIntent);
            }
        });
    }
}
