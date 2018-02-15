package org.ranjangeorge.mystash.persistence.dao.impl;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;

import java.sql.*;

public class StashDAO implements IStashDAO {

    private String dbConnectionUrl;

    private String dbUserName;

    private String dbPassword;

    public StashDAO(
            String theDBConnectionUrl,
            String theDBUser,
            String theDBPassword)
            throws ClassNotFoundException {

        // DB Connection Information
        dbConnectionUrl = theDBConnectionUrl;
        dbUserName = theDBUser;
        dbPassword = theDBPassword;

        // Register Driver
        Class.forName("org.postgresql.Driver");
    }

    @Override
    public double fetchBalance(
            @NotNull final String stashId)
            throws SQLException {

        // Get Balance
        double balance;
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "select balance from mystash");
                 ResultSet rs = stmt.executeQuery()) {

                rs.next();
                balance = rs.getDouble("balance");
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }

        return balance;
    }

    @Override
    public void saveBalance(
            @NotNull final String stashId,
            final double balance)
            throws SQLException {

        // Update Balance
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "update mystash set balance = ?")) {

                stmt.setDouble(1, balance);
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated != 1) {
                    throw new SQLException("Unexpected error.  " +
                            "One row should have been updated, but was " +
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
