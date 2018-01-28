package org.ranjangeorge.mystash;

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
}
