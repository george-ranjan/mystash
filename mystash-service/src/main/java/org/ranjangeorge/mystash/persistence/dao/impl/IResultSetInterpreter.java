package org.ranjangeorge.mystash.persistence.dao.impl;

import com.sun.istack.internal.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetInterpreter<T> {

    T interpret(@NotNull final ResultSet resultSet)
            throws SQLException;
}
