package org.ranjangeorge.mystash;

public interface IMyStashService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(CreditInfo credit);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(DebitInfo debit);

    @UsecaseName(value = Usecase.FETCH_BALANCE)
    double fetchBalance();
}
