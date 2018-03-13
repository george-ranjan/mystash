package org.ranjangeorge.mystash.service.api.data;

import com.sun.istack.internal.NotNull;

import java.util.Date;

public class LedgerEntry {

    private CreditOrDebit creditOrDebit;

    private double amount;

    private String description;

    private Date date;

    public LedgerEntry(
            @NotNull final CreditOrDebit creditOrDebit,
            final double amount,
            @NotNull final String description,
            @NotNull final Date date) {

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
