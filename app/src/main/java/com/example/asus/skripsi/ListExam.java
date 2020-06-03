package com.example.asus.skripsi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListExam extends AppCompatActivity {
    public RecyclerView rvExam;
    String nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam);

        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        nim = sharedPreferences.getString(AppVar.KEY_NIM, "");

        rvExam = findViewById(R.id.rv_card_exam);
        rvExam.setHasFixedSize(true);

        getDaftarUjian();
    }

    private void getDaftarUjian() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.GETDAFTARUJIAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        ModelDaftarUjian getDaftarUjian = new Gson().fromJson(response, ModelDaftarUjian.class);

                        rvExam.setLayoutManager(new LinearLayoutManager(ListExam.this));
                        ExamAdapter examAdapter = new ExamAdapter(ListExam.this);
                        examAdapter.setExams(getDaftarUjian.getData());
                        rvExam.setAdapter(examAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.getLocalizedMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
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
