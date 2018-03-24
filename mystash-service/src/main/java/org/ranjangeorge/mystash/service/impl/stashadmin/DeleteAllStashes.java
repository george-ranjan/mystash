package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import java.util.List;

@UsecaseNames(Usecase.DELETE_ALL_STASHES)
public class DeleteAllStashes {

    private final SessionFactory sessionFactory;

    public DeleteAllStashes(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public void deleteAllStashes() {

        Session session = sessionFactory.getCurrentSession();

        //noinspection unchecked
        ((List<Stash>) session.createCriteria(Stash.class)
                .list())
                .forEach(session::delete);
    }
}
