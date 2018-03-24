package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.CREDIT)
public class CreditLedger extends LedgerTxnBase {

    public CreditLedger(
            @NotNull final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void credit(
            @NotNull final String stashId,
            @NotNull final LedgerEntryDTO credit) {

        recordEntry(stashId, credit, CreditOrDebit.CREDIT);
    }
}
