package org.ranjangeorge.mystash.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.ranjangeorge.mystash.service.api.Usecase;

import java.util.HashMap;
import java.util.Map;

public enum MyStashServiceMapper {

    INSTANCE;

    // A SessionFactory is set up once for an application!
    private final SessionFactory sessionFactory =
            new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
                    .buildMetadata()
                    .buildSessionFactory();

    private Map<Usecase, Object> serviceMap = new HashMap<>();

    MyStashServiceMapper() {

        serviceMap.put(Usecase.CREATE_NEW_STASH, new CreateNewStash(sessionFactory));
        serviceMap.put(Usecase.LIST_STASHES, new ListAllStashes(sessionFactory));
        serviceMap.put(Usecase.DELETE_ALL_STASHES, new DeleteAllStashes(sessionFactory));
        //
        LedgerService ledgerService = new LedgerService(sessionFactory);
        serviceMap.put(Usecase.CREDIT, ledgerService);
        serviceMap.put(Usecase.DEBIT, ledgerService);
        serviceMap.put(Usecase.FETCH_BALANCE, new FetchBalance(sessionFactory));
    }

    public Object getService(Usecase usecase) {

        Object service = serviceMap.get(usecase);
        if (service == null) {
            throw new UnsupportedOperationException(usecase.name()
                    + ": implementation not configured or not yet implemented");
        }

        return service;
    }
}
