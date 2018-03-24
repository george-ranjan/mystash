package org.ranjangeorge.mystash.service.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Function;

public enum DBTransactionFactory {

    INSTANCE;

    private final SessionFactory sessionFactory = buildSessionFactory();

    public DBTransaction newDBTransaction() {
        return new DBTransaction(sessionFactory);
    }

    public static <R> R execute(Function<Session, R> dbTransactionFunction) {

        return DBTransactionFactory.INSTANCE
                .newDBTransaction()
                .execute(dbTransactionFunction);
    }

    private SessionFactory buildSessionFactory() {

        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        return new MetadataSources(build)
                .buildMetadata()
                .buildSessionFactory();
    }
}
