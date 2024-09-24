package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    private EditText  email, password;
    private Button createAccount;
    private TextView resetPasswordTextView;
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
            startActivity(intent);
        });

    }
}
