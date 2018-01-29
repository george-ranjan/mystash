package org.ranjangeorge.mystash.persistence;

import com.sun.istack.internal.NotNull;

public class MyStashDAO implements IMyStashDAO {

    @Override
    public <E> void save(
            @NotNull final Class<E> entityType,
            @NotNull final Object entity) {

    }

    @Override
    public <E> void update(
            @NotNull final Class<E> entityType,
            @NotNull final Object entity) {

    }

    @Override
    public <E, EID> E fetch(
            @NotNull final Class<E> entityType,
            @NotNull final EID idOfObjectToFetch) {
        return null;
    }

    @Override
    public <E, EID> void delete(
            @NotNull final Class<E> entityType,
            @NotNull final EID idOfObjectToDelete) {

    }
}
