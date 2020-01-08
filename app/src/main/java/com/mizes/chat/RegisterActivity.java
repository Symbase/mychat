package com.mizes.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loading);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        // запускаем длительную операцию
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }
}
