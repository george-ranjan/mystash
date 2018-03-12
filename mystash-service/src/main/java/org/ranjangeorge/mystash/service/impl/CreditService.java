package org.ranjangeorge.mystash.service.impl;

import com.sun.istack.internal.NotNull;
import org.hibernate.SessionFactory;
import org.ranjangeorge.mystash.service.api.ICreditService;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.Stash;

public class CreditService implements ICreditService {

    private SessionFactory sessionFactory;

    public CreditService(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Record an income
    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry credit) {

        // Get the stash
        Stash stash = sessionFactory.getCurrentSession()
                .load(Stash.class, stashId);

        // Increase the balance by the amount
        stash.credit(credit.getAmount());

        // Update Balance
        sessionFactory.getCurrentSession().save(stash);

        // Save Transaction
        sessionFactory.getCurrentSession().save(credit);
    }
}
