package org.ranjangeorge.mystash;

public class BalanceModifier {

    // Credit the balance
    public double credit(double balance, double theAmount) {

        // Increase the balance by the amount
        return balance + theAmount;
    }

    // Debit the balance
    public double debit(double balance, double theAmount) {

        // Reduce the balance by the amount
        return balance - theAmount;
    }
}
