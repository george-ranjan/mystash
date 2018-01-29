package org.ranjangeorge.mystash.service.txn;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.IMyStashDAO;
import org.ranjangeorge.mystash.service.data.CreditInfo;

import java.sql.SQLException;

public class CreditService {

    private IMyStashDAO myStashDAO;

    public CreditService(
            @NotNull final IMyStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }

    // Record an income
    public void credit(
            @NotNull final String stashId,
            @NotNull final CreditInfo credit)
            throws SQLException {

        // Get Stash
        Stash stash = myStashDAO.fetch(
                Stash.class, stashId);

        // Increase the balance by the amount
        stash.credit(credit.getAmount());

        // Update Balance
        myStashDAO.save(Stash.class, stash);

        // Save Transaction
        myStashDAO.save(CreditInfo.class, credit);
    }
}
