package org.ranjangeorge.mystash.service.txn;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.IMyStashDAO;

public class TransferService {

    private IMyStashDAO myStashDAO;

    public TransferService(
            @NotNull final IMyStashDAO myStashDAO) {
        this.myStashDAO = myStashDAO;
    }
}


