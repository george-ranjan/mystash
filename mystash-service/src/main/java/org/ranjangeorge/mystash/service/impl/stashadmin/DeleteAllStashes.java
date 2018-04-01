package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import java.util.List;

@UsecaseNames(Usecase.DELETE_ALL_STASHES)
public class DeleteAllStashes {

    public void deleteAllStashes() {

        Session session = SessionFactoryHolder.getCurrentSession();
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
