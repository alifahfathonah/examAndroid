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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormToken extends AppCompatActivity {
    public ModelDaftarUjianData modelDaftarUjianData;
    TextView txtMatkul, txtTanggal, txtWaktuUjian;
    EditText edToken;
    Button btnStart;
    private Context context;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_token);
        modelDaftarUjianData = getIntent().getExtras().getParcelable("enter_ujian");

        context = FormToken.this;
        pDialog = new ProgressDialog(context);
        txtMatkul = findViewById(R.id.tv_courses);
        txtTanggal = findViewById(R.id.date);
        txtWaktuUjian = findViewById(R.id.time);

        txtMatkul.setText(modelDaftarUjianData.getNamaMatkul());
        txtTanggal.setText(modelDaftarUjianData.getTglUjian());
        txtWaktuUjian.setText(modelDaftarUjianData.getWaktuMulaiUjian());

        edToken = findViewById(R.id.edt_token);
        btnStart = findViewById(R.id.btn_start_exam);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                token();
            }
        });
    }

    private void token() {
        final String token_ujian = edToken.getText().toString().trim();
        final String id_ujian = modelDaftarUjianData.getIdUjian();
        final String id_soal = modelDaftarUjianData.getIdSoal();

        pDialog.setMessage("Proses Mulai Ujian");
        showDialog();

        // Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.TOKEN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response", response);

                        // if we are getting success from server
                        if (response.contains(AppVar.TOKEN_SUCCESS)) {
                            hideDialog();
                            Toast.makeText(context, "Token Valid", Toast.LENGTH_SHORT).show();

//                            gotoPageExam();
                            startActivity(new Intent(context, PageExamActivity.class).putExtra("id_ujian", id_ujian).putExtra("id_soal", id_soal));
                        } else {
                            hideDialog();
                            // Displaying an error message on toast
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // You can handle error here if you want
                        hideDialog();
                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // Adding parameters to request
                params.put(AppVar.KEY_TOKEN, token_ujian);
                params.put(AppVar.KEY_IDUJIAN, id_ujian);
                return params;
            }
        };
        // Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
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
}
