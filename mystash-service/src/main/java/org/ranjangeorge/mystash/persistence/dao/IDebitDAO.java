package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.data.DebitInfo;

public interface IDebitDAO {

    void saveDebit(
            @NotNull final DebitInfo theDebit);
}
