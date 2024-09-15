package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TranslationActivity extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translation_activity);
    }

    public void popup(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_save,null));
        dialog = builder.create();
        dialog.show();
    }

    public void returnFromPopup(View view) {
        dialog.dismiss();
    }

}