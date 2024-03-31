package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointAllocationRuleService;
import py.com.progweb.prueba.model.PointAllocationRule;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/point-allocation-rules")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointAllocationRuleResource {

    @Inject
    private PointAllocationRuleService pointAllocationRuleService;

    @POST
    public Response createPointAllocationRule(PointAllocationRule rule) {
        pointAllocationRuleService.create(rule);
        return Response.status(Response.Status.CREATED).entity(rule).build();
    }

    @GET
    @Path("/{id}")
    public Response getPointAllocationRuleById(@PathParam("id") Long id) {
        PointAllocationRule rule = pointAllocationRuleService.findById(id);
        if (rule != null) {
            return Response.ok(rule).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAllPointAllocationRules() {
        List<PointAllocationRule> rules = pointAllocationRuleService.findAll();
        return Response.ok(rules).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePointAllocationRule(@PathParam("id") Long id, PointAllocationRule rule) {
        PointAllocationRule existingRule = pointAllocationRuleService.findById(id);
        if (existingRule == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rule.setId(id);
        PointAllocationRule updatedRule = pointAllocationRuleService.update(rule);
        return Response.ok(updatedRule).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePointAllocationRule(@PathParam("id") Long id) {
        pointAllocationRuleService.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/calculate")
    public Response calculatePoints(@QueryParam("amount") Long amount) {
        if (amount == null || amount < 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid amount").build();
        }
        int points = pointAllocationRuleService.calculatePointsForAmount(amount);
        return Response.ok(points).build();
    }
}
