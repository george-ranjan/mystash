package org.ranjangeorge.mystash.persistence.dao.impl;

import java.sql.*;

public class SQLExecutor {

    private String dbConnectionUrl;

    private String dbUserName;

    private String dbPassword;

    /**
     * Client code of this method knows the SQL statement
     * and hence knows right order the parameters need to be in.
     */
    public void SQLExecutor(
            String dbConnectionUrl,
            String dbUserName,
            String dbPassword) {

        this.dbConnectionUrl = dbConnectionUrl;
        this.dbUserName = dbUserName;
        this.dbPassword = dbPassword;
    }

    public void executeUpdate(String updateSql, Object... parameters)
            throws SQLException {

        try (Connection conn = getConnection()) {

            conn.setAutoCommit(false);

            try {

                executeUpdate(updateSql, conn, parameters);
                conn.commit();

            } catch (SQLException e) {

                conn.rollback();
                throw e;
            }
        }
    }

    public <T> T executeQuery(
            String querySql,
            IResultSetInterpreter<T> resultSetInterpreter,
            Object... parameters)
            throws SQLException {

        try (Connection connection = getConnection()) {

            connection.setAutoCommit(false);

            try {

                T result = executeQuery(querySql,
                        connection,
                        parameters,
                        resultSetInterpreter);

                connection.commit();

                return result;

            } catch (SQLException e) {

                connection.rollback();
                throw e;
            }
        }
    }

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                dbConnectionUrl, dbUserName, dbPassword);
    }

    private void executeUpdate(
            String updateSql,
            Connection conn,
            Object[] parameters)
            throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {

            for (int index = 1; index <= parameters.length; index++) {
                stmt.setObject(index, parameters[index]);
            }

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated != 1) {
                throw new SQLException("Should have inserted 1 " +
                        "record, rows inserted were " +
                        rowsUpdated);
            }
        }
    }

    private <T> T executeQuery(
            String querySql,
            Connection conn,
            Object[] parameters,
            IResultSetInterpreter<T> resultSetInterpreter)
            throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(querySql)) {

            for (int index = 1; index <= parameters.length; index++) {
                stmt.setObject(index, parameters[index]);
            }

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSetInterpreter.interpret(resultSet);
            }
        }
    }
}
