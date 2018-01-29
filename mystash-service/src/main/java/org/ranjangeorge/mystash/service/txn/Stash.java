package org.ranjangeorge.mystash.service.txn;

public class Stash {

    private double balance;

    // Credit the balance
    public void credit(double theAmount) {

        balance = balance + theAmount;
    }

    // Debit the balance
    public void debit(double theAmount) {

        balance = balance - theAmount;
    }
}
