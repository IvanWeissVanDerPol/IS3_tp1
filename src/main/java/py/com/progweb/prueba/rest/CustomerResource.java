package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.CustomerServiceEJB;
import py.com.progweb.prueba.model.Customer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@JsonIgnoreProperties
public class CustomerResource {

    @Inject
    private CustomerServiceEJB customerService;


    @POST
    public Response createCustomer(Customer customer) {
        customerService.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerService.listCustomers();
        GenericEntity<List<Customer>> list = new GenericEntity<List<Customer>>(customers) {};
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return Response.ok(updatedCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
