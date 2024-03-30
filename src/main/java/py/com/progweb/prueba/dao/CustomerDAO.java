package py.com.progweb.prueba.dao;

import py.com.progweb.prueba.model.Customer;
import java.util.List;

public interface CustomerDAO {
    void create(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
    Customer findById(Long id);
    List<Customer> findAll();
}
