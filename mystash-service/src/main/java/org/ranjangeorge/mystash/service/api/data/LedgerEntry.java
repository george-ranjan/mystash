package org.ranjangeorge.mystash.service.api.data;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ledgerentry")
public class LedgerEntry {

    @ManyToOne
    @JoinColumn(name = "stashid")
    private Stash stash;

    @Id
    private String id;

    private CreditOrDebit creditOrDebit;

    private double amount;

    private String description;

    private Date date;

    LedgerEntry() {
    }

    public LedgerEntry(
            Stash stash, @NotNull final CreditOrDebit creditOrDebit,
            final double amount,
            @NotNull final String description,
            @NotNull final Date date) {

        id = UUID.randomUUID().toString();
        //
        this.stash = stash;
        this.creditOrDebit = creditOrDebit;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public CreditOrDebit getCreditOrDebit() {
        return creditOrDebit;
    }

    public boolean isCredit() {
        return CreditOrDebit.CREDIT.equals(creditOrDebit);
    }

    public boolean isDebit() {
        return CreditOrDebit.DEBIT.equals(creditOrDebit);
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
