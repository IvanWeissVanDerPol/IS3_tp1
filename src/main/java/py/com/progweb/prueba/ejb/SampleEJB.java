package py.com.progweb.prueba.ejb;

import javax.ejb.Stateless;

@Stateless
public class SampleEJB {

    public String getHelloMessage() {
        return "Hello from EJB!";
    }
}
