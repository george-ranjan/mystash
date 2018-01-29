package org.ranjangeorge.mystash.service;

import org.ranjangeorge.mystash.service.data.CreditInfo;
import org.ranjangeorge.mystash.service.data.DebitInfo;
import org.ranjangeorge.mystash.service.data.TransferInfo;

public interface ITransactionService {

    @UsecaseName(value = Usecase.CREDIT)
    void credit(CreditInfo credit);

    @UsecaseName(value = Usecase.DEBIT)
    void debit(DebitInfo debit);

    @UsecaseName(value = Usecase.TRANSFER)
    void transfer(TransferInfo transfer);
}
