package org.ranjangeorge.mystash.service;

import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.ILedgerService;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.data.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.ServiceLookup;

import java.util.Date;

public class LedgerServiceTest {

    private ILedgerService ledgerService;

    @Before
    public void setUp() {

        ledgerService = ServiceLookup.getService(ILedgerService.class);
    }

    @Test
    public void testSimpleCreditIsSuccessful() {

        ledgerService.credit(
                "my-stash-1",
                new LedgerEntry(
                        CreditOrDebit.CREDIT,
                        100,
                        "Random Credit",
                        new Date()));
    }
}
