package py.com.progweb.prueba.dao.PointAllocationRule;

import py.com.progweb.prueba.model.PointAllocationRule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PointAllocationRuleDAOImpl implements PointAllocationRuleDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointAllocationRule rule) {
        em.persist(rule);
    }

    @Override
    public PointAllocationRule update(PointAllocationRule rule) {
        return em.merge(rule);
    }

    @Override
    public void delete(Long id) {
        PointAllocationRule rule = findById(id);
        if (rule != null) {
            em.remove(rule);
        }
    }

    @Override
    public PointAllocationRule findById(Long id) {
        return em.find(PointAllocationRule.class, id);
    }

    @Override
    public List<PointAllocationRule> findAll() {
        return em.createQuery("SELECT r FROM PointAllocationRule r", PointAllocationRule.class).getResultList();
    }
}
