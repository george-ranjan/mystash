package org.ranjangeorge.mystash.service.api.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stash")
public class Stash {

    @Id
    private String id;

    private double balance = 0d;

    @OneToMany(mappedBy = "stash", cascade = {CascadeType.ALL})
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
