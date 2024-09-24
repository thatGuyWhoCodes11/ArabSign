package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class TranslationActivity extends AppCompatActivity {

    AlertDialog dialog;
    private ActivityResultLauncher<String> requestPermission;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission
        = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted->{
            if (isGranted){
                cameraActivate();
            }
            else{
                customPopup(R.layout.dialog_permission_denied);
            }
        });
        setContentView(R.layout.translation_activity);
        requestCamera();
    }

    public void toActivity(View v){
        Intent intent = new Intent(this,UserMainActivity.class);
        startActivity(intent);
    }

    public void popup(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.getContext().setTheme(R.style.dialogradius);
        builder.setView(inflater.inflate(R.layout.dialog_save,null));
        dialog = builder.create();
        dialog.show();
    }

    public void customPopup(int layout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        builder.getContext().setTheme(R.style.dialogradius);
        builder.setView(inflater.inflate(layout,null));
        dialog = builder.create();
        dialog.show();
    }

    public void returnFromPopup(View view) {
        dialog.dismiss();
    }

    public void outOfActivity(View view){
        returnFromPopup(view);
        Intent intent = new Intent(this,UserMainActivity.class);
        startActivity(intent);
    }

//    public void reRequestCamera(View view){
//        returnFromPopup(view);
//        requestPermission.launch(Manifest.permission.CAMERA);
//        requestCamera();
//    }

    private void requestCamera(){

        boolean allowed = ContextCompat.checkSelfPermission(
                getApplicationContext(),Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
//        boolean rationale =
//        ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA);
        if (allowed){
            cameraActivate();
        }
//        else if (rationale){
//
//        }
        else{
            requestPermission.launch(Manifest.permission.CAMERA);
            }
        }

    private void cameraActivate(){
        try {
            cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        }catch (NullPointerException e){
            int x = 37;
        }
        cameraProviderFuture.addListener(()->{
            try{
                ProcessCameraProvider camProcessProvider
                        = cameraProviderFuture.get();
                cameraStart(camProcessProvider);
            }
            catch (ExecutionException | InterruptedException camE){

            }
        },ContextCompat.getMainExecutor(this));
    }

    private void cameraStart(ProcessCameraProvider camProcessProvider){
        PreviewView camPV = findViewById(R.id.camPV);
        try {
            Preview camPreview = new Preview.Builder().build();
            CameraSelector camSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
            camPreview.setSurfaceProvider(camPV.getSurfaceProvider());
            camProcessProvider.unbindAll();
            Camera cam =
                    camProcessProvider
                    .bindToLifecycle(this,camSelector,camPreview);
        }
        catch (NullPointerException npe){

        }
    }

}