package org.ranjangeorge.mystash.service.api.data;

import java.math.BigDecimal;

public enum CreditOrDebit {

    CREDIT {
        @Override
        public BigDecimal applyTo(BigDecimal balance, BigDecimal amount) {
            return balance.add(amount);
        }
    },
    DEBIT {
        @Override
        public BigDecimal applyTo(BigDecimal balance, BigDecimal amount) {
            return balance.subtract(amount);
        }
    };

    public abstract BigDecimal applyTo(
            final BigDecimal balance,
            final BigDecimal amount);
}
