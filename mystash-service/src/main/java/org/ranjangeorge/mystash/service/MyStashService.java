package org.ranjangeorge.mystash.service;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyStashService implements InvocationHandler {

    private MyStashServiceMapper myStashServiceMapper;

    MyStashService(MyStashServiceMapper myStashServiceMapper) {

        this.myStashServiceMapper = this.myStashServiceMapper;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        // Interpret UsecaseName Name from Annotation
        Usecase usecase = method.getAnnotationsByType(
                UsecaseName.class)[0].value();

        // Get Service for UsecaseName
        IService service = myStashServiceMapper.getService(usecase);

        // Handle No Argument Scenario
        Object input = toInputFromArgs(args);

        // Execute UsecaseName
        return service.doUsecase(input);
    }

    @Nullable
    private Object toInputFromArgs(Object[] args) {

        return ((args != null && args.length > 0) ? args[0] : null);
    }
}
