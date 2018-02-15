package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.sql.SQLException;

public interface ILedgerDAO {

    void record(
            @NotNull final LedgerEntry theEntry)
            throws SQLException;
}
