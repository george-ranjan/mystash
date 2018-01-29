package org.ranjangeorge.mystash.service;

import org.ranjangeorge.mystash.persistence.MyStashDAOOld;

public class MyStashServiceMapper {

    private MyStashDAOOld myStashDB;

    public MyStashServiceMapper(MyStashDAOOld myStashDB) {

        this.myStashDB = myStashDB;
    }

    public IService getService(Usecase usecase) {
        return null;
    }
}
