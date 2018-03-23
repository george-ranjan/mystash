package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.data.Stash;

import java.util.List;

public class DeleteAllStashes {

    private SessionFactory sessionFactory;

    public DeleteAllStashes(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public void deleteAllStashes() {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            //noinspection unchecked
            ((List<Stash>) session.createCriteria(Stash.class)
                    .list())
                    .forEach(session::delete);

            // Commit
            transaction.commit();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }

    }
}