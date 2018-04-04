package org.ranjangeorge.mystash.service.stashadmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ranjangeorge.mystash.service.api.IStashAdminService;
import org.ranjangeorge.mystash.service.impl.support.ServiceLookup;

import java.math.BigDecimal;
import java.util.Set;

public class CreateStashTest {

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

        stashAdminService.createNewStash(
                "my-new-stash",
                "george.ranjan@gmail.com",
                BigDecimal.valueOf(100d));
        //
        Set<String> stashes = stashAdminService.listAllStashes();
        //
        Assert.assertEquals(1, stashes.size());
    }
}
