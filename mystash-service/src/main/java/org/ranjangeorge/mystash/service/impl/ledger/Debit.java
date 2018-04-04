package org.ranjangeorge.mystash.service.impl.ledger;

import org.jetbrains.annotations.NotNull;
import org.ranjangeorge.mystash.service.api.data.CreditOrDebit;
import org.ranjangeorge.mystash.service.api.support.Usecase;
import org.ranjangeorge.mystash.service.api.support.UsecaseNames;

import javax.json.JsonObject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.text.ParseException;

@UsecaseNames(Usecase.DEBIT)
@Path("/ledger/debit")
public class Debit extends TxnBase {

    @POST
    @PathParam("/{stash-id}")
    public void debit(
            @PathParam("{stash-id}") @NotNull final String stashId,
            @NotNull final JsonObject debit)
            throws ParseException {

        recordEntry(stashId, CreditOrDebit.DEBIT, debit);
    }
}
