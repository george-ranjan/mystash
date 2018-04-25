package org.ranjangeorge.mystash.service.stashadmin;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.stashadmin.ListAllStashes;

import javax.json.Json;
import javax.json.JsonArray;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateStashTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CreateNewStash createNewStash;

    private DeleteAllStashes deleteAllStashes;

    private ListAllStashes listAllStashes;

    @Before
    public void setUp() {

        createNewStash = new CreateNewStash();
        deleteAllStashes = new DeleteAllStashes();
        listAllStashes = new ListAllStashes();
        //
        deleteAllStashes.deleteAllStashes();
    }

    @After
    public void teardown() {

        deleteAllStashes.deleteAllStashes();
    }


    @Test
    public void testCreateNewStash() {

        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-new-stash")
                        .add("email", "george.ranjan@gmail.com")
                        .add("initialCredit", 100L).build());
        //
        JsonArray stashes = listAllStashes.listAllStashes();
        //
        assertEquals(1, stashes.size());
        assertNotNull(stashes.getJsonObject(0).getString("id"));
        assertEquals("my-new-stash", stashes.getJsonObject(0).getString("name"));
        assertEquals("george.ranjan@gmail.com", stashes.getJsonObject(0).getString("email"));
        assertEquals(100L, stashes.getJsonObject(0).getJsonNumber("balance").longValue());
    }

    @Test
    public void testCreateNewStashWithoutNameFails() {

        expectedException.expect(IllegalArgumentException.class);
        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("email", "george.ranjan@gmail.com")
                        .add("initialCredit", 100L).build());
    }

    @Test
    public void testCreateNewStashWithoutEmailFails() {

        expectedException.expect(IllegalArgumentException.class);
        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-new-stash")
                        .add("initialCredit", 100L).build());
    }

    @Test
    public void testCreateNewStashWithoutInitialCreditFails() {

        expectedException.expect(IllegalArgumentException.class);
        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-new-stash")
                        .add("email", "george.ranjan@gmail.com").build());
    }

    @Test
    public void testCreateNewStashWithNegativeInitialCreditFails() {

        expectedException.expect(IllegalArgumentException.class);
        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-new-stash")
                        .add("email", "george.ranjan@gmail.com")
                        .add("initialCredit", -100L).build());
    }

    @Test
    public void testCreateNewStashWithZeroInitialCreditShouldBeAllowed() {

        createNewStash.createNewStash(
                Json.createObjectBuilder()
                        .add("name", "my-new-stash")
                        .add("email", "george.ranjan@gmail.com")
                        .add("initialCredit", 0).build());
        //
        JsonArray stashes = listAllStashes.listAllStashes();
        //
        assertEquals(1, stashes.size());
        assertNotNull(stashes.getJsonObject(0).getString("id"));
        assertEquals("my-new-stash", stashes.getJsonObject(0).getString("name"));
        assertEquals("george.ranjan@gmail.com", stashes.getJsonObject(0).getString("email"));
        assertEquals(0, stashes.getJsonObject(0).getJsonNumber("balance").longValue());
    }
}
