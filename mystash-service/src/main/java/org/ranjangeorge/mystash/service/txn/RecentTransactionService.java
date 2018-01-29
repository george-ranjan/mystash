package org.ranjangeorge.mystash.service.txn;

import org.ranjangeorge.mystash.persistence.IMyStashDAO;
import org.ranjangeorge.mystash.service.data.TransactionInfo;

import java.util.List;

public class RecentTransactionService {

    private IMyStashDAO myStashDB;

    public RecentTransactionService(IMyStashDAO myStashDB) {
        this.myStashDB = myStashDB;
    }

    public List<TransactionInfo> getRecentTransactions(int count) {
        return null;
    }
}
