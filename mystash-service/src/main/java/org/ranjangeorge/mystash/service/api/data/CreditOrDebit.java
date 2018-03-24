package org.ranjangeorge.mystash.service.api.data;

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
            final double balance,
            final double amount);
}
