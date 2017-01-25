package com.m.colourgram;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.m.colourgram.utils.L;
import com.m.colourgram.utils.RealmHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * introduce page
 */
public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        RealmHelper helper = new RealmHelper(this);
        helper.init();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);




    }
    private List<String> getPermissionsToRequest() {
        List<String> permissions = new ArrayList<>();

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        return permissions;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        L.d("requestCode : " + requestCode);
        switch (requestCode) {

//            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
//
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 권한 허가
//                    // 해당 권한을 사용해서 작업을 진행할 수 있습니다
//                } else {
//                    // 권한 거부
//                    // 사용자가 해당권한을 거부했을때 해주어야 할 동작을 수행합니다
//                }
//                return;
        }
    }
}
