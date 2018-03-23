package org.ranjangeorge.mystash.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.IStashAdminService;
import org.ranjangeorge.mystash.service.impl.ServiceLookup;

import java.util.Set;

public class StashAdminServiceTest {

    private IStashAdminService stashAdminService;

    @Before
    public void setUp() {

        stashAdminService = ServiceLookup.getService(IStashAdminService.class);
        stashAdminService.deleteAllStashes();
    }

    @After
    public void teardown() {

        stashAdminService.deleteAllStashes();
    }


    @Test
    public void testCreateNewStash() {

        stashAdminService.createNewStash("my-new-stash");
        //
        Set<String> stashes = stashAdminService.list();
        //
        Assert.assertEquals(1, stashes.size());
    }
}