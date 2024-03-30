//package py.com.progweb.prueba.factory;
//
//import py.com.progweb.prueba.models.Customer;
//
//public class CustomerFactory {
//
//    public static Customer createValidCustomer() {
//        Customer customer = new Customer();
//        customer.setFirstName("Ejemplo Nombre");
//        customer.setLastName("Ejemplo Apellido");
//        customer.setEmail("ejemplo@correo.com");
//        // Establece más campos si es necesario
//        return customer;
//    }
//
//    public static Customer createCustomerWithInvalidEmail() {
//        Customer customer = createValidCustomer();
//        customer.setEmail("correoNoValido");
//        return customer;
//    }
//
//    public static Customer createCustomerWithoutName() {
//        Customer customer = createValidCustomer();
//        customer.setFirstName(null);
//        return customer;
//    }
//
//    public static Customer createCustomerWithoutSurname() {
//        Customer customer = createValidCustomer();
//        customer.setLastName(null);
//        return customer;
//    }
//
//    public static Customer createCustomerWithoutEmail() {
//        Customer customer = createValidCustomer();
//        customer.setEmail(null);
//        return customer;
//    }
//
//
//}
