package org.ranjangeorge.mystash.service.txn;

import org.ranjangeorge.mystash.persistence.MyStashDB;
import org.ranjangeorge.mystash.service.data.TransactionInfo;

import java.util.List;

public class RecentTransactionService {

    private MyStashDB myStashDB;

    public RecentTransactionService(MyStashDB myStashDB) {
        this.myStashDB = myStashDB;
    }

    public List<TransactionInfo> getRecentTransactions(int count) {
        return null;
    }
}
