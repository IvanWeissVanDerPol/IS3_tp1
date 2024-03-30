package py.com.progweb.prueba.dao;

import java.util.List;

import py.com.progweb.prueba.model.Customer;

public class PointUseConceptDAO {

    void create(Customer customer);
    Customer update(Customer customer);
    void delete(Integer id);
    Customer findById(Integer id);
    List<Customer> findAll();
    
}
