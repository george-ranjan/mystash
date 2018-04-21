package org.ranjangeorge.mystash.service.impl.stashadmin;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;
import org.ranjangeorge.mystash.service.impl.Stash;
import org.ranjangeorge.mystash.service.impl.support.db.SessionFactoryHolder;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@UsecaseNames(Usecase.LIST_ALL_STASHES)
@Path("/stash/list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListAllStashes {

    @GET
    public JsonArray listAllStashes() {

        Session session = SessionFactoryHolder.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

            @SuppressWarnings("unchecked")
            List<Stash> stashList = (List<Stash>) session.createCriteria(Stash.class).list();

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            stashList.stream()
                    .map(Stash::getName)
                    .forEach(arrayBuilder::add);

            // Commit
            transaction.commit();

            return arrayBuilder.build();

        } catch (RuntimeException e) {

            // Oops! Some problem, rollback
            transaction.rollback();

            throw e;
        }
    }
}
