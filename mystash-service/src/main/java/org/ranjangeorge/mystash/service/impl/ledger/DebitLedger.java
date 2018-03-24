package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.DEBIT)
public class DebitLedger extends LedgerTxnBase {

    public DebitLedger(
            @NotNull final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void debit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO debit) {

        recordEntry(stashId, debit, CreditOrDebit.DEBIT);
    }
}
