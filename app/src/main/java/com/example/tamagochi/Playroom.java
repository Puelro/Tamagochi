package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Playroom extends AppCompatActivity {

    private ImageButton buttonBedroom;
    private ImageButton buttonBathroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playroom);

        buttonBedroom = findViewById(R.id.btnBedroomRight);
        buttonBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Playroom.this, Bedroom.class);
                startActivity(intent);
            }
        });

        buttonBathroom = findViewById(R.id.btnBathroomLeft);
        buttonBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Playroom.this, Bathroom.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Playroom.this,MainActivity.class);
        startActivity(intent);
    }

}
