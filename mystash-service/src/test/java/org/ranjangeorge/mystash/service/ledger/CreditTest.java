package org.ranjangeorge.mystash.service.ledger;

import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.impl.ledger.Credit;
import org.ranjangeorge.mystash.service.impl.ledger.FetchBalance;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.ParseException;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class CreditTest extends TxnBaseTest {

    private Credit credit;

    private FetchBalance fetchBalance;

    @Before
    public void setUp() {

        super.setUp();

        credit = new Credit();
        fetchBalance = new FetchBalance();
    }

    @Override
    protected void doTransaction(JsonObject txnData) throws ParseException {
        credit.credit(txnData);
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
        assertEquals(expectedBalance, actualBalance);
    }
}
