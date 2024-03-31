package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.Customer.CustomerDAO;
import py.com.progweb.prueba.model.Customer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Date;

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

    public void deleteCustomer(Integer id) {
        customerDAO.delete(id);
    }

    public Customer findCustomerById(Integer id) {
        Customer customer = customerDAO.findById(id);
        Date birthdate = customer.getBirthDate();
        // add 1 day to the birthdate by adding 1 day in milliseconds
        birthdate.setTime(birthdate.getTime() + 86400000);
        customer.setBirthDate(birthdate);
        return customer;
    }

    public Customer findByEmail(String email) {
        return customerDAO.findByEmail(email);
    }

    public List<Customer> listCustomers() {
        return  customerDAO.findAll();
    }
}
