package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);


    Button quitButton = findViewById(R.id.btn_quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(); // Close the activity and exit the application
        }
    });
    }
}