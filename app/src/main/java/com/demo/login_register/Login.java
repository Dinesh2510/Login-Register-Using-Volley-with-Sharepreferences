package com.demo.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText et_email, et_pwd;
    TextView txt1;
    Button login;
    String str_pwd, str_email,user_id,str_name,str_contact_no,str_address;

    public static final String SHARED_PREFERENCES_NAME = "login_portal";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "first_name";
    public static final String CONTACT_NO = "contact";
    public static final String EMAIL_ID = "email_id";
    public static final String PASSWORD = "password";
    public static final String ADDRESS = "address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        et_email = findViewById(R.id.l_email);
        et_pwd = findViewById(R.id.l_pwd);
        txt1 = findViewById(R.id.l_txt);
        login = findViewById(R.id.login);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_email = et_email.getText().toString().trim();
                str_pwd = et_pwd.getText().toString().trim();
                SendData(str_email, str_pwd);

            }
        });

    }


    private void SendData(final String str_email, final String str_pwd) {
        String tag_json_obj = "json_string_req";
        String url_path = "http://192.168.43.137/job_portal/app_login.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                showJSON(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "String:" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                final Map<String, String> params = new HashMap<String, String>();

                params.put("password", str_pwd);
                params.put("email", str_email);

                Log.d("check_params", "" + params);

                return params;
            }
        };


        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }

    private void showJSON(String response) {
        try {

            Log.d("check_response1", "" + response);

            JSONObject jsonObject = new JSONObject(response);
            String check = jsonObject.getString("response");

            JSONObject innerObj = new JSONObject(check);

            String user_id = innerObj.getString("user_id");
            String name = innerObj.getString("name");
            String phone = innerObj.getString("phone");
            String email = innerObj.getString("email");
            String address = innerObj.getString("address");

            Log.d("check_response", "" + check);
            Log.d("email", "" + email);

            if (!check.equalsIgnoreCase("faliure")) {

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_ID, user_id);
                editor.putString(PASSWORD, "");
                editor.putString(USER_NAME, name);
                editor.putString(CONTACT_NO, phone);
                editor.putString(EMAIL_ID, email);
                editor.putString(ADDRESS, address);
                editor.commit();

                Toast.makeText(this, "saved_successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Home.class);
                finish();
                startActivity(intent);
                Log.d("sdd", "" + user_id);



            } else {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
