package org.ranjangeorge.mystash.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.Stash;

public class LedgerService {

    private SessionFactory sessionFactory;

    LedgerService(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry credit) {

        recordEntry(stashId, credit);
    }

    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry debit) {

        recordEntry(stashId, debit);
    }

    private void recordEntry(
            @NotNull final String stashId,
            @NotNull final LedgerEntry ledgerEntry) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);

            // Record Entry
            stash.recordEntry(ledgerEntry);

            // Commit
            transaction.commit();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }
}
