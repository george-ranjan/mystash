package org.ranjangeorge.mystash.service;

public interface IStashService {

    @UsecaseName(value = Usecase.NEW_STASH)
    void newStash(String stashId, String description);
}
