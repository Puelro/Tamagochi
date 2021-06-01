package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private Pet myPet;

    ProgressBarManager progressBarManager;

    private int food;

    private ImageButton buttonMenu;
    private ImageButton buttonBedroom;
    private ImageButton buttonBathroom;
    private ImageButton buttonFood;
    private ImageButton buttonStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myPet = new Pet("Peter", 30,30,30,30,100,true,0);

        buttonMenu = findViewById(R.id.btnMenu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonBedroom = findViewById(R.id.btnBedroom);
        buttonBedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonBathroom = findViewById(R.id.btnBathroom);
        buttonBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonFood = findViewById(R.id.btnFood);
        /*buttonFood.setEnabled(false);
        if(food > 0){
            buttonFood.setEnabled(true);
        }*/
        buttonFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myPet.updateHunger(25);
                progressBarManager.updateProgressbarHunger(30);
            }
        });

        buttonStore = findViewById(R.id.btnStore);
        buttonStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopMenu();
            }
        });

    }

    //TODO Bilder für die Kaufoptionen einfügen
    public void shopMenu(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Shop");
        alertDialog.setCancelable(true);

        //alertDialog.setNeutralButtonIcon(getDrawable(R.drawable.download));

        alertDialog.create().show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Game.this,MainActivity.class);
        startActivity(intent);
    }
}
