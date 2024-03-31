package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointWalletService;
import py.com.progweb.prueba.model.PointAllocationRule;
import py.com.progweb.prueba.model.PointWallet;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Services")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServicesRest {

    @Inject
    private PointWalletService pointWalletService;

    @Inject
    private PointAllocationRule pointAllocationRule;

    @GET
    @Path("hello")
    public Response hello() {
        return Response.ok("Hello from ServicesRest").build();
    }

    @GET
    @Path("add_points")
    public Response addPoints() {

    }

}
