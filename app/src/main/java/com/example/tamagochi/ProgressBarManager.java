package com.example.tamagochi;

import android.widget.ProgressBar;
import android.app.Activity;

public class ProgressBarManager {

    private ProgressBar progressBarHunger;
    private ProgressBar progressBarEnergy;
    private ProgressBar progressBarCleanliness;
    private ProgressBar progressBarHappiness;
    private ProgressBar progressBarHealth;

    public ProgressBarManager(Activity _activity ){
        progressBarHunger = _activity.findViewById(R.id.progressBarHunger);
        progressBarHunger.setMax(100);
        progressBarEnergy = _activity.findViewById(R.id.progressBarEnergy);
        progressBarEnergy.setMax(100);
        progressBarCleanliness = _activity.findViewById(R.id.progressBarCleanliness);
        progressBarCleanliness.setMax(100);
        progressBarHappiness = _activity.findViewById(R.id.progressBarHappiness);
        progressBarHappiness.setMax(100);
        progressBarHealth = _activity.findViewById(R.id.progressBarHealth);
        progressBarHealth.setMax(100);
    }

    public void updateProgressbarHunger(int _hunger){ progressBarHunger.setProgress(_hunger); }

    public void updateProgressbarEnergy(int _energy){ progressBarEnergy.setProgress(_energy); }

    public void updateProgressbarCleanliness(int _cleanliness){ progressBarCleanliness.setProgress(_cleanliness); }

    public void updateProgressbarHappiness(int _happiness){ progressBarHappiness.setProgress(_happiness); }

    public void updateProgressbarHealth(int _health){ progressBarHealth.setProgress(_health); }

}
