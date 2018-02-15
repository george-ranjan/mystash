package org.ranjangeorge.mystash.service.txn;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;

public class TransferService {

    private IStashDAO myStashDAO;

    public TransferService(
            @NotNull final IStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}


