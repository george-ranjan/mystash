package org.ranjangeorge.mystash.service.txn;

import org.ranjangeorge.mystash.persistence.IMyStashDAO;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.util.List;

public class RecentTransactionService {

    private IMyStashDAO myStashDB;

    public RecentTransactionService(IMyStashDAO myStashDB) {
        this.myStashDB = myStashDB;
    }

    public List<LedgerEntry> getRecentTransactions(int count) {
        return null;
    }
}
