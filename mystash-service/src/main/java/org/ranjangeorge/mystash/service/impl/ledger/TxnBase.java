package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.impl.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

abstract class TxnBase {

    void recordEntry(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO ledgerEntryDTO,
            @NotNull final CreditOrDebit creditOrDebit) {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);

            // Record Entry

            LedgerEntry ledgerEntry = toLedgerEntry(
                    stash,
                    creditOrDebit,
                    ledgerEntryDTO);

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
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

        return new LedgerEntry(
                stash,
                creditOrDebit,
                ledgerEntryDTO.getAmount(),
                ledgerEntryDTO.getDescription(),
                ledgerEntryDTO.getDate());
    }
}
