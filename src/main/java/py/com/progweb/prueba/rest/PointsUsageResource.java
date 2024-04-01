package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointsService;
import py.com.progweb.prueba.model.PointsHeader;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/points-usage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointsUsageResource {

    @Inject
    private PointsService pointsService;

    @POST
    @Path("/redeem")
    public Response redeemPoints(@QueryParam("customerId") Integer customerId,
                                 @QueryParam("points") Integer points,
                                 @QueryParam("concept") Integer concept_Id) {
        try {
            PointsHeader pointsHeader = pointsService.redeemPoints(customerId, points, concept_Id);
            return Response.status(Response.Status.CREATED).entity(pointsHeader).build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // @GET
    // @Path("/{customerId}")
    // public Response getPointsUsageByCustomer(@PathParam("customerId") Integer customerId) {
    //     try {
    //         List<PointsHeader> pointsUsageList = pointsService.getPointsUsageByCustomer(customerId);
    //         return Response.ok(pointsUsageList).build();
    //     } catch (Exception e) {
    //         // You can refine the exception handling by catching more specific exceptions
    //         // that your service layer might throw in different scenarios
    //         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
    //     }
    // }
}
