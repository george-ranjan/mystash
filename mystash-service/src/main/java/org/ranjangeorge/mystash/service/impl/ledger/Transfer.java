package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.TRANSFER)
public class Transfer {

    public void transfer(
            @NotNull final String fromStashId,
            @NotNull final String toStashId,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

    }
}
