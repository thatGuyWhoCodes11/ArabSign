package com.example.myapplication;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SharedUtils {

    public static void setUserGreeting(TextView tv,String extraString){
        FirebaseFirestore fsdb = FirebaseFirestore.getInstance();
        fsdb.collection("users")
                .document(""+ FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener((value, error) -> {
                    if (error!=null){
                        return;
                    }

                    if (value!=null && value.exists()){
                        CharSequence greeting = value.getString("username");
                        tv.setText(extraString+" ");
                        tv.append(greeting);
                    }
                });
    }

    public static void setUserGreeting(TextView tv){
        FirebaseFirestore fsdb = FirebaseFirestore.getInstance();
        fsdb.collection("users")
                .document(""+ FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener((value, error) -> {
                    if (error!=null){
                        return;
                    }

                    if (value!=null && value.exists()){
                        String greeting = value.getString("username");
                        tv.setText(greeting);
                    }
                });
    }


}
