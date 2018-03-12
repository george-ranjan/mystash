package org.ranjangeorge.mystash.service.impl;

import org.ranjangeorge.mystash.service.api.Usecase;
import org.ranjangeorge.mystash.service.api.UsecaseName;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyStashService implements InvocationHandler {

    private MyStashServiceMapper myStashServiceMapper;

    MyStashService(MyStashServiceMapper myStashServiceMapper) {

        this.myStashServiceMapper = myStashServiceMapper;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Interpret UsecaseName Name from Annotation
        Usecase usecase = method.getAnnotationsByType(
                UsecaseName.class)[0].value();

        // Get Service implementor for UsecaseName
        Object service = myStashServiceMapper.getService(usecase);

        return service.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .invoke(service, args);
    }
}
