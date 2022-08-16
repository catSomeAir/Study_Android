package com.example.project02_iot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.project02_iot.member.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {     //new Handler().postDelayed("인터페이스- new Runnable","지연")
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}