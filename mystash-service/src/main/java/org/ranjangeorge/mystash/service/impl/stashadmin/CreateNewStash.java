package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.Usecase;
import org.ranjangeorge.mystash.service.api.UsecaseNames;
import org.ranjangeorge.mystash.service.api.data.Stash;

@UsecaseNames(Usecase.CREATE_NEW_STASH)
public class CreateNewStash {

    private SessionFactory sessionFactory;

    public CreateNewStash(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public String createNewStash(@NotNull final String stashName) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            String stashId = (String) session.save(new Stash(stashName));

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
