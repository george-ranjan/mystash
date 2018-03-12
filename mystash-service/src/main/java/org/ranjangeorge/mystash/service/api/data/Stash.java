package org.ranjangeorge.mystash.service.api.data;

public class Stash {

    private double balance = 0d;

    public double getBalance() {
        return balance;
    }

    public void credit(double theAmount) {

        balance = balance + theAmount;
    }

    public void debit(double theAmount) {

        balance = balance - theAmount;
    }
}
