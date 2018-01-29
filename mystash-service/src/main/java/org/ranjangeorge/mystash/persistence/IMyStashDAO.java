package org.ranjangeorge.mystash.persistence;

import com.sun.istack.internal.NotNull;

import java.sql.SQLException;

public interface IMyStashDAO {

    <E> void save(
            @NotNull final Class<E> entityType,
            @NotNull final Object entity)
            throws SQLException;

    <E> void update(
            @NotNull final Class<E> entityType,
            @NotNull final Object entity)
            throws SQLException;

    <E, EID> E fetch(
            @NotNull final Class<E> entityType,
            @NotNull final EID idOfObjectToFetch)
            throws SQLException;

    <E, EID> void delete(
            @NotNull final Class<E> entityType,
            @NotNull final EID idOfObjectToDelete)
            throws SQLException;
}
