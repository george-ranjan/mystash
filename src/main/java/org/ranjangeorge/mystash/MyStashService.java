package org.ranjangeorge.mystash;

import java.sql.SQLException;

public class MyStashService {

    private MyStashDB myStashDB;

    public MyStashService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
    }

    // Record an income
    public void credit(double theAmount) throws SQLException {

        // Get Balance
        double balance = myStashDB.fetchBalance();

        // Increase the balance by the amount
        balance = BalanceModifier.credit(balance, theAmount);

        // Update Balance
        myStashDB.saveBalance(balance);

    }

    // Record expense
    public void debit(double theAmount) throws SQLException {

        // Get Balance
        double balance = myStashDB.fetchBalance();

        // Reduce the balance by the amount
        balance = BalanceModifier.debit(balance, theAmount);

        // Update Balance
        myStashDB.saveBalance(balance);
    }

    // Track my balance
    public double getBalance() throws SQLException {

        return myStashDB.fetchBalance();
    }
}
