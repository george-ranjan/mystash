package org.ranjangeorge.mystash;

public class MyStashServiceMapper {

    private MyStashDB myStashDB;

    public MyStashServiceMapper(MyStashDB myStashDB) {

        this.myStashDB = myStashDB;
    }

    public IService getService(String usecaseName) {
        return null;
    }
}
