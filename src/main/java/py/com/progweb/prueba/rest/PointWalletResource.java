package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointWalletService;
import py.com.progweb.prueba.model.PointWallet;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pointwallets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointWalletResource {

    @Inject
    private PointWalletService pointWalletService;

    @POST
    public Response createPointWallet(PointWallet pointWallet) {
        pointWalletService.create(pointWallet);
        return Response.status(Response.Status.CREATED).entity(pointWallet).build();
    }

    @GET
    @Path("/{id}")
    public Response getPointWallet(@PathParam("id") Integer id) {
        PointWallet pointWallet = pointWalletService.findById(id);
        if (pointWallet != null) {
            return Response.ok(pointWallet).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // get all point wallets for a customer by customer id

    @GET
    @Path("/customer/{customerId}")
    public Response getPointWalletsByCustomerId(@PathParam("customerId") Integer customerId) {
        List<PointWallet> pointWallets = pointWalletService.findByCustomerId(customerId);
        return Response.ok(pointWallets).build();
    }

    @GET
    public Response getAllPointWallets() {
        List<PointWallet> pointWallets = pointWalletService.findAll();
        return Response.ok(pointWallets).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePointWallet(@PathParam("id") Integer id, PointWallet pointWallet) {
        PointWallet existingWallet = pointWalletService.findById(id);
        if (existingWallet == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Assume the incoming pointWallet object contains the updates we want to apply
        existingWallet.setAssignedPoints(pointWallet.getAssignedPoints());
        existingWallet.setUsedPoints(pointWallet.getUsedPoints());
        existingWallet.setPointsBalance(pointWallet.getPointsBalance());
        existingWallet.setExpirationDate(pointWallet.getExpirationDate());
        existingWallet.setTransactionAmount(pointWallet.getTransactionAmount());

        PointWallet updatedWallet = pointWalletService.update(existingWallet);
        return Response.ok(updatedWallet).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePointWallet(@PathParam("id") Integer id) {
        pointWalletService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Additional RESTful endpoints as required by your application's logic
}
