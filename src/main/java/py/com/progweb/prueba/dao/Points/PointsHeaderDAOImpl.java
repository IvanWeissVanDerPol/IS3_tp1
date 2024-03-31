package py.com.progweb.prueba.dao.Points;

import py.com.progweb.prueba.model.PointsHeader;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;


@Stateless
public class PointsHeaderDAOImpl implements PointsHeaderDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointsHeader pointsHeader) {
        em.persist(pointsHeader);
    }

    @Override
    public PointsHeader update(PointsHeader pointsHeader) {
        return em.merge(pointsHeader);
    }

    @Override
    public void delete(Long id) {
        PointsHeader pointsHeader = findById(id);
        if (pointsHeader != null) {
            em.remove(pointsHeader);
        }
    }

    @Override
    public PointsHeader findById(Long id) {
        return em.find(PointsHeader.class, id);
    }

    @Override
    public List<PointsHeader> findAll() {
        return em.createQuery("SELECT h FROM PointsHeader h", PointsHeader.class).getResultList();
    }

    @Override
    public List<PointsHeader> findByCustomerId(Long customerId) {
        TypedQuery<PointsHeader> query = em.createQuery(
                "SELECT ph FROM PointsHeader ph WHERE ph.customerId = :customerId", PointsHeader.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

}
