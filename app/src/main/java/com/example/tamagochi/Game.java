package com.example.tamagochi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Manages the Main-Features of the App
 * @author Robin
 */
public class Game extends AppCompatActivity {

    /**Pet*/
    Pet myPet;

    /**ProgressbarManager for Pets values*/
    ProgressBarManager progressBarManager;

    /**checks if Pet is asleep*/
    private boolean isAsleep = false;

    /**counter for timer to decrease values*/
    int counter = 0;
    /**manages how fast values decrease over time*/
    int interval = 5;

    /**counter for sleep timer*/
    int counter2 = 0;
    /**manages how fast energy increases when sleeping*/
    int interval2 = 1;


    /**TextViews*/
    private TextView textViewRoom;
    private TextView textViewName;
    private TextView textViewMoney;
    private TextView textViewFood;
    private TextView textViewCoffee;
    private TextView textViewPotion;

    /**LinearLayout to change the Background*/
    LinearLayout root;

    /**Buttons to navigate through rooms*/
    private ImageButton buttonBedroomRight;
    private ImageButton buttonBathroomRight;
    private ImageButton buttonPlayroomRight;
    private ImageButton buttonKitchenRight;
    private ImageButton buttonBedroomLeft;
    private ImageButton buttonBathroomLeft;
    private ImageButton buttonPlayroomLeft;
    private ImageButton buttonKitchenLeft;

    /**Buttons to interact with Pet*/
    private ImageButton buttonMenu;
    private ImageButton buttonFood;
    private ImageButton buttonCoffee;
    private ImageButton buttonPotion;
    private ImageButton buttonShop;
    private ImageButton buttonSleep;
    private ImageButton buttonPlay;
    private ImageButton buttonPet;
    private ImageButton buttonWash;

    /**ImageViews to indicate Pets current state*/
    private ImageView tired;
    private ImageView dirty;
    private ImageView sleeping;
    private ImageView sad;

    /**
     * initiate values
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /**initiate Pet*/
        myPet = new Pet();

        /**initiate TextViews*/
        textViewRoom = findViewById(R.id.tvRoom);
        textViewName = findViewById(R.id.tvName);
        textViewMoney = findViewById(R.id.tvMoney);
        textViewFood = findViewById(R.id.tvfood);
        textViewCoffee = findViewById(R.id.tvCoffee);
        textViewPotion = findViewById(R.id.tvPotion);
        root=(LinearLayout)findViewById(R.id.root);

        //check if Pet is still Alive
        SharedPreferences save = getSharedPreferences("save", 0);
        boolean alive = save.getBoolean("isAlive",false);
        //if Pet is still Alive
        if(alive){
            //load values saved in SharedPreferences
            loadGame();
        }
        //if Pet is dead or was never created
        else{
            //start a new Game with default values
            newGame();
        }
        myPet.updateIsAlive();
        saveGame();

        /**set TextViews to Pets values*/
        textViewName.setText(myPet.getName());
        textViewMoney.setText(myPet.getMoney() + "€");
        textViewFood.setText(myPet.getFood()+"");
        textViewCoffee.setText(myPet.getCoffee()+"");
        textViewPotion.setText(myPet.getPotion()+"");

        /**initiate ProgressBarManager*/
        progressBarManager = new ProgressBarManager(this);

