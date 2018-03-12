package org.ranjangeorge.mystash.service.impl;

import com.sun.istack.internal.NotNull;
import org.hibernate.SessionFactory;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.Stash;

public class DebitService {

    private SessionFactory sessionFactory;

    public DebitService(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Record an income
    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry debit) {

        // Get the stash
        Stash stash = sessionFactory.getCurrentSession()
                .load(Stash.class, stashId);

        // Increase the balance by the amount
        stash.debit(debit.getAmount());

        // Update Balance
        sessionFactory.getCurrentSession().save(stash);

        // Save Transaction
        sessionFactory.getCurrentSession().save(debit);
    }
}
