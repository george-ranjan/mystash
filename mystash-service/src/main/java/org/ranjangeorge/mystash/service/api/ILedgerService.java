package org.ranjangeorge.mystash.service.api;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;

public interface ILedgerService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry entry);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry entry);
}
