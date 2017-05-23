package com.android.dev.zakir;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class BaseAppActivity extends AppCompatActivity {
    private int viewId;
    public static final int PERMISSION_REQUEST_CODE = 1;

    public BaseAppActivity(int viewId) {
        this.viewId = viewId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        View viewObj = getLayoutInflater().inflate(viewId, null);
        setContentView(viewObj);
    }


    public void navigate(Context context, Class toClass, Bundle bundle, int flag, int backToActivityResult) {
        Intent intent = new Intent(context, toClass);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flag > 0) {
            intent.addFlags(flag);
        }
        if (backToActivityResult > 0) {
            startActivityForResult(intent, backToActivityResult);
        } else {
            startActivity(intent);
        }
    }


    public void showToast(String msg, int duration){
        if (duration == 1){
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method is used to grant request permission at runtime.
     */
//    public void requestPermission(){
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
//                Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
//    }

    /**
     * This method is use to ask permission if OS is greater than L
     */
    public void requestPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }


    /**
     * This method is use to check whether permission is required or not.
     * @return - True or False.
     */
    public boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
}
