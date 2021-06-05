package com.example.tamagochi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

    /**Number of potion-supplies*/
    private int potion;

    /**Counter and Interval to reduce Pet-values*/
    int counter = 0;
    int interval = 1;

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
    private ImageButton buttonCoffee;
    private ImageButton buttonPotion;
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

        timer.run();

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
                buttonCoffee.setVisibility(View.VISIBLE);
                buttonPotion.setVisibility(View.VISIBLE);
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

        buttonCoffee = findViewById(R.id.btnCoffee);
        /*buttonCoffee.setEnabled(false);
        if(food > 0){
            buttonCoffee.setEnabled(true);
        }*/
        buttonCoffee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myPet.getEnergy() < 100) {
                    myPet.updateEnergy(100);
                    updateProgressbarAll();
                }
            }
        });

       buttonPotion = findViewById(R.id.btnPotion);
        /* buttonPotion.setEnabled(false);
        if(potion > 0){
            buttonPotion.setEnabled(true);
        }*/
        buttonPotion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myPet.getHealth() < 100) {
                    myPet.updateHealth(40);
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

        /**The Game starts in the Kitchen*/
        buttonKitchen.setEnabled(false);
        makeAllInteractionButtonsInvisible();
        buttonPotion.setVisibility(View.VISIBLE);
        buttonFood.setVisibility(View.VISIBLE);
        buttonCoffee.setVisibility(View.VISIBLE);
        textViewRoom.setText("Küche");
    }

    /**Timer-Handler to reduce Pet-Values*/
    private Handler handler = new Handler();
    private Runnable timer = new Runnable() {
        @Override
        public void run() {
            if(counter < interval){
                counter++;
            }else{
                myPet.updateHunger(-1);
                myPet.updateEnergy(-1);
                myPet.updateCleanliness(-1);
                myPet.updateHappiness(-1);
                updateProgressbarAll();
                counter = 0;
                //TODO Health senken wenn 3 von 4 Werten 0 erreichen
            }
            handler.postDelayed(timer, 500);
        }
    };


    //TODO Bilder für die Kaufoptionen einfügen
    public void shopMenu(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Shop");
        alertDialog.setCancelable(true);

        //alertDialog.setNeutralButtonIcon(getDrawable(R.drawable.food));
        //alertDialog.setNeutralButtonIcon(getDrawable(R.drawable.potion));

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
        buttonPotion.setVisibility(View.INVISIBLE);
        buttonCoffee.setVisibility(View.INVISIBLE);
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
