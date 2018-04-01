package org.ranjangeorge.mystash.service.ledger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.ILedgerService;
import org.ranjangeorge.mystash.service.api.IStashAdminService;
import org.ranjangeorge.mystash.service.api.data.LedgerEntryDTO;
import org.ranjangeorge.mystash.service.impl.support.ServiceLookup;

import java.util.Date;

public class TransferTest {

    private static final double TEST_AMOUNT = 100d;

    private IStashAdminService stashAdminService;

    private ILedgerService ledgerService;

    private String fromStashId;

    private String toStashId;

    @Before
    public void setUp() {

        stashAdminService = ServiceLookup.getService(IStashAdminService.class);
        ledgerService = ServiceLookup.getService(ILedgerService.class);
        //
        fromStashId = stashAdminService.createNewStash("from-stash");
        toStashId = stashAdminService.createNewStash("to-stash");
        //
        creditFromStashWithInitialAmount(TEST_AMOUNT + 100d);
    }

    private void creditFromStashWithInitialAmount(double initialAmount) {

        ledgerService.credit(
                fromStashId,
                new LedgerEntryDTO(
                        initialAmount,
                        "Initial Amount",
                        new Date()));
    }

    @After
    public void teardown() {

        stashAdminService.deleteAllStashes();
    }

    @Test
    public void testSimpleTransferIsSuccessful() {

        double fromStashBalanceBefore = ledgerService.fetchBalance(fromStashId);
        double toStashBalanceBefore = ledgerService.fetchBalance(toStashId);
        //
        ledgerService.transfer(
                fromStashId, toStashId,
                new LedgerEntryDTO(
                        100d,
                        "Random Transfer",
                        new Date()));
        //
        double fromStashBalanceAfter = ledgerService.fetchBalance(fromStashId);
        double toStashBalanceAfter = ledgerService.fetchBalance(toStashId);
        //
        Assert.assertEquals("Should have reduced from-stash balance by " + TEST_AMOUNT,
                -TEST_AMOUNT, (fromStashBalanceAfter - fromStashBalanceBefore), 0d);
        Assert.assertEquals("Should have increased to-stash balance by " + TEST_AMOUNT,
                TEST_AMOUNT, (toStashBalanceAfter - toStashBalanceBefore), 0d);
    }
}
