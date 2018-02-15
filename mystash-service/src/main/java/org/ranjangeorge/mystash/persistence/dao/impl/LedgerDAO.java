package org.ranjangeorge.mystash.persistence.dao.impl;

import org.ranjangeorge.mystash.persistence.dao.ILedgerDAO;
import org.ranjangeorge.mystash.service.data.LedgerEntry;

import java.sql.*;

public class LedgerDAO implements ILedgerDAO {

    private String dbConnectionUrl;

    private String dbUserName;

    private String dbPassword;

    public LedgerDAO(String dbConnectionUrl, String dbUserName, String dbPassword) {
        this.dbConnectionUrl = dbConnectionUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    @Override
    public void record(LedgerEntry theEntry) throws SQLException {

        // Update Balance
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into transaction_log (amount, description, " +
                            "txntime, txntype) " +
                            "values (?, ?, ?, ?)")) {

                stmt.setDouble(1,
                        theEntry.getAmount());
                stmt.setString(2,
                        theEntry.getDescription());
                stmt.setTimestamp(3,
                        new Timestamp(theEntry.getDate().getTime()));
                stmt.setString(4,
                        theEntry.getCreditOrDebit().name());

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated != 1) {
                    throw new SQLException("Should have inserted 1 " +
                            "transaction, rows inserted were " +
                            rowsUpdated);
                }

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
