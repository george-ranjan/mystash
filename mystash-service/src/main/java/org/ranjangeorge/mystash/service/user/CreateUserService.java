package org.ranjangeorge.mystash.service.user;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.IMyStashDAO;

public class CreateUserService {

    private IMyStashDAO myStashDAO;

    public CreateUserService(
            @NotNull final IMyStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}
