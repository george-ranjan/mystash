package org.ranjangeorge.mystash.service.api.data;

import java.util.ArrayList;
import java.util.List;

public class Stash {

    private double balance = 0d;

    private List<LedgerEntry> ledger = new ArrayList<>();

    public double getBalance() {
        return balance;
    }

    public void recordEntry(LedgerEntry entry) {

        updateBalance(entry);
        ledger.add(entry);
    }

    private void updateBalance(LedgerEntry entry) {

        balance = entry.getCreditOrDebit().applyTo(
                balance, entry.getAmount());
    }
}
