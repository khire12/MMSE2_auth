package com.example.khire.mmse;

import android.accounts.Account;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton,signupPageButton;
    private EditText emailTextL,passwordTextL;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, AccountActivity.class));
            finish();
        }

        loginButton = findViewById(R.id.loginButton);
        signupPageButton = findViewById(R.id.signupPageButton);
        emailTextL = findViewById(R.id.emailTextL);
        passwordTextL = findViewById(R.id.passwordTextL);
        progressBar = findViewById(R.id.progressBar3);




        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!= null) {
                    Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };*/

        signupPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                    finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailTextL.getText().toString();
                final String password = passwordTextL.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                       if(!task.isSuccessful()){
                           if (password.length() < 6) {
                               passwordTextL.setError("Password length must be >=6");
                           } else {
                               Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                           }
                       }
                       else{
                           Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                          startActivity(intent);
                           finish();
                       }

                    }
                });
            }
        });
    }

    //@Override
    /*public void onBackPressed() {
        super.onBackPressed();
        if(mAuth.getCurrentUser()!=null){
            mAuth.signOut();

            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }
        else{
            finish();
        }
    }*/


    @Override
    protected void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
