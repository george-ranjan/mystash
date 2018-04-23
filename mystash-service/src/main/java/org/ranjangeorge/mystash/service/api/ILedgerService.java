package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

import javax.json.JsonObject;

public interface ILedgerService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(
            @NotNull final JsonObject credit);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(
            @NotNull final JsonObject debit);

    @UsecaseName(value = Usecase.TRANSFER)
    void transfer(
            @NotNull final String fromStashId,
            @NotNull final String toStashId,
            @NotNull final LedgerEntryDTO ledgerEntryDTO);

    @UsecaseName(value = Usecase.FETCH_BALANCE)
    long fetchBalance(
            @NotNull final String stashId);

}
