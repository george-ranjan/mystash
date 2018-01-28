package org.ranjangeorge.mystash;

import java.sql.SQLException;

public class CreditService {

    private MyStashDB myStashDB;

    public CreditService(MyStashDB myStashDB) {
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
}
