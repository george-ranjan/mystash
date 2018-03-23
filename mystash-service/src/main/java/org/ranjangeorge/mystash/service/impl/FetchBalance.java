package org.ranjangeorge.mystash.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.data.Stash;

public class FetchBalance {

    private SessionFactory sessionFactory;

    FetchBalance(SessionFactory sessionFactory) {

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
