package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@UsecaseNames(Usecase.DEBIT)
@Path("/ledger/debit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Debit extends TxnBase {

    @POST
    public JsonObject debit(
            @NotNull final JsonObject debit)
            throws ParseException {

        recordEntry(CreditOrDebit.DEBIT, debit);

        return Json.createObjectBuilder().build();
    }
}
