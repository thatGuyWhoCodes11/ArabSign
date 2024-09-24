package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;


public class CreateAccountActivity extends AppCompatActivity {
    private EditText username, email, password, confirmPassword;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
//link the actvity with the layout
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        createAccount = findViewById(R.id.createAccountBtn);


        createAccount.setOnClickListener(v -> {
            //check the password

//            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
//                String usernameText = username.getText().toString();
//                String emailText = email.getText().toString();
//                String passwordText = password.getText().toString();
//                String confirmPasswordText = confirmPassword.getText().toString();
//            }

            Intent intent = new Intent(this, LoginCreatAccActivity.class);
            startActivity(intent);

        });
    }

}
