package py.com.progweb.prueba.dao.Points;

import py.com.progweb.prueba.model.PointsDetail;

import java.time.LocalDate;
import java.util.List;

public interface PointsDetailDAO {
    void create(PointsDetail pointsDetail);
    PointsDetail update(PointsDetail pointsDetail);
    void delete(Long id);
    PointsDetail findById(Long id);
    List<PointsDetail> findAll();
    List<PointsDetail> findEligiblePointsDetails(Integer customerId, Integer pointsToRedeem);
    List<PointsDetail> findPointsUsage(Integer customerId, Integer conceptId, LocalDate startDate);

}