        /**initiate ImageViews for Pets state*/
        tired = findViewById(R.id.ivTired);
        dirty = findViewById(R.id.ivDirty);
        sad = findViewById(R.id.ivSad);
        sleeping = findViewById(R.id.ivSleeping);

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
        buttonBedroomRight = findViewById(R.id.btnBedroomRight);
        buttonBedroomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toBedroom(); }
        });

        /**Button to enter the Bathroom*/
        buttonBathroomRight = findViewById(R.id.btnBathroomRight);
        buttonBathroomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toBathroom(); }
        });

        /**Button to enter the Kitchen*/
        buttonKitchenRight = findViewById(R.id.btnKitchenRight);
        buttonKitchenRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { toKitchen(); }
        });

        /**Button to enter the Playroom*/
        buttonPlayroomRight = findViewById(R.id.btnPlayroomRight);
        buttonPlayroomRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { toPlayroom(); }
        });

        /**Button to enter the Bedroom*/
        buttonBedroomLeft = findViewById(R.id.btnBedroomLeft);
        buttonBedroomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toBedroom(); }
        });

        /**Button to enter the Bathroom*/
        buttonBathroomLeft = findViewById(R.id.btnBathroomLeft);
        buttonBathroomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { toBathroom(); }
        });

        /**Button to enter the Kitchen*/
        buttonKitchenLeft = findViewById(R.id.btnKitchenLeft);
        buttonKitchenLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { toKitchen(); }
        });

        /**Button to enter the Playroom*/
        buttonPlayroomLeft = findViewById(R.id.btnPlayroomLeft);
        buttonPlayroomLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { toPlayroom(); }
        });

        /**Button to feed the Pet*/
        buttonFood = findViewById(R.id.btnFood);
        buttonFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myPet.getHunger() < 100) {
                    myPet.updateHunger(20);
                    updateProgressbarAll();
                    //reduce food resources
                    myPet.updateFood(-1);
                    textViewFood.setText(myPet.getFood()+"");
                    manageResource();
                }
            }
        });

        /**Button to give the Pet coffee*/
        buttonCoffee = findViewById(R.id.btnCoffee);
        buttonCoffee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myPet.getEnergy() < 100) {
                    myPet.updateEnergy(100);
                    updateProgressbarAll();
                    //reduce coffee resources
                    myPet.updateCoffee(-1);
                    textViewCoffee.setText(myPet.getCoffee()+"");
                    manageResource();
                }
            }
        });

        /**Button to heal the Pet*/
        buttonPotion = findViewById(R.id.btnPotion);
        buttonPotion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(myPet.getHealth() < 100) {
                    myPet.updateHealth(40);
                    updateProgressbarAll();
                    //reduce potion resources
                    myPet.updatePotion(-1);
                    textViewPotion.setText(myPet.getPotion()+"");
                    manageResource();
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
                    //start timer to increase energy
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
                Intent intent = new Intent(Game.this, MiniGame.class);
                startActivity(intent);
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
        toKitchen();
        manageResource();
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
                //decrease health if three out of four values are at 0
                if(threeOutOfFourValuesDown()){
                    myPet.updateHealth(-1);
                }
                myPet.updateIsAlive();
            }
            managePetViews();
            handler.postDelayed(timer, 500);
            //if health reached 0 and Pet died
            if(!myPet.getIsAlive()){
                //stop timer
                handler.removeCallbacks(timer);
                handleDeath();
            }
            saveGame();
        }
    };

    /**indicate the pets current state via overlays*/
    public void managePetViews(){
        if(myPet.getEnergy() > 40){
            tired.setVisibility(View.INVISIBLE);
        }else{
            tired.setVisibility(View.VISIBLE);
        }

        if(myPet.getCleanliness() > 40){
            dirty.setVisibility(View.INVISIBLE);
        }else{
            dirty.setVisibility(View.VISIBLE);
        }

        if(myPet.getHappiness() > 40){
            sad.setVisibility(View.INVISIBLE);
        }else{
            sad.setVisibility(View.VISIBLE);
        }

        if(!isAsleep){
            sleeping.setVisibility(View.INVISIBLE);
        }else{
            sleeping.setVisibility(View.VISIBLE);
        }
    }

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
            if(counter2 < interval2){
                counter2++;
            }else if(myPet.getEnergy() < 100){
                myPet.updateEnergy(3);
                updateProgressbarAll();
                counter2 = 0;
            }
            handler2.postDelayed(timerSleep, 500);
            //if Energy is at 100 or Pet wakes up
            if(myPet.getEnergy()>=100||!isAsleep){
                //stop timer
                handler2.removeCallbacks(timerSleep);
                isAsleep = false;
            }
        }
    };

    /**Shop to buy food, coffee and potions*/
    public void shopMenu(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Shop");
        alertDialog.setCancelable(true);

        alertDialog.setNeutralButton("Futter 10€      ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //if money is sufficient
                if(myPet.getMoney() >= 10 ) {
                    //increase food
                    myPet.updateFood(1);
                    //decrease money
                    myPet.updateMoney(-10);
                    textViewFood.setText(myPet.getFood()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    manageResource();
                }else{
                    Toast.makeText(getApplicationContext(),"nicht genug Geld",Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setPositiveButton("Kaffee 50€   ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //if money is sufficient
                if(myPet.getMoney() >= 50 ) {
                    //increase coffee
                    myPet.updateCoffee(1);
                    //decrease money
                    myPet.updateMoney(-50);
                    textViewCoffee.setText(myPet.getCoffee()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    manageResource();
                }else{
                    Toast.makeText(getApplicationContext(),"nicht genug Geld",Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.setNegativeButton("Heiltrank 30€      ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //if money is sufficient
                if(myPet.getMoney() >= 30 ) {
                    //increase potion
                    myPet.updatePotion(1);
                    //decrease money
                    myPet.updateMoney(-30);
                    textViewPotion.setText(myPet.getPotion()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    manageResource();
                }else{
                    Toast.makeText(getApplicationContext(),"nicht genug Geld",Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.create().show();
    }

    /**handles the Pets death*/
    public void handleDeath(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(myPet.getName()+" ist gestorben");
        alertDialog.setCancelable(false);
        alertDialog.setNeutralButton("zum Hauptmenü", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Game.this, MainActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.create().show();
        saveGame();
    }

    /**save the Pets current values with Shared Preferences*/
    public void saveGame(){
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("hunger", myPet.getHunger());
        editor.putInt("cleanliness", myPet.getCleanliness());
        editor.putInt("energy", myPet.getEnergy());
        editor.putInt("happiness", myPet.getHappiness());
        editor.putInt("health", myPet.getHealth());
        editor.putBoolean("isAlive", myPet.getIsAlive());
        editor.putString("name",myPet.getName());
        editor.putInt("money",myPet.getMoney());
        editor.putInt("food",myPet.getFood());
        editor.putInt("potion",myPet.getPotion());
        editor.putInt("coffee",myPet.getCoffee());

        editor.putInt("fun",0);
        editor.putInt("price", 0);

        editor.apply();
    }

    /**load the Pets values from Shared Preferences*/
    public void loadGame(){
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        myPet.updateHunger(save.getInt("hunger",0));
        myPet.updateEnergy(save.getInt("energy",0));
        myPet.updateCleanliness(save.getInt("cleanliness",0));
        myPet.updateHappiness(save.getInt("happiness",0)+(save.getInt("fun",0)));
        myPet.updateHealth(save.getInt("health",0));
        myPet.setName(save.getString("name","unknown"));
        myPet.updateMoney(save.getInt("money",0)+(save.getInt("price",0)));
        myPet.updateFood(save.getInt("food",0));
        myPet.updatePotion(save.getInt("potion",0));
        myPet.updateCoffee(save.getInt("coffee",0));

        textViewMoney.setText(myPet.getMoney()+"€");

        editor.putInt("fun",0);
        editor.putInt("price", 0);
        editor.apply();
    }

    /**set values for new Pet*/
    public void newGame(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("geben deinem Kiwi einen Namen");
        //EditText for new Pets name
        EditText input = new EditText(this);
        alertDialog.setCancelable(false);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the new given name
                myPet.setName(input.getText().toString());
                //if no name was given
                if(myPet.getName().length() == 0)
                    //give default-name
                    myPet.setName("Kiwi");
                textViewName.setText(myPet.getName());
            }
        });
        alertDialog.show();

        //give the Pet default values*/
        myPet.updateHunger(100);
        myPet.updateEnergy(100);
        myPet.updateCleanliness(100);
        myPet.updateHappiness(100);
        myPet.updateHealth(100);
        myPet.updateMoney(100);
        myPet.updateFood(10);
        myPet.updatePotion(5);
        myPet.updateCoffee(0);
    }

    /**save current time when game is paused*/
    public void onPause() {
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        long startTime = System.currentTimeMillis();
        editor.putLong("startTime", startTime );
        editor.apply();
        super.onPause();
    }

    /**decrease Pets values according to passed time since the game was closed*/
    public void onResume() {
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        //do not execute content of OnResume when the App is startet for the first time
        boolean firstTime = save.getBoolean("firstTime",true);
        if(firstTime == true){
            editor.putBoolean("firstTime",false);
            editor.apply();
        }else {
            long startTime = save.getLong("startTime", 0);
            //calculate passed time since the game was last closed in milliseconds
            long elapsedTime = System.currentTimeMillis() - startTime;

            //calculate the exact value by which the Pets values have to be decreased
            int timeFormat = (int) (elapsedTime / 1000) / interval;

            //if three out of four values where at 0 before the app was closed
            if (threeOutOfFourValuesDown()) {
                //decrease health
                myPet.updateHealth(-timeFormat);
            }

            //decrease Pets values
            myPet.updateHunger(-timeFormat);
            myPet.updateEnergy(-timeFormat);
            myPet.updateCleanliness(-timeFormat);
            myPet.updateHappiness(-timeFormat);

            updateProgressbarAll();
        }
        super.onResume();
    }

    /**manages the Buttons for finite Resources*/
    public void manageResource(){
        if(myPet.getFood() > 0){
            buttonFood.setEnabled(true);
            buttonFood.setAlpha(1f);
        //if food is at 0
        }else{
            //disable button
            buttonFood.setEnabled(false);
            buttonFood.setAlpha(0.7f);
        }

        if(myPet.getCoffee() > 0){
            buttonCoffee.setEnabled(true);
            buttonCoffee.setAlpha(1f);
        //if coffee is at 0
        }else{
            //disable button
            buttonCoffee.setEnabled(false);
            buttonCoffee.setAlpha(0.7f);
        }

        if(myPet.getPotion() > 0){
            buttonPotion.setEnabled(true);
            buttonPotion.setAlpha(1f);
        //if potion is at 0
        }else{
            //disable button
            buttonPotion.setEnabled(false);
            buttonPotion.setAlpha(0.7f);
        }
    }

    /**makes all Room Buttons invisible*/
    public void makeAllRoomButtonsInvisible(){
        buttonKitchenRight.setVisibility(View.INVISIBLE);
        buttonBedroomRight.setVisibility(View.INVISIBLE);
        buttonBathroomRight.setVisibility(View.INVISIBLE);
        buttonPlayroomRight.setVisibility(View.INVISIBLE);
        buttonKitchenLeft.setVisibility(View.INVISIBLE);
        buttonBedroomLeft.setVisibility(View.INVISIBLE);
        buttonBathroomLeft.setVisibility(View.INVISIBLE);
        buttonPlayroomLeft.setVisibility(View.INVISIBLE);
    }

    /**makes all interaction Buttons invisible*/
    public void makeAllInteractionButtonsInvisible(){
        buttonFood.setVisibility(View.INVISIBLE);
        buttonSleep.setVisibility(View.INVISIBLE);
        buttonPlay.setVisibility(View.INVISIBLE);
        buttonPet.setVisibility(View.INVISIBLE);
        buttonWash.setVisibility(View.INVISIBLE);
        buttonPotion.setVisibility(View.INVISIBLE);
        buttonCoffee.setVisibility(View.INVISIBLE);
        buttonShop.setVisibility(View.INVISIBLE);

        textViewFood.setVisibility(View.INVISIBLE);
        textViewCoffee.setVisibility(View.INVISIBLE);
        textViewPotion.setVisibility(View.INVISIBLE);
    }

    /**update all Progressbars*/
    public void updateProgressbarAll(){
        progressBarManager.updateProgressbarHunger(myPet.getHunger());
        progressBarManager.updateProgressbarEnergy(myPet.getEnergy());
        progressBarManager.updateProgressbarCleanliness(myPet.getCleanliness());
        progressBarManager.updateProgressbarHappiness(myPet.getHappiness());
        progressBarManager.updateProgressbarHealth(myPet.getHealth());
    }

    /**manages Navigation to Kitchen*/
    public void toKitchen(){
        //only make Views and Buttons visible that are relevant to the room
        makeAllRoomButtonsInvisible();
        buttonBathroomRight.setVisibility(View.VISIBLE);
        buttonBedroomLeft.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonFood.setVisibility(View.VISIBLE);
        buttonCoffee.setVisibility(View.VISIBLE);
        buttonPotion.setVisibility(View.VISIBLE);
        buttonShop.setVisibility(View.VISIBLE);

        textViewFood.setVisibility(View.VISIBLE);
        textViewCoffee.setVisibility(View.VISIBLE);
        textViewPotion.setVisibility(View.VISIBLE);

        //set Background and textView to Kitchen
        textViewRoom.setText("Küche");
        root.setBackgroundResource(R.drawable.kitchen);
        //wake Pet up
        isAsleep = false;
    }

    /**manages Navigation to Bedroom*/
    public void toBedroom(){
        //only make Views and Buttons visible that are relevant to the room
        makeAllRoomButtonsInvisible();
        buttonKitchenRight.setVisibility(View.VISIBLE);
        buttonPlayroomLeft.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonSleep.setVisibility(View.VISIBLE);

        //set Background and textView to Bedroom
        textViewRoom.setText("Schlafzimmer");
        root.setBackgroundResource(R.drawable.bedroom);
    }

    /**manages Navigation to Bathroom*/
    public void toBathroom(){
        //only make Views and Buttons visible that are relevant to the room
        makeAllRoomButtonsInvisible();
        buttonKitchenLeft.setVisibility(View.VISIBLE);
        buttonPlayroomRight.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonWash.setVisibility(View.VISIBLE);

        //set Background and textView to Bathroom
        textViewRoom.setText("Badezimmer");
        root.setBackgroundResource(R.drawable.bathroom);
    }

    /**manages Navigation to Playroom*/
    public void toPlayroom(){
        //only make Views and Buttons visible that are relevant to the room
        makeAllRoomButtonsInvisible();
        buttonBathroomLeft.setVisibility(View.VISIBLE);
        buttonBedroomRight.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonPlay.setVisibility(View.VISIBLE);
        buttonPet.setVisibility(View.VISIBLE);

        //set Background and textView to Playroom
        textViewRoom.setText("Spielzimmer");
        root.setBackgroundResource(R.drawable.playroom);
        //wake Pet up
        isAsleep = false;
    }

    /**go back to Main Menu*/
    @Override
    public void onBackPressed() {
        saveGame();
        Intent intent = new Intent(Game.this,MainActivity.class);
        startActivity(intent);
    }
}
