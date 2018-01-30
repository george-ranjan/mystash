package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.data.TransactionInfo;

public interface ITransactionDAO {

    void saveTransaction(
            @NotNull final TransactionInfo theCredit);
}
