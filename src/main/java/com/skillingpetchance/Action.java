package com.skillingpetchance;

public class Action {
    private String name;
    private double rate;
    private int level;
    private int quantity;

    public Action(int level, int baseRate, String name) {
        this.quantity = 0;
        this.level = level;
        this.rate = 1 / ((double)baseRate - ((double)level * 25));
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", level=" + level +
                ", quantity=" + quantity +
                '}';
    }
}
