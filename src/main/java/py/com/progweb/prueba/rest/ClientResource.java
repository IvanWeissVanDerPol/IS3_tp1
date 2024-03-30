package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.SampleEJB;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/client")
public class ClientResource {

    @Inject
    SampleEJB sampleEJB;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "alecabral; ";
    }
}
