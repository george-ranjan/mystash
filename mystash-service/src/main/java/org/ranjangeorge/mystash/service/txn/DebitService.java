package org.ranjangeorge.mystash.service.txn;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.IMyStashDAO;
import org.ranjangeorge.mystash.service.data.CreditInfo;
import org.ranjangeorge.mystash.service.data.DebitInfo;

public class DebitService {

    private IMyStashDAO myStashDAO;

    public DebitService(
            @NotNull final IMyStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }

    // Record an income
    public void debit(
            @NotNull final String stashId,
            @NotNull final DebitInfo debit) {

        // Get Stash
        Stash stash = myStashDAO.fetch(
                Stash.class, stashId);

        // Increase the balance by the amount
        stash.debit(debit.getAmount());

        // Update Balance
        myStashDAO.insert(Stash.class, stash);

        // Save Transaction
        myStashDAO.insert(CreditInfo.class, debit);
    }
}
