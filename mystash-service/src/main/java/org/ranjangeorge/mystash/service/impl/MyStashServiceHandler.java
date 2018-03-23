package org.ranjangeorge.mystash.service.impl;

import org.ranjangeorge.mystash.service.api.Usecase;
import org.ranjangeorge.mystash.service.api.UsecaseName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyStashServiceHandler implements InvocationHandler {

    private MyStashServiceMapper myStashServiceMapper;

    MyStashServiceHandler(MyStashServiceMapper myStashServiceMapper) {

        this.myStashServiceMapper = myStashServiceMapper;
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

        return service.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .invoke(service, args);
    }
}
