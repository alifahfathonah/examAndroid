package com.example.asus.skripsi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormLogin extends AppCompatActivity {
    private long backpress;
    EditText edUsername, edPassword;
    private Context context;
    private ProgressDialog pDialog;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        context = FormLogin.this;

        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(AppVar.KEY_LOGIN, false)){
            startActivity(new Intent(FormLogin.this, MainActivity.class));
            finish();
            return;
        }

        // Initializing views
        pDialog = new ProgressDialog(context);
        edUsername = findViewById(R.id.edt_username);
        edPassword = findViewById(R.id.edt_password);
        btn = findViewById(R.id.btn_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        final String username = edUsername.getText().toString().trim();
        final String password = edPassword.getText().toString().trim();
        pDialog.setMessage("Login Proses");
        showDialog();
        
        // Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);

                        // if we are getting success from server
                        if (response.contains(AppVar.LOGIN_SUCCESS)) {
                            hideDialog();
                            gotoMainActivity();
                        } else {
                            hideDialog();
                            // Displaying an error message on toast
                            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response", String.valueOf(error));
                        // You can handle error here if you want
                        hideDialog();
                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                // Adding parameters to request
                params.put(AppVar.KEY_USERNAME, username);
                params.put(AppVar.KEY_PASSWORD, password);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(AppVar.KEY_NIM, edUsername.getText().toString()).apply();
        sharedPreferences.edit().putBoolean(AppVar.KEY_LOGIN, true).apply();
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (backpress + 2000 > System.currentTimeMillis()){
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }else {
            Toast.makeText(getApplicationContext(), "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        backpress = System.currentTimeMillis();
    }
}
