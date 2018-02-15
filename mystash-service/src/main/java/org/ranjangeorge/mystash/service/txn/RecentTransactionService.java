package org.ranjangeorge.mystash.service.txn;

import org.ranjangeorge.mystash.persistence.dao.IStashDAO;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.util.List;

public class RecentTransactionService {

    private IStashDAO myStashDB;

    public RecentTransactionService(IStashDAO myStashDB) {
        this.myStashDB = myStashDB;
    }

    public List<LedgerEntry> getRecentTransactions(int count) {
        return null;
    }
}
