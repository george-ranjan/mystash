package org.ranjangeorge.mystash.service.impl;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stash")
public class Stash {

    @Id
    private String id;

    private String name;

    private String ownerEmail;

    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "stash", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private final List<LedgerEntry> ledger = new ArrayList<>();

    Stash() {
    }

    public Stash(
            @NotNull final String name,
            @NotNull final String ownerEmail) {

        this.id = UUID.randomUUID().toString();
        //
        this.name = name;
        this.ownerEmail = ownerEmail;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getOwnerEmail() {
        return ownerEmail;
    }

    public BigDecimal getBalance() {
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
