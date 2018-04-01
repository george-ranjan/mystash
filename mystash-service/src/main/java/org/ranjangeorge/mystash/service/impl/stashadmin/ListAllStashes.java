package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UsecaseNames(Usecase.LIST_ALL_STASHES)
public class ListAllStashes {

    public Set<String> listAllStashes() {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            @SuppressWarnings("unchecked")
            List<Stash> stashList = (List<Stash>) session.createCriteria(Stash.class).list();

            Set<String> stashNames = stashList.stream()
                    .map(Stash::getName)
                    .collect(Collectors.toSet());

            // Commit
            transaction.commit();

            return stashNames;

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }
}
