package org.ranjangeorge.mystash.service.stashadmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.impl.stashadmin.CreateNewStash;
import org.ranjangeorge.mystash.service.impl.stashadmin.DeleteAllStashes;
import org.ranjangeorge.mystash.service.impl.stashadmin.ListAllStashes;

import javax.json.JsonArray;

public class CreateStashTest {

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
                "my-new-stash",
                "george.ranjan@gmail.com",
                100L);
        //
        JsonArray stashes = listAllStashes.listAllStashes();
        //
        Assert.assertEquals(1, stashes.size());
    }
}
