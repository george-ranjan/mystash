package org.ranjangeorge.mystash.service.txn;

import org.ranjangeorge.mystash.persistence.MyStashDB;
import org.ranjangeorge.mystash.service.data.DebitInfo;

import java.sql.SQLException;

public class DebitService {

    private MyStashDB myStashDB;

    public DebitService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
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
}
