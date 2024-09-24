package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText  email, password;
    private Button createAccount;
    private TextView resetPasswordTextView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//link the actvity with the layout

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccountBtn);


        resetPasswordTextView = findViewById(R.id.reset_password_textview);
//click listner for the TextView
        resetPasswordTextView.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResetPasswordActivity.class);
            startActivity(intent);
        });

        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserMainActivity.class);
            String ST_email = email.getText().toString();
            String ST_password = password.getText().toString();
            mAuth = FirebaseAuth.getInstance();
            Log.d("TAG", ST_email+" "+ST_password);
            mAuth.signInWithEmailAndPassword(ST_email.toString(), ST_password.toString()).addOnCompleteListener(Task ->{
                if (Task.isSuccessful()) {
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}
