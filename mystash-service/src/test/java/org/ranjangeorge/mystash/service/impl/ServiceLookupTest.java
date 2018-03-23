package org.ranjangeorge.mystash.service.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.ranjangeorge.mystash.service.impl.support.ServiceLookup;

public class ServiceLookupTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testLookup_ServiceInterfaceWithoutAnnotation_ShouldThrowIllegalArgumentException() {

        IMyUnannotatedService service = ServiceLookup.getService(IMyUnannotatedService.class);
        //
        expectedException.expect(IllegalArgumentException.class);
        service.someServiceMethod();
    }

    private interface IMyUnannotatedService {

        void someServiceMethod();
    }
}
