package org.ranjangeorge.mystash;

import java.util.List;

public class RecentTransactionService {

    private MyStashDB myStashDB;

    public RecentTransactionService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
    }

    public List<Transaction> getRecentTransactions(int count) {
        return null;
    }
}
