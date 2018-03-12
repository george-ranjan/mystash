package org.ranjangeorge.mystash.service.api;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;

public interface IDebitService {

    @UsecaseName(value = Usecase.DEBIT)
    void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry entry);
}
