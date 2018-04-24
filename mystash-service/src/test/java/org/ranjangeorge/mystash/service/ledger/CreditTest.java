package org.ranjangeorge.mystash.service.ledger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.impl.ledger.Credit;
import org.ranjangeorge.mystash.service.impl.ledger.FetchBalance;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.stashadmin.ListAllStashes;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.ParseException;
import java.time.Instant;

public class CreditTest {

    private static final long INITIAL_CREDIT = 100L;

    private DeleteAllStashes deleteAllStashes;

    private Credit credit;

    private FetchBalance fetchBalance;

    private String stashId;

    @Before
    public void setUp() {

        CreateNewStash createNewStash = new CreateNewStash();
        deleteAllStashes = new DeleteAllStashes();
        credit = new Credit();
        fetchBalance = new FetchBalance();
        //
        stashId = createNewStash.createNewStash(
                "my-stash-1",
                "george.ranjan@gmail.com",
                INITIAL_CREDIT).getString("stashId");
    }

    @After
    public void teardown() {

        deleteAllStashes.deleteAllStashes();
    }

    @Test
    public void testSimpleCreditIsSuccessful() throws ParseException {

        JsonObject creditData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();

        credit.credit(creditData);
        //
        long actualBalance = fetchBalance.fetchBalance(stashId);
        //
        long expectedBalance = INITIAL_CREDIT + 100L;
        Assert.assertEquals(expectedBalance, actualBalance);
    }
}
