package py.com.progweb.prueba.dao.Points;

import py.com.progweb.prueba.model.PointsDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class PointsDetailDAOImpl implements PointsDetailDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointsDetail pointsDetail) {
        em.persist(pointsDetail);
    }

    @Override
    public PointsDetail update(PointsDetail pointsDetail) {
        return em.merge(pointsDetail);
    }

    @Override
    public void delete(Long id) {
        PointsDetail pointsDetail = findById(id);
        if (pointsDetail != null) {
            em.remove(pointsDetail);
        }
    }

    @Override
    public PointsDetail findById(Long id) {
        return em.find(PointsDetail.class, id);
    }

    @Override
    public List<PointsDetail> findAll() {
        return em.createQuery("SELECT d FROM PointsDetail d", PointsDetail.class).getResultList();
    }


    @Override
    public List<PointsDetail> findEligiblePointsDetails(Integer customerId, Integer pointsToRedeem) {
        String jpql = "SELECT pd FROM PointsDetail pd " +
                "JOIN pd.pointsHeader ph " +
                "JOIN ph.customer c " +
                "WHERE c.id = " + customerId.toString() +
                "AND pd.pointWallet.expirationDate > " + "CURRENT_DATE " +
                "AND pd.pointWallet.pointsBalance >= " + pointsToRedeem.toString() +
                "ORDER BY pd.pointWallet.expirationDate ASC, pd.pointWallet.pointsBalance DESC";

        TypedQuery<PointsDetail> query = em.createQuery(jpql, PointsDetail.class);
        return query.getResultList();
    }

    @Override
    public List<PointsDetail> findPointsUsage(Integer customerId, Integer conceptId, LocalDate startDate) {
        // the end date is the start date at 23:59:59 
        // extract the year, month and day from the start date and store them in variables
        // set the end date time to 23:59:59
        Integer year = startDate.getYear();
        Integer month = startDate.getMonthValue();
        Integer day = startDate.getDayOfMonth();
        LocalDateTime endDate = LocalDateTime.of(year, month, day, 23, 59, 59);
        
        String jpql = "SELECT pd FROM PointsDetail pd " +
                "JOIN pd.pointsHeader ph " +
                "JOIN ph.customer c " +
                "JOIN ph.pointUseConcept puc " +
                "WHERE c.id = " + customerId.toString() +
                "AND puc.id = " + conceptId.toString() +
                "AND pd.usageDate BETWEEN :startDate AND :endDate";

        TypedQuery<PointsDetail> query = em.createQuery(jpql, PointsDetail.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();


        
    }


}
