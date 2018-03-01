package org.ranjangeorge.mystash.persistence.dao.impl;

import com.sun.istack.internal.NotNull;
import org.ranjangeorge.mystash.persistence.dao.IStashDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StashJDBCDAO implements IStashDAO {

    private SQLExecutor sqlExecutor;

    public StashJDBCDAO(
            SQLExecutor sqlExecutor) {

        this.sqlExecutor = sqlExecutor;
    }

    private static class BalanceInterpreter implements IResultSetInterpreter<Double> {

        @Override
        public Double interpret(ResultSet resultSet) throws SQLException {

            resultSet.next();
            return resultSet.getDouble("balance");
        }
    }

    @Override
    public double fetchBalance(
            @NotNull final String stashId)
            throws SQLException {

        return sqlExecutor.executeQuery(
                "select balance from mystash where id = ?",
                new BalanceInterpreter(),
                stashId);
    }

    @Override
    public void saveBalance(
            @NotNull final String stashId,
            final double balance)
            throws SQLException {

        String updateBalanceSql = "update mystash set balance = ?";

        sqlExecutor.executeUpdate(
                updateBalanceSql,
                balance);
    }
}
