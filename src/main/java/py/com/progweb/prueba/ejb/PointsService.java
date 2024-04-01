package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.Points.PointsDetailDAO;
import py.com.progweb.prueba.dao.Points.PointsHeaderDAO;
import py.com.progweb.prueba.model.Customer;
import py.com.progweb.prueba.model.PointUseConcept;
import py.com.progweb.prueba.model.PointWallet;
import py.com.progweb.prueba.model.PointsDetail;
import py.com.progweb.prueba.model.PointsHeader;

import javax.ejb.Stateless;
import javax.inject.Inject;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Stateless
public class PointsService {

    @Inject
    private PointsHeaderDAO pointsHeaderDAO;

    @Inject
    private PointsDetailDAO pointsDetailDAO;
    
    
    @Inject
    private PointWalletService pointWalletService;

    @Inject
    private PointUseConceptService pointUseConceptService;

    @Inject
    private CustomerService customerService;

    public PointsHeader redeemPoints(Integer customerId, Integer pointsToRedeem, Integer pointUseConcept) {
        
        // get the total points available for redemption for the customer
        // get a list of point wallets for the customer
        List<PointWallet> pointWallets = pointWalletService.findByCustomerId(customerId);
        // get the total points available for redemption
        int totalPoints = 0;
        for (PointWallet wallet : pointWallets) {
            totalPoints += wallet.getPointsBalance();
        }
        // check if the customer has enough points to redeem
        if (totalPoints < pointsToRedeem) {
            throw new IllegalStateException("Not enough points available for redemption.");
        }

        // get all the points details for the customer
        PointsHeader pointsHeader = createPointsHeader(customerId, pointsToRedeem, pointUseConcept);
        int remainingPointsToRedeem = pointsToRedeem;

        // iterate over the point wallets to redeem points

        for (PointWallet wallet : pointWallets) {
            if (remainingPointsToRedeem == 0) {
                break;
            }
            int pointsRemaining = wallet.getPointsBalance();
            if (pointsRemaining >= remainingPointsToRedeem) {
                
                PointsDetail pointsDetail = createPointsDetail(pointsHeader, remainingPointsToRedeem, wallet);
                pointsDetailDAO.create(pointsDetail);
                wallet.setUsedPoints(wallet.getUsedPoints() + remainingPointsToRedeem);
                remainingPointsToRedeem = 0;
            } else {
                
                PointsDetail pointsDetail = createPointsDetail(pointsHeader, pointsRemaining, wallet);
                pointsDetailDAO.create(pointsDetail);
                wallet.setUsedPoints(wallet.getUsedPoints() + pointsRemaining);
                remainingPointsToRedeem = remainingPointsToRedeem - pointsRemaining;

            }
            // persist the point wallet
            pointWalletService.updatePointWallet(wallet);



        }

   
        return pointsHeader;
    }

    private PointsDetail createPointsDetail(PointsHeader pointsHeader, Integer pointsToRedeem, PointWallet pointWallet) {
        PointsDetail pointsDetail = new PointsDetail();

        // calculate the points used and stre ut in a variable

        Integer pointsUsed = pointsToRedeem + pointWallet.getUsedPoints();
        
        pointsDetail.setPointsHeader(pointsHeader);
        pointsDetail.setPointsUsed(pointsUsed);
        pointsDetail.setPointWallet(pointWallet);
        return pointsDetail;
    }

    private PointsHeader createPointsHeader(Integer customerId, Integer pointsToRedeem, Integer pointUseConcept_Id) {
        PointsHeader pointsHeader = new PointsHeader();
        // get the customer
        Customer customer = customerService.findCustomerById(customerId);
        pointsHeader.setCustomer(customer);

        pointsHeader.setPointsUsed(pointsToRedeem);
        pointsHeader.setUsageDate(new Date());

        // get the point use concept
        PointUseConcept pointUseConcept = pointUseConceptService.findById(pointUseConcept_Id);
        pointsHeader.setPointUseConcept(pointUseConcept);
        pointsHeaderDAO.create(pointsHeader);
        return pointsHeader;

    }

    public List<PointsDetail> findPointsUsage(Integer customerId, Integer conceptId, LocalDate startDate) {
        return pointsDetailDAO.findPointsUsage(customerId, conceptId, startDate);
    }


}
