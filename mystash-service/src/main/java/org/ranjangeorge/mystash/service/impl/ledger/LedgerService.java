package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames({Usecase.CREDIT, Usecase.DEBIT})
public class LedgerService {

    private final SessionFactory sessionFactory;

    public LedgerService(
            @NotNull final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO credit) {

        recordEntry(stashId, CreditOrDebit.CREDIT, credit);
    }

    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO debit) {

        recordEntry(stashId, CreditOrDebit.DEBIT, debit);
    }

    private void recordEntry(
            @NotNull final String stashId,
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final LedgerEntryDTO ledgerEntryDTO) {

        // Fetch Stash
        Stash stash = sessionFactory.getCurrentSession()
                .load(Stash.class, stashId);

        // Record Entry
        LedgerEntry ledgerEntry = toLedgerEntry(
                stash,
                creditOrDebit,
                ledgerEntryDTO);

        stash.recordEntry(ledgerEntry);

    }

    @NotNull
    private LedgerEntry toLedgerEntry(
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
