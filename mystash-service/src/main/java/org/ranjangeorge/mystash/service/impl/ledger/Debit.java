package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@UsecaseNames(Usecase.DEBIT)
@Path("/ledger/debit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Debit extends TxnBase {

    @POST
    @Path("/{stash-id}")
    public void debit(
            @PathParam("{stash-id}") @NotNull final String stashId,
            @NotNull final JsonObject debit)
            throws ParseException {

        recordEntry(stashId, CreditOrDebit.DEBIT, debit);
    }
}
