package org.ranjangeorge.mystash.persistence;

import org.ranjangeorge.mystash.service.data.CreditInfo;
import org.ranjangeorge.mystash.service.data.DebitInfo;

import java.sql.*;

public class MyStashDB {

    private String dbConnectionUrl;

    private String dbUserName;

    private String dbPassword;

    public MyStashDB(
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

    public double fetchBalance() throws SQLException {

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

    public void saveBalance(double balance) throws SQLException {

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

    public void saveCredit(CreditInfo theCredit)
            throws SQLException {

        // Update Balance
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into transaction_log (amount, description, " +
                            "txntime, txntype) " +
                            "values (?, ?, ?, ?)")) {

                stmt.setDouble(1,
                        theCredit.getAmount());
                stmt.setString(2,
                        theCredit.getDescription());
                stmt.setTimestamp(3,
                        new Timestamp(theCredit.getDate().getTime()));
                stmt.setString(4,
                        "CREDIT");

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

    public void saveDebit(DebitInfo theDebit)
            throws SQLException {

        // Update Balance
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "insert into transaction_log (amount, description, " +
                            "txntime, txntype) " +
                            "values (?, ?, ?, ?)")) {

                stmt.setDouble(1,
                        theDebit.getAmount());
                stmt.setString(2,
                        theDebit.getDescription());
                stmt.setTimestamp(3,
                        new Timestamp(theDebit.getDate().getTime()));
                stmt.setString(4,
                        "DEBIT");

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
