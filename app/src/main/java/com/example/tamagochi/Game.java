package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {

    private Pet myPet;

    ProgressBarManager progressBarManager;

    /**Number of food-supplies*/
    private int food;

    /**TextView to indicate the current room*/
    private TextView textViewRoom;

    /**LinearLayout to change the Background*/
    LinearLayout root;

    /**Buttons*/
    private Button buttonBedroom;
    private Button buttonBathroom;
    private Button buttonPlayroom;
    private Button buttonKitchen;
    private ImageButton buttonMenu;
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
        root=(LinearLayout)findViewById(R.id.root);

        progressBarManager = new ProgressBarManager(this);
        progressBarManager.updateProgressbarHunger(30);

        updateProgressbarAll();

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
                enableAllRoomButtons();
                buttonBedroom.setEnabled(false);
                makeAllInteractionButtonsInvisible();
                buttonSleep.setVisibility(View.VISIBLE);
                textViewRoom.setText("Schlafzimmer");
                root.setBackgroundResource(R.drawable.bedroom);

            }
        });

        buttonBathroom = findViewById(R.id.btnBathroom);
        buttonBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAllRoomButtons();
                buttonBathroom.setEnabled(false);
                makeAllInteractionButtonsInvisible();
                buttonWash.setVisibility(View.VISIBLE);
                textViewRoom.setText("Badezimmer");
                root.setBackgroundResource(R.drawable.bathroom);
            }
        });

        buttonKitchen = findViewById(R.id.btnKitchen);
        buttonKitchen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                enableAllRoomButtons();
                buttonKitchen.setEnabled(false);
                makeAllInteractionButtonsInvisible();
                buttonFood.setVisibility(View.VISIBLE);
                textViewRoom.setText("Küche");
                root.setBackgroundResource(R.drawable.kitchen);
            }
        });

        buttonPlayroom = findViewById(R.id.btnPlayroom);
        buttonPlayroom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                enableAllRoomButtons();
                buttonPlayroom.setEnabled(false);
                makeAllInteractionButtonsInvisible();
                buttonPlay.setVisibility(View.VISIBLE);
                buttonPet.setVisibility(View.VISIBLE);
                textViewRoom.setText("Spielzimmer");
                root.setBackgroundResource(R.drawable.playroom);
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
                if(myPet.getHunger() < 100) {
                    myPet.updateHunger(20);
                    updateProgressbarAll();
                }
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
                //TODO MiniGame
            }
        });

        buttonPet = findViewById(R.id.btnPet);
        buttonPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPet.updateHappiness(5);
                updateProgressbarAll();
            }
        });

        buttonWash = findViewById(R.id.btnWash);
        buttonWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPet.updateCleanliness(40);
                updateProgressbarAll();
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

    public void enableAllRoomButtons(){
        buttonKitchen.setEnabled(true);
        buttonBedroom.setEnabled(true);
        buttonBathroom.setEnabled(true);
        buttonPlayroom.setEnabled(true);
    }

    public void makeAllInteractionButtonsInvisible(){
        buttonFood.setVisibility(View.INVISIBLE);
        buttonSleep.setVisibility(View.INVISIBLE);
        buttonPlay.setVisibility(View.INVISIBLE);
        buttonPet.setVisibility(View.INVISIBLE);
        buttonWash.setVisibility(View.INVISIBLE);
    }

    /**
     * Update all Prgressbars
     */
    public void updateProgressbarAll(){
        progressBarManager.updateProgressbarHunger(myPet.getHunger());
        progressBarManager.updateProgressbarEnergy(myPet.getEnergy());
        progressBarManager.updateProgressbarCleanliness(myPet.getCleanliness());
        progressBarManager.updateProgressbarHappiness(myPet.getHappiness());
        progressBarManager.updateProgressbarHealth(myPet.getHealth());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Game.this,MainActivity.class);
        startActivity(intent);
    }
}
