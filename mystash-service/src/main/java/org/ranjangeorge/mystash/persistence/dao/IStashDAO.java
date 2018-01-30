package org.ranjangeorge.mystash.persistence.dao;

import com.sun.istack.internal.NotNull;

public interface IStashDAO {

    void createStash(
            @NotNull final String name,
            @NotNull final String description);

    double fetchBalance(
            @NotNull final String stashId);

    void saveBalance(
            @NotNull final String stashId,
            final double balance);
}
