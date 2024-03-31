package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointExpirationService;
import py.com.progweb.prueba.model.PointExpiration;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/point-expirations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointExpirationResource {

    @Inject
    private PointExpirationService pointExpirationService;

    @POST
    public Response createPointExpiration(PointExpiration pointExpiration) {
        pointExpirationService.create(pointExpiration);
        return Response.status(Response.Status.CREATED).entity(pointExpiration).build();
    }

    @GET
    @Path("/{id}")
    public Response getPointExpirationById(@PathParam("id") Long id) {
        PointExpiration pointExpiration = pointExpirationService.findById(id);
        if (pointExpiration != null) {
            return Response.ok(pointExpiration).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAllPointExpirations() {
        return Response.ok(pointExpirationService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePointExpiration(@PathParam("id") Long id, PointExpiration pointExpiration) {
        PointExpiration updatedPointExpiration = pointExpirationService.update(pointExpiration);
        if (updatedPointExpiration != null) {
            return Response.ok(updatedPointExpiration).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePointExpiration(@PathParam("id") Long id) {
        pointExpirationService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
