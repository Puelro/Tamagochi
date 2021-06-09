package com.example.tamagochi;

/**
 * Class Pet manages the Pet with all it's values
 * @author Robin
 */
public class Pet {

    /** Pets values */
    private String name;
    private int hunger;
    private int energy;
    private int cleanliness;
    private int happiness;
    private int health;
    private int awayTime;
    private boolean isAlive;

    /**empty Constructor*/
    public Pet(){

    }

    /**
     * Constructor sets values
     * @param _name the Pets given Name
     * @param _hunger
     * @param _energy
     * @param _cleanliness
     * @param _happiness
     * @param _health
     * @param _isAlive
     * @param _awayTime
     */
    public Pet(String _name, int _hunger, int _energy, int _cleanliness, int _happiness, int _health, boolean _isAlive, int _awayTime){
        name = _name;
        hunger = _hunger;
        energy = _energy;
        cleanliness = _cleanliness;
        happiness = _happiness;
        health = _health;
        isAlive = _isAlive;
        awayTime = _awayTime;
    }

    /**Methods to update values*/
    public void updateHunger(int food){
        if(hunger + food > 100)
            hunger = 100;
        else if(hunger + food <0)
            hunger = 0;
        else
            hunger += food;
    }

    //TODO sleep-Wert umrechnen
    public void updateEnergy(int sleep){
        if(energy + sleep > 100)
            energy = 100;
        else if(energy + sleep <0)
            energy = 0;
        else
            energy += sleep;
    }

    //TODO waschen konzept
    public void updateCleanliness(int soap){
        if(cleanliness + soap > 100)
            cleanliness = 100;
        else if(cleanliness + soap <0)
            cleanliness = 0;
        else
            cleanliness += soap;
    }

    public void updateHappiness(int play){
        if(happiness + play > 100)
            happiness = 100;
        else if(happiness + play < 0)
            happiness = 0;
        else
            happiness += play;
    }

    public void updateHealth(int potion){
        if(health + potion > 100)
            health = 100;
        else if(health + potion <0)
            health = 0;
        else
            health += potion;
    }

    public boolean updateIsAlive(){
        if(health > 0){
            isAlive = true;
        }else
            isAlive = false;
        return isAlive;
    }

    //TODO AwayTime-Faktor ausrechenen
    public void updateAwayTime(int time){
        updateCleanliness(-time);
        updateEnergy(-time);
        updateHappiness(-time);
        updateHunger(-time);
    }

    //TODO save() - Methode

    /**get-Methods*/
    public String getName() { return name; }
    public int getHunger() { return hunger; }
    public int getEnergy() { return energy; }
    public int getCleanliness() { return cleanliness; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public int getAwayTime() { return awayTime; }
    public boolean getIsAlive() { return isAlive; }

}
