package testdata;

import py.com.progweb.prueba.model.Customer;
import java.util.Date;

public class CustomerTestDataFactory {

    
    public static Customer createValidCustomer() {
        Customer customer = new Customer();
        // make a random string to add as a suffix to all fields
        String randomSuffix = Long.toHexString(Double.doubleToLongBits(Math.random()));
        customer.setFirstName("John" + randomSuffix);
        customer.setLastName("Doe" + randomSuffix);
        customer.setDocumentNumber("12345678" + randomSuffix);
        customer.setDocumentType("CI");
        customer.setNationality("Paraguayan");
        customer.setEmail("john.doe" + randomSuffix + "@example.com");
        customer.setPhone("12345678" + randomSuffix);
        customer.setBirthDate(new Date());
        return customer;
    }

    public static Customer createInvalidCustomer() {
        Customer customer = new Customer();
        // Populate fields in a way that is known to be invalid for your business logic
        return customer;
    }
}
