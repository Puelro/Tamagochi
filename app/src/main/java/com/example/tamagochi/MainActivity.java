package com.example.tamagochi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Manages Main-Menu
 * @author Robin
 */
public class MainActivity extends AppCompatActivity {

    /**Button to enter Game*/
    private ImageButton buttonContinue;

    /**
     * initiate Button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**initiate Button to enter game*/
        buttonContinue = findViewById(R.id.btnContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
            }
        });
    }

    /**disable back-Button*/
    @Override
    public void onBackPressed() {}

}