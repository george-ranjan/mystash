package org.ranjangeorge.mystash.service.ledger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public abstract class TxnBaseTest {

    static final long INITIAL_CREDIT = 100L;

    private DeleteAllStashes deleteAllStashes;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    String stashId;

    @Before
    public void setUp() {

        CreateNewStash createNewStash = new CreateNewStash();
        deleteAllStashes = new DeleteAllStashes();
        //
        stashId = createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-stash-1")
                        .add("email", "george.ranjan@gmail.com")
                        .add("initialCredit", INITIAL_CREDIT).build())
                .getString("id");
    }

    @After
    public void teardown() {

        deleteAllStashes.deleteAllStashes();
    }

    @Test
    public void testTransactionWithInvalidStashIdFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", "invalid-stash-id")
                .add("amount", 100L)
                .add("description", "Random Transaction")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithOutStashIdFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("amount", 100L)
                .add("description", "Random Transaction")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithOutAmountFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("description", "Random Transaction")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithNegativeAmountFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", -10L)
                .add("description", "Random Transaction")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithOutTransactionDateFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", 100L)
                .add("description", "Random Transaction").build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithOutTransactionDateInFutureFails() throws ParseException {

        Instant futureDate = Instant.now().plus(1, ChronoUnit.DAYS);
        //
        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", 100L)
                .add("description", "Random Transaction")
                .add("txndate", new DateStringConverter().toString(futureDate)).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    @Test
    public void testTransactionWithOutTransactionDescriptionFails() throws ParseException {

        JsonObject txnData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", 100L)
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        doTransaction(txnData);
    }

    protected abstract void doTransaction(JsonObject txnData) throws ParseException;
}
