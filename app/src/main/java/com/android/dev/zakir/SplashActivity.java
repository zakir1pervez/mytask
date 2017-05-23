package com.android.dev.zakir;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseAppActivity {

    public SplashActivity() {
        super(R.layout.activity_splash);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) checkPermissionInSplash();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)checkPermissionInSplash();
                break;
        }
    }

     public void checkPermissionInSplash(){
         if (checkPermission()) {
             Timer timer = new Timer();
             timer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                     navigate(SplashActivity.this, HomeActivity.class, null, 0, 0);
                     finish();
                 }
             }, 3000);//halt for 3 second
         } else {
             requestPermission();
         }
     }
}
