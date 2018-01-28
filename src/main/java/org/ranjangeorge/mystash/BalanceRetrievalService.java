package org.ranjangeorge.mystash;

import java.sql.SQLException;

public class BalanceRetrievalService {

    private MyStashDB myStashDB;

    public BalanceRetrievalService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
    }

    // Track my balance
    public double getBalance() throws SQLException {

        return myStashDB.fetchBalance();
    }
}
