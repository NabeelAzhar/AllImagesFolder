package com.naturewallpaper.allimagesfolder.activites;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.naturewallpaper.allimagesfolder.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
 Button main_button;
 String[] RequirePermission ={Manifest.permission.WRITE_EXTERNAL_STORAGE,
         Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
      int CODE=12;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_button=findViewById(R.id.main_button);
        setPermission();
        main_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent= new Intent(MainActivity.this,ImageActivity.class);
               startActivity(intent);
           }
       });

    }
    public  boolean setPermission(){
        List<String> PermissionList=new ArrayList<>();
        for (String wellCome:RequirePermission){
            if (ContextCompat.checkSelfPermission(this,wellCome)!= PackageManager.PERMISSION_GRANTED);{
                PermissionList.add(wellCome);
            }

        }
        if (!PermissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,PermissionList.toArray(new String[PermissionList.size()]),CODE);
            return false;
        }

      return  true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==CODE){
            HashMap<String,Integer> premissionResult=new HashMap<>();
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i]==PackageManager.PERMISSION_DENIED);
                premissionResult.put(permissions[i],grantResults[i]);
            }
        }
    }
}
