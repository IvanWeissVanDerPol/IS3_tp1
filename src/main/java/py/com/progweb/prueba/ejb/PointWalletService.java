package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.dao.PointAllocationRule.PointAllocationRuleDAO;
import py.com.progweb.prueba.dao.PointUseConcept.PointUseConceptDAO;
import py.com.progweb.prueba.dao.PointWallet.PointWalletDAO;
import py.com.progweb.prueba.model.PointAllocationRule;
import py.com.progweb.prueba.model.PointUseConcept;
import py.com.progweb.prueba.model.PointWallet;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.List;

@Stateless
public class PointWalletService {

    @Inject
    private PointWalletDAO pointWalletDAO;

    @Inject
    private PointAllocationRuleDAO pointAllocationRuleDAO;
    
    @Inject
    private PointUseConceptDAO pointUseConceptDAO;
    // Add your mail session configuration here
    @Resource(name = "java:jboss/mail/Default")
    private Session mailSession;

    public PointWallet create(PointWallet pointWallet) {
        pointWalletDAO.create(pointWallet);
        return pointWallet; 
    }

    public PointWallet update(PointWallet pointWallet) {
        return pointWalletDAO.update(pointWallet);
    }

    public void delete(Integer id) {
        pointWalletDAO.delete(id);
    }

    public PointWallet findById(Integer id) {
        return pointWalletDAO.findById(id);
    }

    public List<PointWallet> findAll() {
        return pointWalletDAO.findAll();
    }

    public PointWallet addPointsToCustomer(Integer customerId, Double transactionAmount) {
            // Find the allocation rule based on the transaction amount
            PointAllocationRule rule = pointAllocationRuleDAO.findRuleByTransactionAmount(transactionAmount);
            
            if (rule == null) {
                // Handle the case where no rule is found
                // This could involve throwing an exception or assigning a default number of points
                throw new IllegalStateException("No point allocation rule found for the given amount.");
            }
            
            Integer pointsToAssign = calculatePoints(transactionAmount, rule);
            
            // Create a new PointWallet entry
            PointWallet newPointWallet = new PointWallet();
            newPointWallet.setCustomerId(customerId);
            newPointWallet.setAssignmentDate(new Date()); // Current date for assignment
            newPointWallet.setExpirationDate(calculateExpirationDate()); // You will implement this method
            newPointWallet.setAssignedPoints(pointsToAssign);
            newPointWallet.setUsedPoints(0);
            newPointWallet.setPointsBalance(pointsToAssign);
            newPointWallet.setTransactionAmount(transactionAmount);
            
            // Persist the new PointWallet
            pointWalletDAO.create(newPointWallet);
            
            return newPointWallet;
        }

        
    private Integer calculatePoints(Double transactionAmount, PointAllocationRule rule) {
        // Calculate points based on the rule
        // Example: If the rule is 1 point per 50.000 and the transaction is 150.000, it should return 3
        return (int)(transactionAmount / rule.getPointsPerUnit());
    }
    
    private Date calculateExpirationDate() {
        // Calculate the expiration date for the points
        // You will need to determine how you calculate the expiration based on your business rules
        // For example, if points expire after one year, you could add one year to the current date
        Date now = new Date();
        // Set calendar to one year (or your business rule) from now
        // Calendar calendar = Calendar.getInstance();
        // Calendar.setTime(now);
        // calendar.add(Calendar.YEAR, 1);
        // return calendar.getTime();
        
        // For simplicity, just returning the current date as a placeholder
        return now;
    }

    public boolean usePoints(Integer customerId, Integer useConceptId) {
        // First, find the concept to see how many points are needed
        PointUseConcept concept = pointUseConceptDAO.findById(useConceptId);
        if (concept == null) {
            throw new IllegalArgumentException("Concept not found");
        }

        // Next, check if the customer has enough points
        List<PointWallet> wallets = pointWalletDAO.findAllByCustomerId(customerId);
        int pointsNeeded = concept.getRequiredPoints();
        int pointsAvailable = wallets.stream()
                                     .mapToInt(PointWallet::getPointsBalance)
                                     .sum();

        if (pointsAvailable < pointsNeeded) {
            return false; // Not enough points
        }

        // Use points from the oldest wallet first (FIFO)
        for (PointWallet wallet : wallets) {
            if (pointsNeeded <= 0) {
                break;
            }
            int pointsToUse = Math.min(wallet.getPointsBalance(), pointsNeeded);
            wallet.setUsedPoints(wallet.getUsedPoints() + pointsToUse);
            wallet.setPointsBalance(wallet.getPointsBalance() - pointsToUse);
            pointsNeeded -= pointsToUse;
            pointWalletDAO.update(wallet); // Persist changes in each wallet
        }

        // After using points, send an email as a receipt
        sendEmailReceipt(customerId, concept, pointsNeeded);

        return true;
    }

    private void sendEmailReceipt(Integer customerId, PointUseConcept concept, int pointsUsed) {
        // Implement your email sending logic here using JavaMail API
        // This will require proper SMTP configuration and may use async processing
        try {
            Message message = new MimeMessage(mailSession);
            // Set the recipient and the sender
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("customer_email@example.com"));
            message.setFrom(new InternetAddress("noreply@example.com"));
            message.setSubject("Points Redemption Receipt");

            String emailContent = "Dear Customer,\n\n"
                    + "You have used " + pointsUsed + " points for concept: " + concept.getDescription() + ".\n\n"
                    + "Best Regards,\n"
                    + "Your Loyalty Program Team";

            message.setText(emailContent);
            Transport.send(message);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public List<PointWallet> findByCustomerId(Integer customerId) {
        List<PointWallet> wallets = pointWalletDAO.findAllByCustomerId(customerId);
        // return wallets sorted by expiration date and only return the ones that have not expired AND have points available to use
        wallets.removeIf(wallet -> wallet.getExpirationDate().before(new Date()));

        wallets.removeIf(wallet -> wallet.getPointsBalance() <= 0);

        wallets.sort((w1, w2) -> w1.getExpirationDate().compareTo(w2.getExpirationDate()));
        return wallets;

    }

    public PointWallet findWalletClosestToExpiration(Integer customerId) {
        List<PointWallet> wallets = pointWalletDAO.findAllByCustomerId(customerId);
        // Sort the wallets by expiration date in ascending order
        wallets.sort((w1, w2) -> w1.getExpirationDate().compareTo(w2.getExpirationDate()));
        // Return the wallet with the earliest expiration date
        return wallets.get(0);
        
    }

    public void updatePointWallet(PointWallet wallet) {
        pointWalletDAO.update(wallet);

    }

    
}
