package org.ranjangeorge.mystash.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.ranjangeorge.mystash.service.api.Usecase;

public class MyStashServiceMapper {

    // A SessionFactory is set up once for an application!
    private static final SessionFactory SESSION_FACTORY =
            new MetadataSources(new StandardServiceRegistryBuilder().configure().build())
                    .buildMetadata()
                    .buildSessionFactory();

    private LedgerService ledgerService = new LedgerService(SESSION_FACTORY);

    public Object getService(Usecase usecase) {

        if (usecase.equals(Usecase.CREDIT)) {
            return ledgerService;
        }

        throw new UnsupportedOperationException();
    }
}
