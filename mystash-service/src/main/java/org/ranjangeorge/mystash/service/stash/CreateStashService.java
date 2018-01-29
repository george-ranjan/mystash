package org.ranjangeorge.mystash.service.stash;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.IMyStashDAO;

public class CreateStashService {

    private IMyStashDAO myStashDAO;

    public CreateStashService(
            @NotNull final IMyStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}
