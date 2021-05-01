package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Bedroom extends AppCompatActivity {

    private ImageButton buttonKitchen;
    private ImageButton buttonPlayroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom);

        buttonKitchen = findViewById(R.id.btnKitchenRight);
        buttonKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bedroom.this,Kitchen.class);
                startActivity(intent);
            }
        });

        buttonPlayroom = findViewById(R.id.btnPlayroomLeft);
        buttonPlayroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bedroom.this,Playroom.class);
                startActivity(intent);
            }
        });

    }

}
