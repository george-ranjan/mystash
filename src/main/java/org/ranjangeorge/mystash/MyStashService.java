package org.ranjangeorge.mystash;

import java.sql.*;

public class MyStashService {

    private String dbConnectionUrl;

    private String dbUserName;

    private String dbPassword;

    public MyStashService(
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

    // Record an income
    public void credit(double theAmount) throws SQLException {

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

        // Increase the balance by the amount
        balance = balance + theAmount;

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

    // Record expense
    public void debit(double theAmount) throws SQLException {

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

        // Reduce the balance by the amount
        balance = balance - theAmount;

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

    // Track my balance
    public double getBalance() throws SQLException {

        // Get Balance
        try (Connection conn = DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword)) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "select balance from mystash");
                 ResultSet rs = stmt.executeQuery()) {

                rs.next();
                double balance = rs.getDouble("balance");
                conn.commit();

                return balance;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
