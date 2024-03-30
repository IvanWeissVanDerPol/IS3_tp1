//// FILEPATH = src/main/java/py/com/progweb/prueba/dao/CustomerDAO.java
//package py.com.progweb.prueba.dao;
//
//import py.com.progweb.prueba.models.Customer;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import java.util.List;
//
//
//
//public class CustomerDAO implements CustomerDAOLocal {
//
//    @PersistenceContext(unitName = "CustomerPersistenceUnit") // Cambiar a tu nombre de unidad de persistencia en path = src/main/resources/META-INF/persistence.xml
//    private EntityManager em;
//
//    @Override
//    public Customer findById(Long id) {
//        return em.find(Customer.class, id);
//    }
//
//    @Override
//    public void create(Customer customer) {
//        em.persist(customer);
//    }
//
//    @Override
//    public Customer update(Customer customer) {
//        return em.merge(customer);
//    }
//
//    @Override
//    public void delete(Long id) {
//        Customer customer = findById(id);
//        if (customer != null) {
//            em.remove(customer);
//        }
//    }
//
//    @Override
//    public List<Customer> findAll() {
//        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
//        return query.getResultList();
//    }
//
//    public boolean emailExists(String email) {
//        Long count = em.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.email = :email", Long.class)
//                       .setParameter("email", email)
//                       .getSingleResult();
//        return count > 0;
//    }
//
//    public boolean documentNumberExists(String documentNumber) {
//        Long count = em.createQuery("SELECT COUNT(c) FROM Customer c WHERE c.documentNumber = :documentNumber", Long.class)
//                       .setParameter("documentNumber", documentNumber)
//                       .getSingleResult();
//        return count > 0;
//    }
//
//}
