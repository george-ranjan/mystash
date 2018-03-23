package org.ranjangeorge.mystash.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.data.Stash;

public class LedgerService {

    private SessionFactory sessionFactory;

    LedgerService(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO credit) {

        recordEntry(stashId, credit);
    }

    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO debit) {

        recordEntry(stashId, debit);
    }

    private void recordEntry(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);

            // Record Entry
            LedgerEntry ledgerEntry = toLedgerEntry(stash, ledgerEntryDTO);

            stash.recordEntry(ledgerEntry);

            // Commit
            transaction.commit();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }

    @NotNull
    private LedgerEntry toLedgerEntry(
            @NotNull final Stash stash,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

        return new LedgerEntry(
                stash,
                ledgerEntryDTO.getCreditOrDebit(),
                ledgerEntryDTO.getAmount(),
                ledgerEntryDTO.getDescription(),
                ledgerEntryDTO.getDate());
    }
}
