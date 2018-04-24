package org.ranjangeorge.mystash.service.ledger;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.ranjangeorge.mystash.service.impl.ledger.Debit;
import org.ranjangeorge.mystash.service.impl.ledger.FetchBalance;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.Json;
import javax.json.JsonObject;
import java.text.ParseException;
import java.time.Instant;

public class DebitTest {

    private static final long INITIAL_CREDIT = 100L;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private DeleteAllStashes deleteAllStashes;

    private Debit debit;

    private FetchBalance fetchBalance;

    private String stashId;

    @Before
    public void setUp() {

        CreateNewStash createNewStash = new CreateNewStash();
        deleteAllStashes = new DeleteAllStashes();
        debit = new Debit();
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

        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testDebitMoreThanBalanceFails() throws ParseException {

        JsonObject debitData = Json.createObjectBuilder()
                .add("stashId", stashId)
                .add("amount", INITIAL_CREDIT + 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        debit.debit(debitData);
    }

    @Test
    public void testDebitWithInvalidStashIdFails() throws ParseException {

        JsonObject debitData = Json.createObjectBuilder()
                .add("stashId", "invalid-stash-id")
                .add("amount", 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        debit.debit(debitData);
    }

    @Test
    public void testDebitWithOutStashIdFails() throws ParseException {

        JsonObject debitData = Json.createObjectBuilder()
                .add("amount", 100L)
                .add("description", "Random Credit")
                .add("txndate", new DateStringConverter().toString(Instant.now())).build();
        //
        expectedException.expect(IllegalArgumentException.class);
        debit.debit(debitData);
    }
}
