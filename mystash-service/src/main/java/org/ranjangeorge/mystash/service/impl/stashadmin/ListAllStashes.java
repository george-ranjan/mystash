package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.SessionFactory;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UsecaseNames(Usecase.LIST_ALL_STASHES)
public class ListAllStashes {

    private final SessionFactory sessionFactory;

    public ListAllStashes(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public Set<String> listAllStashes() {

        //noinspection unchecked
        return ((List<Stash>) sessionFactory.getCurrentSession()
                .createCriteria(Stash.class)
                .list())
                .stream()
                .map(Stash::getName)
                .collect(Collectors.toSet());
    }
}
