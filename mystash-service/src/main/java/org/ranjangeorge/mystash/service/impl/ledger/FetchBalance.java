package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.SessionFactory;
import org.ranjangeorge.mystash.service.api.data.Stash;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

@UsecaseNames(Usecase.FETCH_BALANCE)
public class FetchBalance {

    private final SessionFactory sessionFactory;

    public FetchBalance(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public double fetchBalance(String stashId) {

        return sessionFactory.getCurrentSession()
                .load(Stash.class, stashId)
                .getBalance();
    }
}
