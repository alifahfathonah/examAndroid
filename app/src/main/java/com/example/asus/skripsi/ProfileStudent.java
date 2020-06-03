package com.example.asus.skripsi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;


public class ProfileStudent extends AppCompatActivity {
    EditText edtNim, edtNama, edtProdi, edtSemester;
    String nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);

        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        nim = sharedPreferences.getString(AppVar.KEY_NIM, "");
        Log.d("NIM", nim);

        edtNim = findViewById(R.id.edt_nim);
        edtNama = findViewById(R.id.edt_nama);
        edtProdi = findViewById(R.id.edt_prodi);
        edtSemester = findViewById(R.id.edt_semester);

        getMahasiswa();

    }

    private void getMahasiswa() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.GETMAHASISWA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        ModelgetMahasiswa getMhs = new Gson().fromJson(response, ModelgetMahasiswa.class);

                        edtNim.setText(getMhs.getNim());
                        edtNama.setText(getMhs.getNamaMhs());
                        edtProdi.setText(getMhs.getNamaProdi());
                        edtSemester.setText(getMhs.getSemester());

                        Log.e("Data", String.valueOf(edtNim));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                // Adding parameters to request
                params.put(AppVar.KEY_NIM, nim);

                // returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
