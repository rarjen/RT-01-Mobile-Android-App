package com.example.aplikasirt01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void back(View view) {
        Intent Intent = new Intent(info.this, Menu_utama.class);
        startActivity(Intent);
    }
}