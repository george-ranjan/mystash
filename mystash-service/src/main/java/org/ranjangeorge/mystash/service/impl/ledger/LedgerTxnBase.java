package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.data.Stash;

abstract class LedgerTxnBase {

    @NotNull LedgerEntry toLedgerEntry(
            @NotNull final Stash stash,
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

        return new LedgerEntry(
                stash,
                creditOrDebit,
                ledgerEntryDTO.getAmount(),
                ledgerEntryDTO.getDescription(),
                ledgerEntryDTO.getDate());
    }
}
