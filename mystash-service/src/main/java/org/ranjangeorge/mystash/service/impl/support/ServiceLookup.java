package org.ranjangeorge.mystash.service.impl.support;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.reflections.Reflections;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public enum ServiceLookup implements IMyStashServiceMapper {

    INSTANCE;

    private final SessionFactory sessionFactory = buildSessionFactory();

    private final Map<Usecase, Object> serviceMap = new HashMap<>();

    ServiceLookup() {
        buildServiceMap();
    }

    private void buildServiceMap() {

        new Reflections("org.ranjangeorge.mystash.service.impl")
                .getTypesAnnotatedWith(UsecaseNames.class, true)
                .stream()
                .map(this::createServiceInstance)
                .forEach(svcInstance -> {

                    UsecaseNames usecaseNames = svcInstance.getClass().getAnnotationsByType(UsecaseNames.class)[0];
                    for (Usecase usecase : usecaseNames.value()) {
                        serviceMap.put(usecase, svcInstance);
                    }
                });
    }

    private Object createServiceInstance(Class<?> serviceImplementationClass) {

        try {

            return serviceImplementationClass.getConstructor(SessionFactory.class)
                    .newInstance(sessionFactory);

        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(
                    "Unable to instantiate the service implementation class(" + serviceImplementationClass + "). "
                            + "Please confirm it has public constructor with single org.hibernate.SessionFactory parameter",
                    e);
        }
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

    @NotNull
    private <SVCINTF> SVCINTF doGetService(Class<SVCINTF> serviceInterface) {
        MyStashServiceHandler serviceHandler = new MyStashServiceHandler(
                INSTANCE, sessionFactory);

        Object service = Proxy.newProxyInstance(
                ServiceLookup.class.getClassLoader(),
                new Class[]{serviceInterface},
                serviceHandler);

        //noinspection unchecked
        return (SVCINTF) service;
    }

    public static <SVCINTF> SVCINTF getService(Class<SVCINTF> serviceInterface) {

        return INSTANCE.doGetService(serviceInterface);
    }
}
