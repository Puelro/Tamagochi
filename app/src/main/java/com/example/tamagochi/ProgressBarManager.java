package com.example.tamagochi;

import android.widget.ProgressBar;
import android.app.Activity;

public class ProgressBarManager {

    ProgressBar progressBarHunger;
    ProgressBar progressBarEnergy;
    ProgressBar progressBarCleanliness;
    ProgressBar progressBarHappiness;
    ProgressBar progressBarHealth;

    public ProgressBarManager(Activity _activity ){
        progressBarHunger = _activity.findViewById(R.id.progressBarHunger);
        progressBarHunger.setMax(100);
        progressBarEnergy = _activity.findViewById(R.id.progressBarEnergy);
        progressBarEnergy.setMax(100);
        progressBarCleanliness = _activity.findViewById(R.id.progressBarCleanliness);
        progressBarCleanliness.setMax(100);
        progressBarHappiness = _activity.findViewById(R.id.progressBarHappiness);
        progressBarHealth = _activity.findViewById(R.id.progressBarHealth);
    }

    public void updateProgressbarHunger(int _hunger){
        progressBarHunger.setProgress(_hunger);
    }

}
