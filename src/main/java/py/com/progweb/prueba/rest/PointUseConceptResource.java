package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointUseConceptService;
import py.com.progweb.prueba.model.PointUseConcept;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/point-use-concepts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointUseConceptResource {

    @Inject
    private PointUseConceptService pointUseConceptService;

    @POST
    public Response createPointUseConcept(PointUseConcept concept) {
        pointUseConceptService.create(concept);
        return Response.status(Response.Status.CREATED).entity(concept).build();
    }

    @GET
    @Path("/{id}")
    public Response getPointUseConceptById(@PathParam("id") Long id) {
        PointUseConcept concept = pointUseConceptService.findById(id);
        if (concept != null) {
            return Response.ok(concept).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAllPointUseConcepts() {
        List<PointUseConcept> concepts = pointUseConceptService.findAll();
        return Response.ok(concepts).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePointUseConcept(@PathParam("id") Long id, PointUseConcept concept) {
        PointUseConcept updatedConcept = pointUseConceptService.update(concept);
        if (updatedConcept != null) {
            return Response.ok(updatedConcept).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePointUseConcept(@PathParam("id") Long id) {
        pointUseConceptService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
