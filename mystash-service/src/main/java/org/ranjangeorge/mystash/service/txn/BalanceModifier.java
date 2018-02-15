package org.ranjangeorge.mystash.service.txn;

public class BalanceModifier {

    // Credit the balance
    public static double credit(double balance, double theAmount) {

        return balance + theAmount;
    }

    // Debit the balance
    public static double debit(double balance, double theAmount) {

        return balance - theAmount;
    }
}
