package py.com.progweb.prueba.rest;

//import py.com.progweb.prueba.ejb.CustomerManagementBean;
// import py.com.progweb.prueba.models.Customer;

// import javax.ejb.EJB;
// import javax.validation.ConstraintViolationException;
// import javax.validation.Valid;
// import java.util.HashMap;
// import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerService {

//    @EJB
//    private CustomerManagementBean customerManagementBean;
//
    // Test endpoint
    @GET
    @Path("/test")
    public Response testService() {

        return Response.ok("Service is running").build();

    }
//
//    @GET
//    @Path("/{id}")
//    public Response getCustomer(@PathParam("id") Long id) {
//        return customerManagementBean.getCustomer(id);
//    }
//
//    @POST
//    public Response createCustomer(@Valid Customer customer) {
//        return customerManagementBean.createCustomer(customer);
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response updateCustomer(@PathParam("id") Long id, @Valid Customer customer) {
//        return customerManagementBean.updateCustomer(id, customer);
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteCustomer(@PathParam("id") Long id) {
//        return customerManagementBean.deleteCustomer(id);
//    }
//
//    private Response buildValidationErrorResponse(ConstraintViolationException e) {
//        Map<String, String> responseObj = new HashMap<>();
//        e.getConstraintViolations().forEach(violation -> responseObj.put(violation.getPropertyPath().toString(), violation.getMessage()));
//        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
//    }
}
