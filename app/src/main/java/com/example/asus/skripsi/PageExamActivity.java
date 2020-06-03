package com.example.asus.skripsi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.threeten.bp.LocalTime;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class PageExamActivity extends AppCompatActivity {
    private long backpress;
    public RecyclerView rvSoal;
    TextView tvWaktu;
    String nim, id_ujian;
    SoalAdapter soalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_exam);

        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        nim = sharedPreferences.getString(AppVar.KEY_NIM, "");
        id_ujian = getIntent().getExtras().getString("id_ujian", "");

        tvWaktu = findViewById(R.id.tv_waktu);
        rvSoal = findViewById(R.id.rv_page_exam);
        rvSoal.setHasFixedSize(true);

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        getSoalUjian();
    }

    private void getSoalUjian() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.GETSOALUJIAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response", response);
                        ModelSoalUjian getSoalUjian = new Gson().fromJson(response, ModelSoalUjian.class);

                        rvSoal.setLayoutManager(new LinearLayoutManager(PageExamActivity.this));
                        soalAdapter = new SoalAdapter(PageExamActivity.this);
                        soalAdapter.setSoal(getSoalUjian.getData());

                        for (int i=0;i<getSoalUjian.getData().size();i++){
                            soalAdapter.getJawaban().add(new ModelInsertData.DataJawaban(getSoalUjian.getData().get(i).getIdDetailSoal(),"0","Tidak ada jawaban"));
                        }

                        rvSoal.setAdapter(soalAdapter);

                       LocalTime waktuSekarang = LocalTime.parse(getSoalUjian.getWaktuSekarang());
                       LocalTime waktuSelesai = LocalTime.parse(getSoalUjian.getWaktuSelesai());
                       Long mils = ChronoUnit.MILLIS.between(waktuSekarang, waktuSelesai);

                       new CountDownTimer(mils, 1000){

                           @Override
                           public void onTick(long millisUntilFinished) {
                               String text = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                       TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                       TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                                tvWaktu.setText(text);
                           }

                           @Override
                           public void onFinish() {

                           }
                       }.start();
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
                params.put(AppVar.KEY_IDUJIAN, getIntent().getExtras().getString("id_ujian"));
                params.put(AppVar.KEY_IDSOAL, getIntent().getExtras().getString("id_soal"));

                // returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void insertData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.INSERTDATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("responseketikainsert", response);
                        Intent finish = new Intent(PageExamActivity.this, FinishExamActivity.class);
                        startActivity(finish);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.getLocalizedMessage());
                    }
                }){

            @Override
            public byte[] getBody() throws AuthFailureError {
                    ModelInsertData modelInsertData = new ModelInsertData(nim, id_ujian, soalAdapter.getJawaban());

                return new Gson().toJson(modelInsertData).getBytes();
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Anda sudah di halaman ujian", Toast.LENGTH_SHORT).show();
    }
}
