package org.ranjangeorge.mystash.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.ranjangeorge.mystash.service.api.Usecase;
import org.ranjangeorge.mystash.service.impl.ledger.FetchBalance;
import org.ranjangeorge.mystash.service.impl.ledger.LedgerService;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.stashadmin.ListAllStashes;

import java.util.HashMap;
import java.util.Map;

public enum MyStashServiceMapper {

    INSTANCE;

    private Map<Usecase, Object> serviceMap = new HashMap<>();

    MyStashServiceMapper() {

        SessionFactory sessionFactory = buildSessionFactory();

        serviceMap.put(Usecase.CREATE_NEW_STASH, new CreateNewStash(sessionFactory));
        serviceMap.put(Usecase.LIST_STASHES, new ListAllStashes(sessionFactory));
        serviceMap.put(Usecase.DELETE_ALL_STASHES, new DeleteAllStashes(sessionFactory));
        //
        LedgerService ledgerService = new LedgerService(sessionFactory);
        serviceMap.put(Usecase.CREDIT, ledgerService);
        serviceMap.put(Usecase.DEBIT, ledgerService);
        serviceMap.put(Usecase.FETCH_BALANCE, new FetchBalance(sessionFactory));
    }

    private SessionFactory buildSessionFactory() {

        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        return new MetadataSources(build)
                .buildMetadata()
                .buildSessionFactory();
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
