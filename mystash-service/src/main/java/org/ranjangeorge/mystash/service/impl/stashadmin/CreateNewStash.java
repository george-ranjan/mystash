package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.LedgerEntry;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@UsecaseNames(Usecase.CREATE_NEW_STASH)
@Path("/stash")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateNewStash {

    @POST
    public JsonObject createNewStash(JsonObject newStashInfo) {

        if (!newStashInfo.containsKey("name")) {
            throw new IllegalArgumentException("No name specified");
        }

        if (!newStashInfo.containsKey("email")) {
            throw new IllegalArgumentException("No email specified");
        }

        if (!newStashInfo.containsKey("initialCredit")) {
            throw new IllegalArgumentException("No initial credit specified");
        }

        if (newStashInfo.getJsonNumber("initialCredit").longValue() < 0) {
            throw new IllegalArgumentException("Initial credit amount specified should always be >= 0 ");
        }

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            // New Stash
            Stash newStash = new Stash(newStashInfo.getString("name"), newStashInfo.getString("email"));
            String stashId = (String) session.save(newStash);

            // Initial Credit
            LedgerEntry credit = new LedgerEntry(
                    newStash,
                    CreditOrDebit.CREDIT,
                    newStashInfo.getJsonNumber("initialCredit").longValue(),
                    "Starting Credit",
                    new Date());
            newStash.recordEntry(credit);

            // Commit
            transaction.commit();

            return Json.createObjectBuilder()
                    .add("id", stashId)
                    .build();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }

    }
}
