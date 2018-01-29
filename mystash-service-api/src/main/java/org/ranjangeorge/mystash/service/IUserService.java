package org.ranjangeorge.mystash.service;

public interface IUserService {

    @UsecaseName(value = Usecase.CREATE_USER)
    void createUser(String emailId, String name);


}
