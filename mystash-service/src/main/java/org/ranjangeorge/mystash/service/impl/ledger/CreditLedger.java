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

@UsecaseNames(Usecase.CREDIT)
public class CreditLedger extends LedgerTxn {

    private SessionFactory sessionFactory;

    public CreditLedger(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO credit) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);

            // Record Entry
            LedgerEntry ledgerEntry = toLedgerEntry(
                    stash,
                    CreditOrDebit.CREDIT,
                    credit);

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
