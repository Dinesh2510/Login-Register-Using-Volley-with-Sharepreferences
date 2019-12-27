package com.demo.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import static com.demo.login_register.Login.SHARED_PREFERENCES_NAME;
import static com.demo.login_register.Login.USER_ID;
import static com.demo.login_register.Login.USER_NAME;

public class Splash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        user_id = sharedPreferences.getString(USER_ID, "");

        load();

    }

    public void load() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!user_id.equals("") ) {
                    Intent i = new Intent(Splash.this, Home.class);
                    finish();
                    startActivity(i);

                } else {
                    Intent i = new Intent(Splash.this, Login.class);
                    finish();
                    startActivity(i);
                }

            }
        }, 2000);

    }}