package org.ranjangeorge.mystash.service.stash;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;

public class CreateStashService {

    private IStashDAO myStashDAO;

    public CreateStashService(
            @NotNull final IStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}
