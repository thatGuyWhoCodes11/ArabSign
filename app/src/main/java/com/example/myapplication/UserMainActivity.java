package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.UserMainActivityBinding;
import com.google.android.material.navigation.NavigationBarView;

public class UserMainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    AlertDialog dialog;
    NavigationBarView navigationBar_v;
    UserMainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserMainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigationBar_v = findViewById(R.id.nav_view);
        navigationBar_v.setOnItemSelectedListener(this);
        replaceFragment(new HomeFragment());
    }
    public void toActivity(View view) {
        Intent intent = new Intent(this, TranslationActivity.class);
        startActivity(intent);
    }

    public void popup(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_input,null));
        dialog = builder.create();
        dialog.show();
    }

    public void returnFromPopup(View view) {
        dialog.dismiss();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.navigation_home){
            replaceFragment(new HomeFragment());
            return true;
        }
        if(item.getItemId() == R.id.navigation_chat) {
            replaceFragment(new HistoryFragment());
            return true;
        }
        if(item.getItemId() == R.id.navigation_profile){
            replaceFragment(new ProfileFragment());
            return true;
        }
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_view, fragment).addToBackStack("custom");
        ft.commit();
    }
}
