package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.FETCH_BALANCE)
public class FetchBalance {

    private SessionFactory sessionFactory;

    public FetchBalance(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public double fetchBalance(String stashId) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            double balance = session.load(Stash.class, stashId).getBalance();

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
