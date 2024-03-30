//package py.com.progweb.prueba.ejb;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import py.com.progweb.prueba.BaseTest;
//import py.com.progweb.prueba.dao.CustomerDAOLocal;
//import py.com.progweb.prueba.factory.CustomerFactory;
//import py.com.progweb.prueba.models.Customer;
//
//import javax.persistence.EntityNotFoundException;
//import javax.validation.ValidationException;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(MockitoExtension.class)
//public class CustomerManagementBeanTest extends BaseTest {
//
//    @Mock
//    private CustomerDAOLocal customerDAOLocal;
//
//    @InjectMocks
//    private CustomerManagementBean customerManagementBean;
//
//    private Customer validCustomer;
//
//    // @BeforeEach
//    // public void setUp() {
//    //     super.setUp(); // Llama al setUp de BaseTest si es necesario
//    //     validCustomer = CustomerFactory.createValidCustomer();
//    // }
//
//    // @Test
//    // void createValidCustomer() {
//    //     prepareAndExecuteCreateCustomer(validCustomer, true);
//    // }
////
////    @Test
////    void createCustomerWithoutName() {
////        Customer customer = CustomerFactory.createCustomerWithoutName();
////        prepareAndExecuteCreateCustomer(customer, false);
////    }
////
////    @Test
////    void createCustomerWithoutSurname() {
////        Customer customer = CustomerFactory.createCustomerWithoutSurname();
////        prepareAndExecuteCreateCustomer(customer, false);
////    }
////
////    @Test
////    void updateExistingCustomer() {
////        Customer updatedCustomer = CustomerFactory.createValidCustomer();
////        updatedCustomer.setFirstName("Jane");
////        prepareAndExecuteUpdateCustomer(1L, updatedCustomer, true);
////    }
////
////    @Test
////    void updateNonExistingCustomer() {
////        Customer nonExistingCustomer = CustomerFactory.createValidCustomer();
////        prepareAndExecuteUpdateCustomer(999L, nonExistingCustomer, false);
////    }
////
////    @Test
////    void deleteExistingCustomer() {
////        Customer customer = CustomerFactory.createValidCustomer();
////
////    }
////
////    @Test
////    void deleteNonExistingCustomer() {
////        prepareAndExecuteDeleteCustomer(999L, false);
////    }
////
////    @Test
////    void findCustomerByIdExists() {
////        prepareAndExecuteFindCustomerById(1L, true);
////    }
////
////    @Test
////    void findCustomerByIdDoesNotExist() {
////        prepareAndExecuteFindCustomerById(999L, false);
////    }
////
////    @Test
////    void listAllCustomersWhenNotEmpty() {
////        prepareAndExecuteListAllCustomers(true);
////    }
////
////    @Test
////    void listAllCustomersWhenEmpty() {
////        prepareAndExecuteListAllCustomers(false);
////    }
//
//    private void prepareAndExecuteCreateCustomer(Customer customer, boolean isValid) {
//        if (isValid) {
//            customerManagementBean.addCustomer(customer);
//            verify(customerDAOLocal).create(customer);
//        } else {
//            assertThrows(ValidationException.class, () -> customerManagementBean.addCustomer(customer));
//        }
//    }
//
//    // Método auxiliar para actualizar un cliente
//    private void prepareAndExecuteUpdateCustomer(Long customerId, Customer updatedCustomer, boolean shouldSucceed) {
//        if (shouldSucceed) {
//            when(customerDAOLocal.findById(customerId)).thenReturn(updatedCustomer);
//            customerManagementBean.updateCustomer(customerId, updatedCustomer);
//            verify(customerDAOLocal).update(updatedCustomer);
//        } else {
//            when(customerDAOLocal.findById(customerId)).thenReturn(null);
//            assertThrows(EntityNotFoundException.class,
//                    () -> customerManagementBean.updateCustomer(customerId, updatedCustomer));
//        }
//    }
//
//    // Método auxiliar para eliminar un cliente
//    private void prepareAndExecuteDeleteCustomer(Long customerId, boolean shouldSucceed) {
//        if (shouldSucceed) {
//            doNothing().when(customerDAOLocal).delete(customerId);
//            assertTrue(customerManagementBean.deleteCustomer(customerId));
//            verify(customerDAOLocal).delete(customerId);
//        } else {
//            doThrow(new EntityNotFoundException()).when(customerDAOLocal).delete(customerId);
//            assertFalse(customerManagementBean.deleteCustomer(customerId));
//        }
//    }
//
//    // Método auxiliar para consultar un cliente por ID
//    private void prepareAndExecuteFindCustomerById(Long customerId, boolean exists) {
//        if (exists) {
//            when(customerDAOLocal.findById(customerId)).thenReturn(validCustomer);
//            Customer result = customerManagementBean.getCustomer(customerId);
//            verify(customerDAOLocal).findById(customerId);
//            assertThat(result).isEqualTo(validCustomer);
//        } else {
//            when(customerDAOLocal.findById(customerId)).thenReturn(null);
//            assertThrows(EntityNotFoundException.class, () -> customerManagementBean.getCustomer(customerId));
//        }
//    }
//
//    // Método auxiliar para listar todos los clientes
//    private void prepareAndExecuteListAllCustomers(boolean hasCustomers) {
//        if (hasCustomers) {
//            List<Customer> customers = Arrays.asList(new Customer(), new Customer());
//            when(customerDAOLocal.findAll()).thenReturn(customers);
//            List<Customer> result = customerManagementBean.listAllCustomers();
//            assertThat(result).hasSize(customers.size());
//        } else {
//            when(customerDAOLocal.findAll()).thenReturn(Collections.emptyList());
//            List<Customer> result = customerManagementBean.listAllCustomers();
//            assertThat(result).isEmpty();
//        }
//    }
//
//
//}
