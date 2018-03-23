package org.ranjangeorge.mystash.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.ILedgerService;
import org.ranjangeorge.mystash.service.api.IStashAdminService;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.impl.support.ServiceLookup;

import java.util.Date;

public class LedgerServiceTest {

    private IStashAdminService stashAdminService;

    private ILedgerService ledgerService;

    private String stashId;

    @Before
    public void setUp() {

        stashAdminService = ServiceLookup.getService(IStashAdminService.class);
        stashId = stashAdminService.createNewStash("my-stash-1");
        //
        ledgerService = ServiceLookup.getService(ILedgerService.class);
    }

    @After
    public void teardown() {

        stashAdminService.deleteAllStashes();
    }

    @Test
    public void testSimpleCreditIsSuccessful() {

        ledgerService.credit(
                stashId,
                new LedgerEntryDTO(
                        100d,
                        "Random Credit",
                        new Date()));
        //
        double balance = ledgerService.fetchBalance(stashId);
        //
        Assert.assertEquals(100d, balance, 0d);
    }

    @Test
    public void testSimpleDebitIsSuccessful() {

        ledgerService.debit(
                stashId,
                new LedgerEntryDTO(
                        100d,
                        "Random Credit",
                        new Date()));
        //
        double balance = ledgerService.fetchBalance(stashId);
        //
        Assert.assertEquals(-100d, balance, 0d);
    }
}
