package com.example.tamagochi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Manages the Main-Features of the App
 * @author Robin
 */
public class Game extends AppCompatActivity {

    /**Pet*/
    private Pet myPet;

    /**ProgressbarManager for Pets values*/
    ProgressBarManager progressBarManager;

    /**Number of food-supplies*/
    private int food;

    /**Number of potion-supplies*/
    private int potion;

    /**checks if Pet is asleep*/
    private boolean isAsleep = false;

    /**Counter and Interval to reduce Pet-values*/
    int counter = 0;
    int counter2 = 0;
    int interval = 1;

    /**TextViews*/
    private TextView textViewRoom;
    private TextView textViewName;

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
    private ImageButton buttonShop;
    private ImageButton buttonSleep;
    private ImageButton buttonPlay;
    private ImageButton buttonPet;
    private ImageButton buttonWash;

    /*@Override
    protected void onStart() {
        super.onStart();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myPet = new Pet("Kiwi", 50,50,50,50,70,true,0);

        /**initialize TextViews*/
        textViewRoom = findViewById(R.id.tvRoom);
        textViewName = findViewById(R.id.tvName);
        textViewName.setText(myPet.getName());
        root=(LinearLayout)findViewById(R.id.root);

        /**initialize ProgressBarManager*/
        progressBarManager = new ProgressBarManager(this);
        progressBarManager.updateProgressbarHunger(30);

        updateProgressbarAll();

        /**start Timer to reduce values*/
        timer.run();

        /**Button to go back to MainMenu*/
        buttonMenu = findViewById(R.id.btnMenu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**Button to enter the Bedroom*/
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

        /**Button to enter the Bathroom*/
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
                isAsleep = false;
            }
        });

        /**Button to enter the Kitchen*/
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
                buttonShop.setVisibility(View.VISIBLE);
                textViewRoom.setText("Küche");
                root.setBackgroundResource(R.drawable.kitchen);
                isAsleep = false;
            }
        });

        /**Button to enter the Playroom*/
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
                isAsleep = false;
            }
        });

        /**Button to feed the Pet*/
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

        /**Button to give the Pet coffee*/
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

        /**Button to heal the Pet*/
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

        /**Button to open the shop*/
        buttonShop = findViewById(R.id.btnShop);
        buttonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopMenu();
            }
        });

        /**Button to let the Pet Sleep*/
        buttonSleep = findViewById(R.id.btnSleep);
        buttonSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAsleep == false){
                    isAsleep = true;
                    timerSleep.run();
                }
                else {
                    isAsleep = false;
                }
            }
        });

        /**Button to play the minniGame*/
        buttonPlay = findViewById(R.id.btnPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO MiniGame
            }
        });

        /**Button to pet the Pet*/
        buttonPet = findViewById(R.id.btnPet);
        buttonPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPet.updateHappiness(5);
                updateProgressbarAll();
            }
        });

        /**Button to wash the Pet*/
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
        buttonShop.setVisibility(View.VISIBLE);
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
                //health decreases if three out of four values are at 0
                if(threeOutOfFourValuesDown()){
                    myPet.updateHealth(-1);
                }
            }
            handler.postDelayed(timer, 500);
        }
    };

    /**checks if at least three out of four values reached 0*/
    public boolean threeOutOfFourValuesDown(){
        if(myPet.getEnergy()<=0 && myPet.getHunger()<=0 && myPet.getHappiness()<=0 || myPet.getEnergy()<=0 && myPet.getHunger()<=0 && myPet.getCleanliness()<=0 || myPet.getEnergy()<=0 && myPet.getHappiness()<=0 && myPet.getCleanliness()<=0 || myPet.getHunger()<=0 && myPet.getHappiness()<=0 && myPet.getCleanliness()<=0){
           return true;
        }else{
            return false;
        }
    }

    /**Timer to increase Energy while sleeping*/
    Handler handler2 = new Handler();
    private Runnable timerSleep = new Runnable() {
        @Override
        public void run() {
            if(counter2 < interval){
                counter2++;
            }else if(myPet.getEnergy() < 100){
                myPet.updateEnergy(3);
                updateProgressbarAll();
                counter2 = 0;
            }
            handler2.postDelayed(timerSleep, 500);
            if(myPet.getEnergy()>=100||isAsleep==false){
                handler2.removeCallbacks(timerSleep);
                isAsleep = false;
            }
        }
    };

    //TODO Bilder für die Kaufoptionen einfügen
    /**Shop to buy food, coffee and potions*/
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
        buttonShop.setVisibility(View.INVISIBLE);
    }

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
