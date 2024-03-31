package py.com.progweb.prueba.dao.Customer;

import py.com.progweb.prueba.model.Customer;
import java.util.List;

public interface CustomerDAO {
    void create(Customer customer);
    Customer update(Customer customer);
    void delete(Integer id);
    Customer findById(Integer id);
    Customer findByEmail(String email);
    List<Customer> findAll();
}
