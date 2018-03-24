package org.ranjangeorge.mystash.service.impl.support;

import java.lang.reflect.Proxy;

public class ServiceLookup {

    public static <SVCINTF> SVCINTF getService(Class<SVCINTF> serviceInterface) {

        MyStashServiceHandler serviceHandler = new MyStashServiceHandler(MyStashServiceMapper.INSTANCE);

        Object service = Proxy.newProxyInstance(
                ServiceLookup.class.getClassLoader(),
                new Class[]{serviceInterface},
                serviceHandler);

        //noinspection unchecked
        return (SVCINTF) service;
    }
}
