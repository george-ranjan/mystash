package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@UsecaseNames(Usecase.CREATE_NEW_STASH)
@Path("/stash")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CreateNewStash {

    @POST
    public JsonObject createNewStash(
            @NotNull @FormParam("name") final String stashName,
            @NotNull @FormParam("email") final String ownerEmail,
            final @FormParam("initialCredit") long initialCredit) {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // New Stash
            Stash newStash = new Stash(stashName, ownerEmail);
            String stashId = (String) session.save(newStash);

            // Initial Credit
            LedgerEntry credit = new LedgerEntry(
                    newStash,
                    CreditOrDebit.CREDIT,
                    initialCredit,
                    "Starting Credit",
                    new Date());
            newStash.recordEntry(credit);

            // Commit
            transaction.commit();

            return Json.createObjectBuilder()
                    .add("stashId", stashId)
                    .build();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }

    }
}
