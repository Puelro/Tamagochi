package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Bathroom extends AppCompatActivity {

    private ImageButton buttonKitchen;
    private ImageButton buttonPlayroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);

        buttonKitchen = findViewById(R.id.btnKitchenLeft);
        buttonKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bathroom.this,Kitchen.class);
                startActivity(intent);
            }
        });

        buttonPlayroom = findViewById(R.id.btnPlayroomRight);
        buttonPlayroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bathroom.this,Playroom.class);
                startActivity(intent);
            }
        });

    }

}
