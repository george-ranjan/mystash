package org.ranjangeorge.mystash.service.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

public class DBTransaction {

    private final SessionFactory sessionFactory;

    DBTransaction(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public <R> R execute(Function<Session, R> dbTransactionFunction) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            R result = dbTransactionFunction.apply(session);

            // Commit
            transaction.commit();

            return result;

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }

    }
}
