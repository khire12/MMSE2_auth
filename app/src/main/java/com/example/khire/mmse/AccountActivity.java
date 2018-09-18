package com.example.khire.mmse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button logout;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        editText = findViewById(R.id.emailShowtext);





      if(mAuth.getCurrentUser()!=null){
          editText.setText("Welcome "+ mAuth.getCurrentUser().getEmail());
      }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });







    }


    private void logoutUser(){
        mAuth.signOut();
        if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(AccountActivity.this,SignupActivity.class));
            finish();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        logoutUser();
    }
}
