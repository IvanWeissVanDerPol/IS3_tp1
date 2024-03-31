package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.Points.PointsDetailDAO;
import py.com.progweb.prueba.dao.Points.PointsHeaderDAO;
import py.com.progweb.prueba.model.PointsDetail;
import py.com.progweb.prueba.model.PointsHeader;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class PointsService {

    @Inject
    private PointsHeaderDAO pointsHeaderDAO;

    @Inject
    private PointsDetailDAO pointsDetailDAO;

    public PointsHeader redeemPoints(Integer customerId, Integer pointsToRedeem, String pointUseConcept) {
        List<PointsDetail> eligiblePointsDetails = pointsDetailDAO.findEligiblePointsDetails(customerId, pointsToRedeem);
        if (eligiblePointsDetails.isEmpty() || sumPoints(eligiblePointsDetails) < pointsToRedeem) {
            throw new IllegalStateException("Not enough points available for redemption.");
        }
        PointsHeader pointsHeader = createPointsHeader(customerId, pointsToRedeem, pointUseConcept);
        int remainingPointsToRedeem = pointsToRedeem;

        for (PointsDetail detail : eligiblePointsDetails) {
            int pointsUsedFromThisDetail = Math.min(detail.getPointsRemaining(), remainingPointsToRedeem);
            remainingPointsToRedeem -= pointsUsedFromThisDetail;

            detail.setPointsRemaining(detail.getPointsRemaining() - pointsUsedFromThisDetail);
            pointsDetailDAO.update(detail);

            PointsDetail redemptionDetail = createPointsDetail(detail, pointsUsedFromThisDetail, pointsHeader);
            pointsDetailDAO.create(redemptionDetail);

            if (remainingPointsToRedeem == 0) {
                break;
            }
        }

        pointsHeaderDAO.create(pointsHeader);
        return pointsHeader;
    }

    private PointsHeader createPointsHeader(Integer customerId, Integer pointsToRedeem, String pointUseConcept) {
        PointsHeader pointsHeader = new PointsHeader();
        pointsHeader.setCustomerId(customerId);
        pointsHeader.setPointsUsed(pointsToRedeem);
        pointsHeader.setUsageDate(new Date());
        pointsHeader.setPointUseConcept(pointUseConcept);
        return pointsHeader;
    }

    private PointsDetail createPointsDetail(PointsDetail originalDetail, int pointsUsed, PointsHeader pointsHeader) {
        PointsDetail pointsDetail = new PointsDetail();
        pointsDetail.setPointsHeader(pointsHeader);
        pointsDetail.setPointsUsed(pointsUsed);
        pointsDetail.setPointWallet(originalDetail.getPointWallet());
        pointsDetail.setUsageDate(new Date());
        int remainingPoints = originalDetail.getPointsRemaining() - pointsUsed;
        pointsDetail.setPointsRemaining(remainingPoints);
        return pointsDetail;
    }

    private int sumPoints(List<PointsDetail> details) {
        int sum = 0;
        for (PointsDetail detail : details) {
            sum += detail.getPointsRemaining();
        }
        return sum;
    }

    public List<PointsHeader> getPointsUsageByCustomer(Long customerId) {
        return pointsHeaderDAO.findByCustomerId(customerId);
    }

}
