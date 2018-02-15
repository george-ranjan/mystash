package org.ranjangeorge.mystash.service.txn;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.ILedgerDAO;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.sql.SQLException;

public class DebitService {

    private IStashDAO myStashDAO;

    private ILedgerDAO ledgerDAO;

    public DebitService(
            @NotNull final IStashDAO myStashDAO,
            @NotNull final ILedgerDAO ledgerDAO) {
        this.myStashDAO = myStashDAO;
        this.ledgerDAO = ledgerDAO;
    }

    // Record an income
    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry credit) throws SQLException {

        // Get current balance
        double balance = myStashDAO.fetchBalance(stashId);

        // Increase the balance by the amount
        balance = BalanceModifier.debit(balance, credit.getAmount());

        // Update Balance
        myStashDAO.saveBalance(stashId, balance);

        // Save Transaction
        ledgerDAO.record(credit);
    }
}
