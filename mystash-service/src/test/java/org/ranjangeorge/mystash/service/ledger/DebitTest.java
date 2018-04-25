package org.ranjangeorge.mystash.service.ledger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.impl.ledger.Debit;
import org.ranjangeorge.mystash.service.impl.ledger.FetchBalance;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.ParseException;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class DebitTest extends TxnBaseTest {


    private Debit debit;

    private FetchBalance fetchBalance;

    @Before
    public void setUp() {
        super.setUp();
        debit = new Debit();
        fetchBalance = new FetchBalance();
    }

    @Override
    protected void doTransaction(JsonObject debitData) throws ParseException {
        debit.debit(debitData);
    }

    @Test
    public void testSimpleDebitIsSuccessful() throws ParseException {

        JsonObject debitData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();

        debit.debit(debitData);
        //
        long actualBalance = fetchBalance.fetchBalance(stashId);
        //
        long expectedBalance = INITIAL_CREDIT - 100L;

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testDebitWithAmountMoreThanBalanceFails() throws ParseException {

        JsonObject debitData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", INITIAL_CREDIT + 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        debit.debit(debitData);
    }
}
