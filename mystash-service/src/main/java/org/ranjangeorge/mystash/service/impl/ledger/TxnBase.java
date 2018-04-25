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
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

abstract class TxnBase {

    private IEmailService emailService;

    TxnBase() {
        emailService = IEmailService.getEmailService();
    }

    void recordEntry(
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final JsonObject ledgerEntryJson)
            throws ParseException {

        if (!ledgerEntryJson.containsKey("stashId")) {
            throw new IllegalArgumentException("No stash id specified");
        }

        if (!ledgerEntryJson.containsKey("amount")) {
            throw new IllegalArgumentException("No amount specified");
        }

        if (!ledgerEntryJson.containsKey("txndate")) {
            throw new IllegalArgumentException("No transaction date specified");
        }

        if (!ledgerEntryJson.containsKey("description")) {
            throw new IllegalArgumentException("No description specified");
        }

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Fetch Stash
            Stash stash = session.get(Stash.class, ledgerEntryJson.getString("stashId"));
            if (stash == null) {
                throw new IllegalArgumentException("Invalid id specified for stash");
            }

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

            throw e;
        }
    }

    @NotNull
    private LedgerEntry toLedgerEntry(
            @NotNull final Stash stash,
            @NotNull final CreditOrDebit creditOrDebit,
            @NotNull final JsonObject ledgerEntryJson)
            throws ParseException {

        long amount = ledgerEntryJson.getJsonNumber("amount").longValue();
        String description = ledgerEntryJson.getString("description");
        Instant txnDate = new DateStringConverter().toInstant(ledgerEntryJson.getString("txndate"));

        if (amount <= 0) {
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
