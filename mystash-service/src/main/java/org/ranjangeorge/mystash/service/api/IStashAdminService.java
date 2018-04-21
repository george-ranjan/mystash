package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

import javax.json.JsonArray;

public interface IStashAdminService {

    @UsecaseName(Usecase.CREATE_NEW_STASH)
    String createNewStash(
            @NotNull String stashName,
            @NotNull String ownerEmail,
            long initialCredit);

    @UsecaseName(Usecase.LIST_ALL_STASHES)
    JsonArray listAllStashes();

    @UsecaseName(Usecase.DELETE_ALL_STASHES)
    void deleteAllStashes();
}
