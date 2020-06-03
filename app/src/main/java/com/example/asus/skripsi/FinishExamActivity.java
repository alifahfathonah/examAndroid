package com.example.asus.skripsi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FinishExamActivity extends AppCompatActivity {
    Button btnFinish;
    String nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_exam);

        SharedPreferences sharedPreferences = getSharedPreferences("akunLogin", Context.MODE_PRIVATE);
        nim = sharedPreferences.getString(AppVar.KEY_NIM, "");

        btnFinish = findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finish = new Intent(FinishExamActivity.this, MainActivity.class);
                startActivity(finish);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Anda sudah selesai ujian", Toast.LENGTH_SHORT).show();
    }
}
