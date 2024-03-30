//// FILEPATH = src/main/java/py/com/progweb/prueba/dao/CustomerDAOLocal.java
//package py.com.progweb.prueba.ejb;
//
//import py.com.progweb.prueba.dao.CustomerDAOLocal;
//import py.com.progweb.prueba.models.Customer;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.validation.Valid;
//import javax.ws.rs.core.Response;
//
//import java.util.List;
//
//
//
//@Stateless
//public class CustomerManagementBean{
//
//    @Inject
//    private CustomerDAOLocal customerDAO;
//
//    public Response getCustomer(Long id) {
//        Customer customer = customerDAO.findById(id);
//        if (customer != null) {
//            return Response.ok(customer).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found with ID: " + id).build();
//        }
//    }
//
//
//    public Response createCustomer(@Valid Customer customer) {
//        if (customerDAO.emailExists(customer.getEmail())) {
//            return Response.status(Response.Status.CONFLICT).entity("Email already exists.").build();
//        }
//        if (customerDAO.documentNumberExists(customer.getDocumentNumber())) {
//            return Response.status(Response.Status.CONFLICT).entity("Document number already exists.").build();
//        }
//        try {
//            customerDAO.create(customer);
//            return Response.status(Response.Status.CREATED).entity(customer).build();
//        } catch (Exception e) {
//            // Log the exception
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }
//
//
//
//    public Response  updateCustomer(Long id,@Valid  Customer customer) {
//                // Asegúrate de que el cliente existe antes de intentar actualizarlo
//        Customer existingCustomer = customerDAO.findById(id);
//        if (existingCustomer != null) {
//            // Actualizar los campos necesarios o toda la entidad
//            existingCustomer.setFirstName(customer.getFirstName());
//            existingCustomer.setLastName(customer.getLastName());
//            existingCustomer.setDocumentNumber(customer.getDocumentNumber());
//            existingCustomer.setDocumentType(customer.getDocumentType());
//            existingCustomer.setNationality(customer.getNationality());
//            existingCustomer.setEmail(customer.getEmail());
//            existingCustomer.setPhone(customer.getPhone());
//            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
//            Customer updatedCustomer = customerDAO.update(existingCustomer);
//            return Response.ok(updatedCustomer).build();
//
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found with ID: " + id).build();
//        }
//    }
//
//
//    public Response deleteCustomer(Long id) {
//        Customer existingCustomer = customerDAO.findById(id);
//        if (existingCustomer != null) {
//            customerDAO.delete(id);
//            return Response.ok().entity("Customer deleted successfully").build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found with ID: " + id).build();
//        }
//    }
//
//
//    public Response listAllCustomers() {
//        List<Customer> customers = customerDAO.findAll();
//        if (customers != null && !customers.isEmpty()) {
//            return Response.ok(customers).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("No customers found").build();
//        }
//    }
//
//}
