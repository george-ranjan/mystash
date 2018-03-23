package org.ranjangeorge.mystash.service.api;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;

public interface ILedgerService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO entry);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry entry);

    @UsecaseName(value = Usecase.FETCH_BALANCE)
    double fetchBalance(
            @NotNull final String stashId);
}
