package org.ranjangeorge.mystash.service.api.data;

public enum CreditOrDebit {

    CREDIT {
        @Override
        public long applyTo(long balance, long amount) {
            return balance + amount;
        }
    },
    DEBIT {
        @Override
        public long applyTo(long balance, long amount) {
            return balance - amount;
        }
    };

    public abstract long applyTo(
            final long balance,
            final long amount);
}
