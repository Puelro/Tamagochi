package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Kitchen extends AppCompatActivity {

    private ImageButton buttonBedroom;
    private ImageButton buttonBathroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        buttonBedroom = findViewById(R.id.btnBedroomLeft);
        buttonBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kitchen.this, Bedroom.class);
                startActivity(intent);
            }
        });

        buttonBathroom = findViewById(R.id.btnBathroomRight);
        buttonBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Kitchen.this, Bathroom.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Kitchen.this,MainActivity.class);
        startActivity(intent);
    }

}
