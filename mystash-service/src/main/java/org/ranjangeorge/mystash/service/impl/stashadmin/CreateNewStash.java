package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.CREATE_NEW_STASH)
public class CreateNewStash {

    private final SessionFactory sessionFactory;

    public CreateNewStash(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public String createNewStash(@NotNull final String stashName) {

        return (String) sessionFactory.getCurrentSession()
                .save(new Stash(stashName));

    }
}
