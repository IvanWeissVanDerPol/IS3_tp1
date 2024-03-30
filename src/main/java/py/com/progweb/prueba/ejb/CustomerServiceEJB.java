package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.CustomerDAO;
import py.com.progweb.prueba.model.Customer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CustomerServiceEJB {

    @Inject
    private CustomerDAO customerDAO;

    public void createCustomer(Customer customer) {
        customerDAO.create(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }

    public void deleteCustomer(Long id) {
        customerDAO.delete(id);
    }

    public Customer findCustomerById(Long id) {
        return customerDAO.findById(id);
    }

    public List<Customer> listCustomers() {
        return  customerDAO.findAll();
    }
}
