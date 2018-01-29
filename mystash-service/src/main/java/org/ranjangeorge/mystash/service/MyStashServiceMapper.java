package org.ranjangeorge.mystash.service;

import org.ranjangeorge.mystash.persistence.MyStashDAO;

public class MyStashServiceMapper {

    private MyStashDAO myStashDB;

    public MyStashServiceMapper(MyStashDAO myStashDB) {

        this.myStashDB = myStashDB;
    }

    public IService getService(Usecase usecase) {
        return null;
    }
}
