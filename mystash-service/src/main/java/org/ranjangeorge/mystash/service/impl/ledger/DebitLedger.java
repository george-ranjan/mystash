package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.DEBIT)
public class DebitLedger extends LedgerTxn {

    private SessionFactory sessionFactory;

    public DebitLedger(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO debit) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);

            // Record Entry
            LedgerEntry ledgerEntry = toLedgerEntry(
                    stash,
                    CreditOrDebit.DEBIT,
                    debit);

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
