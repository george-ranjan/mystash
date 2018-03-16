package org.ranjangeorge.mystash.service.impl;

import java.lang.reflect.Proxy;

public class ServiceLookup {

    public static <SVCINTF> SVCINTF getService(Class<SVCINTF> serviceInterface) {

        return (SVCINTF) Proxy.newProxyInstance(ServiceLookup.class.getClassLoader(),
                new Class[]{serviceInterface}, new MyStashService(new MyStashServiceMapper()));
    }
}
