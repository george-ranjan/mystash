package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@UsecaseNames(Usecase.CREDIT)
@Path("/ledger/credit")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Credit extends TxnBase {

    @POST
    public JsonObject credit(
            @NotNull final JsonObject credit)
            throws ParseException {

        recordEntry(CreditOrDebit.CREDIT, credit);

        return Json.createObjectBuilder().build();
    }
}
