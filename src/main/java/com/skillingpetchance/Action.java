package com.skillingpetchance;

public class Action {
    private transient double rate;
    private int quantity;

    //because of how actions are stored the name and level should be droppable because they can be gotten from the maps
    //that hold them, but I am leaving them in for now, in case they simplify a future process.
    public Action(int level, int baseRate) {
        this.quantity = 0;
        if(level == 200){
            this.rate = (1 / ((double) baseRate - ((double) 99 * 25))) * 15;
        }
        else {
            this.rate = 1 / ((double) baseRate - ((double) level * 25));
        }
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void calculateRate(double baseRate, int level ) {
        this.rate = 1 / ((double)baseRate - ((double) level * 25));
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return "Action{" +
                "rate=" + rate +
                ", quantity=" + quantity +
                '}';
    }
}
