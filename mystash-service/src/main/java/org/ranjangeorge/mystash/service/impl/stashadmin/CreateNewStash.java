package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import java.math.BigDecimal;
import java.util.Date;

@UsecaseNames(Usecase.CREATE_NEW_STASH)
public class CreateNewStash {

    public String createNewStash(
            @NotNull final String stashName,
            @NotNull final String ownerEmail,
            @NotNull final BigDecimal initialCredit) {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // New Stash
            Stash newStash = new Stash(stashName, ownerEmail);
            String stashId = (String) session.save(newStash);

            // Initial Credit
            LedgerEntry credit = new LedgerEntry(
                    newStash,
                    CreditOrDebit.CREDIT,
                    initialCredit,
                    "Starting Credit",
                    new Date());
            newStash.recordEntry(credit);

            // Commit
            transaction.commit();

            return stashId;

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }

    }
}
