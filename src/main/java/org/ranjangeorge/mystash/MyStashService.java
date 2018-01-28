package org.ranjangeorge.mystash;

import java.sql.SQLException;

public class MyStashService {

    private MyStashDB myStashDB;

    public MyStashService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
    }

    // Record an income
    public void credit(CreditInfo theCredit) throws SQLException {

        // Get Balance
        double balance = myStashDB.fetchBalance();

        // Increase the balance by the amount
        balance = BalanceModifier.credit(
                balance, theCredit.getAmount());

        // Update Balance
        myStashDB.saveBalance(balance);

        // Save the Credit
        myStashDB.saveCredit(theCredit);
    }

    // Record expense
    public void debit(DebitInfo theDebit) throws SQLException {

        // Get Balance
        double balance = myStashDB.fetchBalance();

        // Reduce the balance by the amount
        balance = BalanceModifier.debit(balance, theDebit.getAmount());

        // Update Balance
        myStashDB.saveBalance(balance);

        // Save Transaction
        myStashDB.saveDebit(theDebit); // ADDED THIS LINE
    }

    // Track my balance
    public double getBalance() throws SQLException {

        return myStashDB.fetchBalance();
    }
}
