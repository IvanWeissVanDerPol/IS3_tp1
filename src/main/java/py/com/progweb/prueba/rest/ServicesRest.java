package py.com.progweb.prueba.rest;

import py.com.progweb.prueba.ejb.CustomerService;
import py.com.progweb.prueba.ejb.PointAllocationRuleService;
import py.com.progweb.prueba.ejb.PointExpirationService;
import py.com.progweb.prueba.ejb.PointUseConceptService;
import py.com.progweb.prueba.ejb.PointWalletService;
import py.com.progweb.prueba.ejb.PointsService;
import py.com.progweb.prueba.model.Customer;
import py.com.progweb.prueba.model.PointAllocationRule;
import py.com.progweb.prueba.model.PointExpiration;
import py.com.progweb.prueba.model.PointUseConcept;
import py.com.progweb.prueba.model.PointWallet;
// import py.com.progweb.prueba.utils.EmailUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Path("/Services")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServicesRest {

    @Inject
    private PointWalletService pointWalletService;

    @Inject
    private PointUseConceptService pointUseConceptService;

    @Inject
    private PointExpirationService pointExpirationService;

    
    @Inject
    private PointsService pointsService;

    
    @Inject
    private CustomerService customerService;

    @Inject
    private PointAllocationRuleService pointAllocationRuleService;


    @GET
    @Path("/hello")
    public Response hello() {
        return Response.ok("Hello from ServicesRest").build();
    }


    // MAKE A METHOD THAT RETURNS THE POINT EXPIRATION RULE THAT IS VALID FOR THE CURRENT DATE

    public PointExpiration findExpirationRule(Date currentDate) {

        List<PointExpiration> rules = pointExpirationService.findAll();
        // find the rule that is valid for the current date
        for (PointExpiration rule : rules) {
            if (currentDate.after(rule.getStartDate()) && currentDate.before(rule.getEndDate())) {
                return rule;
            }
        }
        // if no rule is found, return null
        return null;

    }


    public Date calculateExpirationDate(Date assignmentDate) {
        // get the valid point expiration rule for the current date
        PointExpiration pointExpiration = findExpirationRule(assignmentDate);
        if (pointExpiration == null) {
            // if no rule is found, return null
            return null;
        }
        // calculate the expiration date based on the rule's duration
        return addDays(assignmentDate, pointExpiration.getDurationDays());


    }

    private static Date addDays(Date assignmentDate, Integer durationDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(assignmentDate);
        calendar.add(Calendar.DAY_OF_MONTH, durationDays);
        return calendar.getTime();
    }

    @POST
    @Path("/add_points/{customer_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPoints(@PathParam("customer_id") Integer  customer_id,@QueryParam("assignedPoints") Integer assignedPoints) {
        PointWallet pointWallet = new PointWallet(customer_id,assignedPoints,calculateExpirationDate(new Date()));
        pointWalletService.create(pointWallet);
        return Response.ok(pointWallet).build();
    }


    @Path("/use_points")
    @POST
    public Response usePoints(
            @QueryParam("customer_id") Integer customerId,
            @QueryParam("concept_id") Integer conceptId) {


        //  get the customers email
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid customer ID.").build();
        }

        // Find the concept to see how many points are needed
        PointUseConcept concept = pointUseConceptService.findById(conceptId);
        if (concept == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid concept ID.").build();
        }
        // get all the wallets sorted by expiration date
        List<PointWallet> wallets = pointWalletService.findByCustomerId(customerId);
        
        // find the total points available
        Integer totalPoints = 0;
        for (PointWallet wallet : wallets) {
            totalPoints += wallet.getPointsBalance();
        }


        // check if the customer has enough points
        if (totalPoints < concept.getRequiredPoints()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Not enough points.").build();
        }

        // use pointsService to use points
        pointsService.redeemPoints(customerId, concept.getRequiredPoints(), concept.getId());
        
    
        

        

        // persist changes in each wallet
        for (PointWallet wallet : wallets) {
            pointWalletService.update(wallet);
        }


        // // use emailService to send email

        // String cuerpo = EmailUtils.getCuerpoEmail(customer,
        // EmailUtils.enviarCorreo(System.getenv("user"), cliente.getEmail(), "Comprobante por uso de puntos", cuerpo);


        // // Prepare email content
        // String emailContent = prepareEmailContent(customerId, conceptId, concept.getRequiredPoints());
        // // Send email
        // boolean emailSent = emailService.sendMail(customer.getEmail(), "Points used", emailContent);

        // if (!emailSent) {
        //     return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to send confirmation email.").build();
        // }

        return Response.ok().entity("Points used successfully and email sent.").build();
    }

    // private String prepareEmailContent(Integer customerId, Integer conceptId, Integer pointsToUse) {
    //     // get customer by ID
    //     Customer customer = customerService.findCustomerById(customerId);

    //     // get concept by ID
    //     PointUseConcept concept = pointUseConceptService.findById(conceptId);


    //     String CustomerName = customer.getFirstName() + " " + customer.getLastName();
    //     return "Dear " + CustomerName  + ",\n\n" +
    //             "This is a confirmation that you have used " + pointsToUse + " points for the concept '" + concept.getDescription() + "'.\n" +
    //             "If you have any questions, please do not hesitate to contact us.\n\n" +
    //             "Best regards,\n" +
    //             "Your Loyalty Program Team";
    // }

    // consultar cuantos puntos equivale a un monto X: es un servicio informativo
    @GET
    @Path("/points_for_amount")
    public Response pointsForAmount(@QueryParam("amount") Integer amount) {
        
        // get the rules from pointAlocationRuleService
        Integer conversion = 0;
        List<PointAllocationRule> rules = pointAllocationRuleService.findAll();
        // iterate over the rules to find the one that applies to the amount given and store it as a variable
        for (PointAllocationRule rule : rules) {
            if (amount >= rule.getLowerLimit() && amount <= rule.getUpperLimit()) {
                 conversion =  rule.getPointsPerUnit();
            }
        }

        // calculate the points based on the rule and the amount given
        Float points = (float) (amount / conversion);
    
        return Response.ok(points).build();
        
        
        
    }



    

}
