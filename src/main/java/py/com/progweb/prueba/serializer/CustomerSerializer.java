//package py.com.progweb.prueba.serializer;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import py.com.progweb.prueba.dto.CustomerDTO;
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//
//public class CustomerSerializer extends StdSerializer<CustomerDTO> {
//
//    public CustomerSerializer() {
//        this(null);
//    }
//
//    public CustomerSerializer(Class<CustomerDTO> t) {
//        super(t);
//    }
//
//    @Override
//    public void serialize(CustomerDTO value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeStartObject();
//        gen.writeNumberField("id", value.getId());
//        gen.writeStringField("firstName", value.getFirstName());
//        gen.writeStringField("lastName", value.getLastName());
//        // Asume que quieres formatear la fecha de nacimiento a un formato específico
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDateOfBirth = sdf.format(value.getDateOfBirth());
//        gen.writeStringField("dateOfBirth", formattedDateOfBirth);
//        gen.writeStringField("documentNumber", value.getDocumentNumber());
//        gen.writeStringField("documentType", value.getDocumentType());
//        gen.writeStringField("nationality", value.getNationality());
//        gen.writeStringField("email", value.getEmail());
//        gen.writeStringField("phone", value.getPhone());
//        gen.writeEndObject();
//    }
//}
