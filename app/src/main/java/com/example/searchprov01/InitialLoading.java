package com.example.searchprov01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class InitialLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_loading);
        Handler handler = new Handler();
        handler.post(() -> {
            startActivity(new Intent(InitialLoading.this, MainActivity.class));
            finish();
        });
    }
}