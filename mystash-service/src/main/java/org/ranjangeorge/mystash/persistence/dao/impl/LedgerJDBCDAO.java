package org.ranjangeorge.mystash.persistence.dao.impl;

import org.ranjangeorge.mystash.persistence.dao.ILedgerDAO;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.sql.SQLException;

public class LedgerJDBCDAO implements ILedgerDAO {

    private SQLExecutor sqlExecutor;

    public LedgerJDBCDAO(SQLExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    @Override
    public void record(LedgerEntry theEntry) throws SQLException {

        String insertLedgerEntrySQL = "insert into ledger "
                + "(amount, description, txntime, txntype) "
                + "values (?, ?, ?, ?)";

        sqlExecutor.executeUpdate(
                insertLedgerEntrySQL,
                theEntry.getAmount(),
                theEntry.getDescription(),
                theEntry.getDate().getTime(),
                theEntry.getCreditOrDebit().name());
    }

}
