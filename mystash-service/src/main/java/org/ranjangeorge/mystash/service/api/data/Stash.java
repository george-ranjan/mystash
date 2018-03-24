package org.ranjangeorge.mystash.service.api.data;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stash")
public class Stash {

    @Id
    private String id;

    private String name;

    private double balance = 0d;

    @OneToMany(mappedBy = "stash", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private final List<LedgerEntry> ledger = new ArrayList<>();

    Stash() {
    }

    public Stash(@NotNull String name) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
