package com.demo.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.demo.login_register.Login.ADDRESS;
import static com.demo.login_register.Login.EMAIL_ID;
import static com.demo.login_register.Login.SHARED_PREFERENCES_NAME;
import static com.demo.login_register.Login.USER_ID;
import static com.demo.login_register.Login.USER_NAME;

public class Home extends AppCompatActivity {
    TextView home_name,home_userid,home_email,home_address;
    SharedPreferences sharedPreferences;
    String str_name,str_email,str_userid,str_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        str_name = sharedPreferences.getString(USER_NAME, "");
        str_email = sharedPreferences.getString(EMAIL_ID, "");
        str_address = sharedPreferences.getString(ADDRESS, "");
        str_userid = sharedPreferences.getString(USER_ID, "");


        home_name = findViewById(R.id.h_name);
        home_userid = findViewById(R.id.h_userid);
        home_email = findViewById(R.id.h_email);
        home_address = findViewById(R.id.h_address);


        home_name.setText(str_name);
        home_userid.setText(str_userid);
        home_email.setText(str_email);
        home_address.setText(str_address);

        Button button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
