package com.example.tamagochi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniGame extends AppCompatActivity {

    int price = 0;
    private TextView textViewPrice;

    private ImageView cup1, cup2, cup3;

    private List<Integer> cups;

    private Button buttonRetry;
    private Button buttonGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame);

        cups = new ArrayList<>();
        cups.add(01);
        cups.add(02);
        cups.add(03);

        Collections.shuffle(cups);

        textViewPrice = findViewById(R.id.tvPrice);
        textViewPrice.setVisibility(View.INVISIBLE);

        cup1 = findViewById(R.id.ivCup1);
        cup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cups.get(0)==01) {
                    cup1.setImageResource(R.drawable.cupright);
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                }else if(cups.get(0)==02||cups.get(0)==03){
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                if(cups.get(1)==01) {
                    cup2.setImageResource(R.drawable.cupright);
                }else if(cups.get(1)==02||cups.get(1)==03){
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                if (cups.get(2) == 01) {
                    cup3.setImageResource(R.drawable.cupright);
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        cup2 = findViewById(R.id.ivCup2);
        cup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cups.get(1)==01) {
                    cup2.setImageResource(R.drawable.cupright);
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                }else if(cups.get(1)==02||cups.get(1)==03){
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                if(cups.get(0)==01) {
                    cup1.setImageResource(R.drawable.cupright);
                }else if(cups.get(0)==02||cups.get(0)==03){
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                if (cups.get(2) == 01) {
                    cup3.setImageResource(R.drawable.cupright);
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        cup3 = findViewById(R.id.ivCup3);
        cup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cups.get(2) == 01) {
                    cup3.setImageResource(R.drawable.cupright);
                    price+=50;
                    textViewPrice.setText("+"+price+"€");
                    textViewPrice.setVisibility(View.VISIBLE);
                } else if (cups.get(2) == 02 || cups.get(2) == 03) {
                    cup3.setImageResource(R.drawable.cupwrong);
                }

                if(cups.get(0)==01) {
                    cup1.setImageResource(R.drawable.cupright);
                }else if(cups.get(0)==02||cups.get(0)==03){
                    cup1.setImageResource(R.drawable.cupwrong);
                }

                if(cups.get(1)==01) {
                    cup2.setImageResource(R.drawable.cupright);
                }else if(cups.get(1)==02||cups.get(1)==03){
                    cup2.setImageResource(R.drawable.cupwrong);
                }

                cup1.setEnabled(false);
                cup2.setEnabled(false);
                cup3.setEnabled(false);
            }
        });

        buttonGame = findViewById(R.id.btnGame);
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences save = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = save.edit();
                editor.putInt("price", price);

                //finish();
                Intent intent = new Intent(MiniGame.this, Game.class);
                startActivity(intent);
            }
        });

        buttonRetry = findViewById(R.id.btnRetry);
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(cups);

                cup1.setImageResource(R.drawable.cup);
                cup2.setImageResource(R.drawable.cup);
                cup3.setImageResource(R.drawable.cup);
                cup1.setEnabled(true);
                cup2.setEnabled(true);
                cup3.setEnabled(true);
            }
        });
    }

    public void onBackPressed(){
        //finish();
        Intent intent = new Intent(MiniGame.this, Game.class);
        startActivity(intent);
    }
}
