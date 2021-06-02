package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private Pet myPet;

    ProgressBarManager progressBarManager;

    private int food;

    private TextView textViewRoom;

    private ImageButton buttonMenu;
    private Button buttonBedroom;
    private Button buttonBathroom;
    private Button buttonPlayroom;
    private Button buttonKitchen;
    private ImageButton buttonFood;
    private ImageButton buttonStore;
    private ImageButton buttonSleep;
    private ImageButton buttonPlay;
    private ImageButton buttonPet;
    private ImageButton buttonWash;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myPet = new Pet("Peter", 30,30,30,30,100,true,0);

        textViewRoom = findViewById(R.id.tvRoom);

        progressBarManager = new ProgressBarManager(this);
        progressBarManager.updateProgressbarHunger(30);

        //TODO In der Küche starten
        /**The Game starts in the Kitchen*/
        /*buttonKitchen.setEnabled(false);
        buttonSleep.setVisibility(View.INVISIBLE);
        buttonPlay.setVisibility(View.INVISIBLE);
        buttonPet.setVisibility(View.INVISIBLE);
        buttonWash.setVisibility(View.INVISIBLE);*/
        textViewRoom.setText("Küche");

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
                buttonKitchen.setEnabled(true);
                buttonBedroom.setEnabled(false);
                buttonBathroom.setEnabled(true);
                buttonPlayroom.setEnabled(true);
                buttonFood.setVisibility(View.INVISIBLE);
                buttonSleep.setVisibility(View.VISIBLE);
                buttonPlay.setVisibility(View.INVISIBLE);
                buttonPet.setVisibility(View.INVISIBLE);
                buttonWash.setVisibility(View.INVISIBLE);
                textViewRoom.setText("Schlafzimmer");

            }
        });

        buttonBathroom = findViewById(R.id.btnBathroom);
        buttonBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonKitchen.setEnabled(true);
                buttonBedroom.setEnabled(true);
                buttonBathroom.setEnabled(false);
                buttonPlayroom.setEnabled(true);
                buttonFood.setVisibility(View.INVISIBLE);
                buttonSleep.setVisibility(View.INVISIBLE);
                buttonPlay.setVisibility(View.INVISIBLE);
                buttonPet.setVisibility(View.INVISIBLE);
                buttonWash.setVisibility(View.VISIBLE);
                textViewRoom.setText("Badezimmer");
            }
        });

        buttonKitchen = findViewById(R.id.btnKitchen);
        buttonKitchen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonKitchen.setEnabled(false);
                buttonBedroom.setEnabled(true);
                buttonBathroom.setEnabled(true);
                buttonPlayroom.setEnabled(true);
                buttonFood.setVisibility(View.VISIBLE);
                buttonSleep.setVisibility(View.INVISIBLE);
                buttonPlay.setVisibility(View.INVISIBLE);
                buttonPet.setVisibility(View.INVISIBLE);
                buttonWash.setVisibility(View.INVISIBLE);
                textViewRoom.setText("Küche");
            }
        });

        buttonPlayroom = findViewById(R.id.btnPlayroom);
        buttonPlayroom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonKitchen.setEnabled(true);
                buttonBedroom.setEnabled(true);
                buttonBathroom.setEnabled(true);
                buttonPlayroom.setEnabled(false);
                buttonFood.setVisibility(View.INVISIBLE);
                buttonSleep.setVisibility(View.INVISIBLE);
                buttonPlay.setVisibility(View.VISIBLE);
                buttonPet.setVisibility(View.VISIBLE);
                buttonWash.setVisibility(View.INVISIBLE);
                textViewRoom.setText("Spielzimmer");
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
                progressBarManager.updateProgressbarHunger(25);
            }
        });

        buttonStore = findViewById(R.id.btnStore);
        buttonStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopMenu();
            }
        });

        buttonSleep = findViewById(R.id.btnSleep);
        buttonSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonPlay = findViewById(R.id.btnPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonPet = findViewById(R.id.btnPet);
        buttonPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonWash = findViewById(R.id.btnWash);
        buttonWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
