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
    private boolean isAlive;

    /** values for finite Resources*/
    private int food;
    private int potion;
    private int coffee;
    private int money;

    /**Constructor*/
    public Pet(){
        hunger = 0;
        energy = 0;
        cleanliness = 0;
        happiness = 0;
        health = 0;
    }

    /**
     * Methods to update and set values
     * making sure the Pets values stay between 0 an 100
     */
    public void setName(String _name){
        name = _name;
    }

    public void updateHunger(int food){
        if(hunger + food > 100)
            hunger = 100;
        else if(hunger + food <0)
            hunger = 0;
        else
            hunger += food;
    }

    public void updateEnergy(int sleep){
        if(energy + sleep > 100)
            energy = 100;
        else if(energy + sleep <0)
            energy = 0;
        else
            energy += sleep;
    }

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

    public void updateFood(int _food){
        if(food + _food <0)
            food = 0;
        else
            food+=_food;
    }

    public void updatePotion(int _potion){
        if(potion + _potion <0)
            potion = 0;
        else
            potion += _potion;
    }

    public void updateCoffee(int _coffee){
        if(coffee + _coffee <0)
            coffee = 0;
        else
            coffee += _coffee;
    }

    public void updateMoney(int _money){
        if(money + _money <0)
            money = 0;
        else
            money += _money;
    }

    /**get-Methods*/
    public String getName() { return name; }
    public int getHunger() { return hunger; }
    public int getEnergy() { return energy; }
    public int getCleanliness() { return cleanliness; }
    public int getHappiness() { return happiness; }
    public int getHealth() { return health; }
    public boolean getIsAlive() { return isAlive; }
    public int getFood() { return food; }
    public int getPotion() { return potion; }
    public int getCoffee() { return coffee; }
    public int getMoney() { return money; }

}
