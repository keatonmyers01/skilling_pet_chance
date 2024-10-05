package com.skillingpetchance;

public class StaticAction {
    private String name;
    private double rate;
    private int quantity;

    public StaticAction(int baseRate, String name) {
        this.quantity = 0;
        this.rate = 1 / ((double)baseRate);
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
                ", quantity=" + quantity +
                '}';
    }
}
