package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseName;

public interface ILedgerService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO entry);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO entry);

    @UsecaseName(value = Usecase.TRANSFER)
    void transfer(
            @NotNull final String fromStashId,
            @NotNull final String toStashId,
            @NotNull final LedgerEntryDTO ledgerEntryDTO);

    @UsecaseName(value = Usecase.FETCH_BALANCE)
    double fetchBalance(
            @NotNull final String stashId);

}
