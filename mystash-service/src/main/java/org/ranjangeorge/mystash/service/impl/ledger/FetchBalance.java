package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import java.math.BigDecimal;

@UsecaseNames(Usecase.FETCH_BALANCE)
public class FetchBalance {

    public BigDecimal fetchBalance(String stashId) {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            BigDecimal balance = session.load(Stash.class, stashId).getBalance();

            // Commit
            transaction.commit();

            return balance;

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }
}
