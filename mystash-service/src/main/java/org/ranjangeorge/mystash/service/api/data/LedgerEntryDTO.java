package org.ranjangeorge.mystash.service.api.data;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class LedgerEntryDTO {

    private final double amount;

    private final String description;

    private final Date date;

    public LedgerEntryDTO(
            final double amount,
            @NotNull final String description,
            @NotNull final Date date) {

        this.amount = amount;
        this.description = description;
        this.date = date;
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
