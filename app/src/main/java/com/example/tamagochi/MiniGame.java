package com.example.tamagochi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the (Cup-)Mini-Game
 * @author Robin
 */
public class MiniGame extends AppCompatActivity {

    /**values to raise Happiness and Money by winning the MiniGame*/
    int price;
    int fun;

    private TextView textViewPrice;

    private ImageView cup1, cup2, cup3;

    private Button buttonRetry;
    private Button buttonGame;

    /**list of cups used for MiniGame*/
    private List<Integer> cups;

    /**
     * initiate Cups and Buttons needed for MiniGame
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);

        price = 0;
        fun = 0;

        /**initiate and fill list with 3 cups*/
        cups = new ArrayList<>();
        cups.add(01);
        cups.add(02);
        cups.add(03);

        /**shuffle cups*/
        Collections.shuffle(cups);

        /**initiate TextViews*/
        textViewPrice = findViewById(R.id.tvPrice);
        textViewPrice.setVisibility(View.INVISIBLE);

        /**initiate the case that the first cup was clicked*/
        cup1 = findViewById(R.id.ivCup1);
        cup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if first cup was correct
                if(cups.get(0)==01) {
                    //lift first cup (with kiwi)
                    cup1.setImageResource(R.drawable.cupright);
                    //raise fun and money
                    fun+=50;
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                //if first cup was incorrect
                }else if(cups.get(0)==02||cups.get(0)==03){
                    //lift first cup (empty)
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                //if second cup was correct
                if(cups.get(1)==01) {
                    //lift second cup (with kiwi)
                    cup2.setImageResource(R.drawable.cupright);
                //if second cup was incorrect
                }else if(cups.get(1)==02||cups.get(1)==03){
                    //lift second cup (empty)
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                //if third cup was correct
                if (cups.get(2) == 01) {
                    //lift third cup (with kiwi)
                    cup3.setImageResource(R.drawable.cupright);
                //if third cup was incorrect
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    //lift third cup (empty)
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                //disable all cups to prevent cheating
                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        /**initiate the case that the second cup was clicked*/
        cup2 = findViewById(R.id.ivCup2);
        cup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if second cup was correct
                if(cups.get(1)==01) {
                    //lift second cup (with kiwi)
                    cup2.setImageResource(R.drawable.cupright);
                    //raise fun and money
                    fun+=50;
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                //if second cup was incorrect
                }else if(cups.get(1)==02||cups.get(1)==03){
                    //lift second cup (empty)
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                //if first cup was correct
                if(cups.get(0)==01) {
                    //lift first cup (with kiwi)
                    cup1.setImageResource(R.drawable.cupright);
                //if first cup was incorrect
                }else if(cups.get(0)==02||cups.get(0)==03){
                    //lift first cup (empty)
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                //if third cup was correct
                if (cups.get(2) == 01) {
                    //lift third cup (with kiwi)
                    cup3.setImageResource(R.drawable.cupright);
                //if third cup was incorrect
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    //lift third cup (empty)
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                //disable all cups to prevent cheating
                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        /**initiate the case that the third cup was clicked*/
        cup3 = findViewById(R.id.ivCup3);
        cup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if third cup was correct
                if (cups.get(2) == 01) {
                    //lift third cup (with kiwi)
                    cup3.setImageResource(R.drawable.cupright);
                    //raise fun and money
                    fun+=50;
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                //if third cup was incorrect
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    //lift third cup (empty)
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                //if first cup was correct
                if(cups.get(0)==01) {
                    //lift first cup (with kiwi)
                    cup1.setImageResource(R.drawable.cupright);
                //if first cup was incorrect
                }else if(cups.get(0)==02||cups.get(0)==03){
                    //lift first cup (empty)
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                //if second cup was correct
                if(cups.get(1)==01) {
                    //lift second cup (with kiwi)
                    cup2.setImageResource(R.drawable.cupright);
                //if second cup was incorrect
                }else if(cups.get(1)==02||cups.get(1)==03){
                    //lift second cup (empty)
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                //disable all cups to prevent cheating
                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        /**go back to main-Game*/
        buttonGame = findViewById(R.id.btnGame);
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { backToGame(); }});

        /**Button to retry the Mini-game*/
        buttonRetry = findViewById(R.id.btnRetry);
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(cups);

                cup1.setImageResource(R.drawable.cup);
                cup2.setImageResource(R.drawable.cup);
                cup3.setImageResource(R.drawable.cup);
                //re-enable cups
                cup1.setEnabled(true);
                cup2.setEnabled(true);
                cup3.setEnabled(true);
            }
        });
    }

    /**method to go back to the main Game*/
    public void backToGame(){
        //save values fun and price to add them to my Pets values
        SharedPreferences save = getSharedPreferences("save", 0);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("fun",fun);
        editor.putInt("price", price);
        editor.apply();
        //go back to Game-Activity
        Intent intent = new Intent(MiniGame.this, Game.class);
        startActivity(intent);
    }

    /**go back to main-Game*/
    public void onBackPressed(){
        backToGame();
    }
}
