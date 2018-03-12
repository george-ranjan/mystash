package org.ranjangeorge.mystash.service.api;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;

public interface ICreditService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntry entry);

}
