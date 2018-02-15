package org.ranjangeorge.mystash.service.user;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;

public class CreateUserService {

    private IStashDAO myStashDAO;

    public CreateUserService(
            @NotNull final IStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}
