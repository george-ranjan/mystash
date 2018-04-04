package org.ranjangeorge.mystash.service.impl.ledger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.IEmailService;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.impl.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;
import org.ranjangeorge.mystash.service.impl.support.lang.DateStringConverter;

import javax.json.JsonObject;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

abstract class TxnBase {

    private IEmailService emailService;

    TxnBase() {
        emailService = IEmailService.getEmailService();
    }

    void recordEntry(
            @NotNull final String stashId,
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final JsonObject ledgerEntryJson)
            throws ParseException {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        String stashOwnerEmail = null;
        try {

            // Fetch Stash
            Stash stash = session.load(Stash.class, stashId);
            if (stash == null) {
                throw new IllegalArgumentException("Invalid id specified for stash");
            }

            stashOwnerEmail = stash.getOwnerEmail();

            // Record Entry
            LedgerEntry ledgerEntry = toLedgerEntry(
                    stash,
                    creditOrDebit,
                    ledgerEntryJson);

            stash.recordEntry(ledgerEntry);

            // Commit
            transaction.commit();


        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            // Send Email
            assert stashOwnerEmail != null
                    : "Stash should always have an owner";

            throw e;
        }
    }

    @NotNull
    private LedgerEntry toLedgerEntry(
            @NotNull final Stash stash,
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final JsonObject ledgerEntryJson)
            throws ParseException {

        BigDecimal amount = ledgerEntryJson.getJsonNumber("amount").bigDecimalValue();
        String description = ledgerEntryJson.getString("description");
        Instant txnDate = new DateStringConverter().toInstant(ledgerEntryJson.getString("txndate"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Ledger entry amount specified should always be > 0 ");
        }

        if (Instant.now().isBefore(txnDate)) {
            throw new IllegalArgumentException("You cannot post a ledger entry that is in the future");
        }

        return new LedgerEntry(
                stash,
                creditOrDebit,
                amount,
                description,
                new Date(txnDate.getEpochSecond()));
    }
}
