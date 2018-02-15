package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;

import java.sql.SQLException;

public interface IStashDAO {

    double fetchBalance(
            @NotNull String stashId)
            throws SQLException;

    void saveBalance(
            @NotNull String stashId,
            final double balance)
            throws SQLException;
}
