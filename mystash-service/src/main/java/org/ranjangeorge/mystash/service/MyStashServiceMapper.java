package org.ranjangeorge.mystash.service;

public class MyStashServiceMapper {

    private StashDAOOld myStashDB;

    public MyStashServiceMapper(StashDAOOld myStashDB) {

        this.myStashDB = myStashDB;
    }

    public IService getService(Usecase usecase) {
        return null;
    }
}
