package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);


        Button drawRoute = findViewById(R.id.draw_route_button);
        drawRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsWindow();
            }
        });

        Button options = findViewById(R.id.btn_options2);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptions();
            }
        });

        Button quitButton = findViewById(R.id.btn_quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and exit the application
            }
        });


    }

    public void openOptionsWindow() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openOptions() {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}

