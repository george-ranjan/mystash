package org.ranjangeorge.mystash.service.impl.support;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyStashServiceHandler implements InvocationHandler {

    private final IMyStashServiceMapper myStashServiceMapper;

    private final SessionFactory sessionFactory;

    MyStashServiceHandler(
            @NotNull final IMyStashServiceMapper myStashServiceMapper,
            @NotNull final SessionFactory sessionFactory) {

        this.myStashServiceMapper = myStashServiceMapper;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        UsecaseName[] usecaseNameAnnotations = method.getAnnotationsByType(UsecaseName.class);
        if (usecaseNameAnnotations.length == 0) {
            throw new IllegalArgumentException("Please specify @UsecaseName annotation on the service interface("
                    + "Method:" + method.getName() + ")");
        }

        Usecase usecase = usecaseNameAnnotations[0].value();

        // Get Service implementor for UsecaseName
        Object service = myStashServiceMapper.getService(usecase);

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            Object result = service.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .invoke(service, args);

            // Commit
            transaction.commit();

            return result;

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }
}
