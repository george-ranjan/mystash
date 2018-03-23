package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

import java.util.Set;

public interface IStashAdminService {

    @UsecaseName(Usecase.CREATE_NEW_STASH)
    String createNewStash(@NotNull String stashName);

    @UsecaseName(Usecase.LIST_STASHES)
    Set<String> list();

    @UsecaseName(Usecase.DELETE_ALL_STASHES)
    void deleteAllStashes();
}
