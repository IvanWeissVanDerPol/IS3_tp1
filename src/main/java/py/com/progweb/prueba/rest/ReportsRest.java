package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.PointsService;
// import py.com.progweb.prueba.utils.EmailUtils;
import py.com.progweb.prueba.model.PointsDetail;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
@Path("/report")
public class ReportsRest {
    
    @Inject
    private PointsService pointsService;
    
    @GET
    @Path("/usage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPointUsageReport(@QueryParam("customerId") Integer customerId,
                                        @QueryParam("conceptId") Integer conceptId,
                                        @QueryParam("Date") String startDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);

        // find all points that match the criteria
        List<PointsDetail> usages = pointsService.findPointsUsage(customerId, conceptId, startDate);

        return Response.ok(usages).build();
    }
}
