package org.ranjangeorge.mystash.service.api.data;

import org.jetbrains.annotations.NotNull;

public enum CreditOrDebit {

    CREDIT {
        @Override
        public double applyTo(double balance, double amount) {
            return balance + amount;
        }
    },
    DEBIT {
        @Override
        public double applyTo(double balance, double amount) {
            return balance - amount;
        }
    };

    public abstract double applyTo(
            @NotNull final double balance,
            @NotNull final double amount);
}
