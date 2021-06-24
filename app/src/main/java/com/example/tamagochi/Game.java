package com.example.tamagochi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    Pet myPet;

    /**ProgressbarManager for Pets values*/
    ProgressBarManager progressBarManager;

    /**checks if Pet is asleep*/
    private boolean isAsleep = false;

    /**Counter and Interval to reduce Pet-values*/
    int counter = 0;
    int counter2 = 0;
    int interval = 1;

    /**TextViews*/
    private TextView textViewRoom;
    private TextView textViewName;
    private TextView textViewMoney;
    private TextView textViewFood;
    private TextView textViewCoffee;
    private TextView textViewPotion;

    /**LinearLayout to change the Background*/
    LinearLayout root;

    /**Buttons*/
    private ImageButton buttonBedroomRight;
    private ImageButton buttonBathroomRight;
    private ImageButton buttonPlayroomRight;
    private ImageButton buttonKitchenRight;

    private ImageButton buttonBedroomLeft;
    private ImageButton buttonBathroomLeft;
    private ImageButton buttonPlayroomLeft;
    private ImageButton buttonKitchenLeft;

    private ImageButton buttonMenu;
    private ImageButton buttonFood;
    private ImageButton buttonCoffee;
    private ImageButton buttonPotion;
    private ImageButton buttonShop;
    private ImageButton buttonSleep;
    private ImageButton buttonPlay;
    private ImageButton buttonPet;
    private ImageButton buttonWash;

    private ImageView tired;
    private ImageView dirty;
    private ImageView sleeping;
    private ImageView sad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myPet = new Pet();

        /**initialize TextViews*/
        textViewRoom = findViewById(R.id.tvRoom);
        textViewName = findViewById(R.id.tvName);
        textViewMoney = findViewById(R.id.tvMoney);
        textViewFood = findViewById(R.id.tvfood);
        textViewCoffee = findViewById(R.id.tvCoffee);
        textViewPotion = findViewById(R.id.tvPotion);
        root=(LinearLayout)findViewById(R.id.root);

        SharedPreferences save = getSharedPreferences("save", 0);
        boolean alive = save.getBoolean("isAlive",false);
        if(alive){
            loadGame();
        }
        else{
            newGame();
        }
        myPet.updateIsAlive();
        saveGame();

        textViewName.setText(myPet.getName());
        textViewMoney.setText(myPet.getMoney() + "€");
        textViewFood.setText(myPet.getFood()+"");
        textViewCoffee.setText(myPet.getCoffee()+"");
        textViewPotion.setText(myPet.getPotion()+"");

        /**initialize ProgressBarManager*/
        progressBarManager = new ProgressBarManager(this);
        progressBarManager.updateProgressbarHunger(30);

        /**initialise ImageViews for Pets state*/
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
                //finish();
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
                    myPet.updateFood(-1);
                    textViewFood.setText(myPet.getFood()+"");
                    checkResource();
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
                    myPet.updateCoffee(-1);
                    textViewCoffee.setText(myPet.getCoffee()+"");
                    checkResource();
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
                    myPet.updatePotion(-1);
                    textViewPotion.setText(myPet.getPotion()+"");
                    checkResource();
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
                finish();
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
        checkResource();
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
                myPet.updateIsAlive();
            }
            managePetViews();
            handler.postDelayed(timer, 500);
            if(!myPet.getIsAlive()){
                handler.removeCallbacks(timer);
                handleDeath();
            }
            saveGame();
        }
    };

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
            if(counter2 < interval){
                counter2++;
            }else if(myPet.getEnergy() < 100){
                myPet.updateEnergy(3);
                updateProgressbarAll();
                counter2 = 0;
            }
            handler2.postDelayed(timerSleep, 500);
            if(myPet.getEnergy()>=100||!isAsleep){
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
                if(myPet.getMoney() >= 10 ) {
                    myPet.updateFood(1);
                    myPet.updateMoney(-10);
                    textViewFood.setText(myPet.getFood()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    checkResource();
                }
            }
        });

        alertDialog.setPositiveButton("Kaffee 50€   ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(myPet.getMoney() >= 50 ) {
                    myPet.updateCoffee(1);
                    myPet.updateMoney(-50);
                    textViewCoffee.setText(myPet.getCoffee()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    checkResource();
                }
            }
        });

        alertDialog.setNegativeButton("Heiltrank 30€      ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(myPet.getMoney() >= 30 ) {
                    myPet.updatePotion(1);
                    myPet.updateMoney(-30);
                    textViewPotion.setText(myPet.getPotion()+"");
                    textViewMoney.setText(myPet.getMoney() + "€");
                    checkResource();
                }
            }
        });

        alertDialog.create().show();
    }

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
        //editor.commit();
    }

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

        System.out.println("price: "+save.getInt("price",0));
        System.out.println("money: "+myPet.getMoney());

        editor.putInt("fun",0);
        editor.putInt("price", 0);
        editor.apply();
        //editor.commit();
    }

    public void newGame(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("geben deinem Kiwi einen Namen");
        EditText input = new EditText(this);
        alertDialog.setCancelable(false);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myPet.setName(input.getText().toString());
                if(myPet.getName().length() == 0)
                    myPet.setName("Kiwi");
                textViewName.setText(myPet.getName());
            }
        });
        alertDialog.show();

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

    public void onPause() {
        //currentTime minis
        //double für Zeit
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        long startTime = System.currentTimeMillis();
        editor.putLong("startTime", startTime );
        editor.apply();
        //editor.commit();
        super.onPause();
    }

    public void onResume() {
        SharedPreferences save = getSharedPreferences("save", 0);
        long startTime = save.getLong("startTime", 0);
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println(elapsedTime);
        super.onResume();
    }

    public void checkResource(){
        if(myPet.getFood() > 0){
            buttonFood.setEnabled(true);
            buttonFood.setAlpha(1f);
        }else{
            buttonFood.setEnabled(false);
            buttonFood.setAlpha(0.7f);
        }

        if(myPet.getCoffee() > 0){
            buttonCoffee.setEnabled(true);
            buttonCoffee.setAlpha(1f);
        }else{
            buttonCoffee.setEnabled(false);
            buttonCoffee.setAlpha(0.7f);
        }

        if(myPet.getPotion() > 0){
            buttonPotion.setEnabled(true);
            buttonPotion.setAlpha(1f);
        }else{
            buttonPotion.setEnabled(false);
            buttonPotion.setAlpha(0.7f);
        }
    }

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

    public void updateProgressbarAll(){
        progressBarManager.updateProgressbarHunger(myPet.getHunger());
        progressBarManager.updateProgressbarEnergy(myPet.getEnergy());
        progressBarManager.updateProgressbarCleanliness(myPet.getCleanliness());
        progressBarManager.updateProgressbarHappiness(myPet.getHappiness());
        progressBarManager.updateProgressbarHealth(myPet.getHealth());
    }

    public void toKitchen(){
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

        textViewRoom.setText("Küche");
        root.setBackgroundResource(R.drawable.kitchen);
        isAsleep = false;
    }

    public void toBedroom(){
        makeAllRoomButtonsInvisible();
        buttonKitchenRight.setVisibility(View.VISIBLE);
        buttonPlayroomLeft.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonSleep.setVisibility(View.VISIBLE);
        textViewRoom.setText("Schlafzimmer");
        root.setBackgroundResource(R.drawable.bedroom);
    }

    public void toBathroom(){
        makeAllRoomButtonsInvisible();
        buttonKitchenLeft.setVisibility(View.VISIBLE);
        buttonPlayroomRight.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonWash.setVisibility(View.VISIBLE);
        textViewRoom.setText("Badezimmer");
        root.setBackgroundResource(R.drawable.bathroom);
        isAsleep = false;
    }

    public void toPlayroom(){
        makeAllRoomButtonsInvisible();
        buttonBathroomLeft.setVisibility(View.VISIBLE);
        buttonBedroomRight.setVisibility(View.VISIBLE);
        makeAllInteractionButtonsInvisible();
        buttonPlay.setVisibility(View.VISIBLE);
        buttonPet.setVisibility(View.VISIBLE);
        textViewRoom.setText("Spielzimmer");
        root.setBackgroundResource(R.drawable.playroom);
        isAsleep = false;
    }

    @Override
    public void onBackPressed() {
        saveGame();
        //finish();
        Intent intent = new Intent(Game.this,MainActivity.class);
        startActivity(intent);
    }
}
