package org.ranjangeorge.mystash.service;

import org.ranjangeorge.mystash.service.data.CreditInfo;
import org.ranjangeorge.mystash.service.data.TransferInfo;

public interface ILedgerService {

    @UsecaseName(value = Usecase.CREDIT)
    void record(CreditInfo credit);

    @UsecaseName(value = Usecase.TRANSFER)
    void transfer(TransferInfo transfer);
}
