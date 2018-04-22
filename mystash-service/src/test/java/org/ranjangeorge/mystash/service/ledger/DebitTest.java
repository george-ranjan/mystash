package org.ranjangeorge.mystash.service.ledger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.ILedgerService;
import org.ranjangeorge.mystash.service.api.IStashAdminService;
import org.ranjangeorge.mystash.service.impl.support.ServiceLookup;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.time.Instant;

public class DebitTest {

    private static final long INITIAL_CREDIT = 100L;

    private IStashAdminService stashAdminService;

    private ILedgerService ledgerService;

    private String stashId;

    @Before
    public void setUp() {

        stashAdminService = ServiceLookup.getService(IStashAdminService.class);
        stashId = stashAdminService.createNewStash(
                "my-stash-1",
                "george.ranjan@gmail.com",
                INITIAL_CREDIT).getString("stashId");
        //
        ledgerService = ServiceLookup.getService(ILedgerService.class);
    }

    @After
    public void teardown() {

        stashAdminService.deleteAllStashes();
    }


    @Test
    public void testSimpleDebitIsSuccessful() {

        JsonObject debit = Json.createObjectBuilder()
                .add("amount", BigDecimal.valueOf(100d))
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();

        ledgerService.debit(
                stashId,
                debit);
        //
        long actualBalance = ledgerService.fetchBalance(stashId);
        //
        long expectedBalance = INITIAL_CREDIT - 100L;

        Assert.assertEquals(expectedBalance, actualBalance);
    }
}
