package org.ranjangeorge.mystash.service.impl.support.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public enum SessionFactoryHolder {

    INSTANCE;

    private SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory() {

        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        return new MetadataSources(build)
                .buildMetadata()
                .buildSessionFactory();
    }

    public static Session getCurrentSession() {
        return INSTANCE.sessionFactory.getCurrentSession();
    }
}
