package org.ranjangeorge.mystash.service;

import org.ranjangeorge.mystash.persistence.MyStashDB;

public class MyStashServiceMapper {

    private MyStashDB myStashDB;

    public MyStashServiceMapper(MyStashDB myStashDB) {

        this.myStashDB = myStashDB;
    }

    public IService getService(Usecase usecase) {
        return null;
    }
}
