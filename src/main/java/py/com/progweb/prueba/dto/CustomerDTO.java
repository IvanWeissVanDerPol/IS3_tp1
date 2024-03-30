//// FILEPATH = src/main/java/py/com/progweb/prueba/models/Customer.java
//package py.com.progweb.prueba.dto;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import py.com.progweb.prueba.serializer.CustomerSerializer;
//
//import java.util.Date;
//
//
//import javax.validation.constraints.Size;
//import javax.validation.constraints.Past;
//
//
//@JsonSerialize(using = CustomerSerializer.class)
//public class CustomerDTO {
//
//    private Long id;
//
//    @NotBlank(message = "El nombre no puede estar vacío")
//    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
//    private String firstName;
//
//    @NotBlank(message = "El apellido no puede estar vacío")
//    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
//    private String lastName;
//
//    @NotBlank(message = "El número de documento no puede estar vacío")
//    private String documentNumber;
//
//    @NotBlank(message = "El tipo de documento no puede estar vacío")
//    private String documentType;
//
//    @NotBlank(message = "La nacionalidad no puede estar vacía")
//    private String nationality;
//
//    @Email(message = "El correo electrónico debe ser válido")
//    @NotBlank(message = "El correo electrónico no puede estar vacío")
//    private String email;
//
//    @NotBlank(message = "El teléfono no puede estar vacío")
//    private String phone;
//
//    @Past(message = "La fecha de nacimiento debe ser en el pasado")
//    private Date dateOfBirth;
//
//    // Constructor por defecto
//    public CustomerDTO() {
//    }
//
//    // Constructor con todos los campos
//    public CustomerDTO(Long id, String firstName, String lastName, String documentNumber, String documentType, String nationality, String email, String phone, Date dateOfBirth) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.documentNumber = documentNumber;
//        this.documentType = documentType;
//        this.nationality = nationality;
//        this.email = email;
//        this.phone = phone;
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    // Getters y setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getDocumentNumber() {
//        return documentNumber;
//    }
//
//    public void setDocumentNumber(String documentNumber) {
//        this.documentNumber = documentNumber;
//    }
//
//    public String getDocumentType() {
//        return documentType;
//    }
//
//    public void setDocumentType(String documentType) {
//        this.documentType = documentType;
//    }
//
//    public String getNationality() {
//        return nationality;
//    }
//
//    public void setNationality(String nationality) {
//        this.nationality = nationality;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//}
