package py.com.progweb.prueba.dao.PointAllocationRule;

import py.com.progweb.prueba.model.PointAllocationRule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    
    public PointAllocationRule findRuleByTransactionAmount(Double transactionAmount) {
        TypedQuery<PointAllocationRule> query = em.createQuery(
            "SELECT r FROM PointAllocationRule r WHERE :amount BETWEEN r.lowerLimit AND r.upperLimit",
            PointAllocationRule.class);
        query.setParameter("amount", transactionAmount);

        List<PointAllocationRule> rules = query.getResultList();
        
        if (!rules.isEmpty()) {
            // Assuming the rules are sorted such that the most generous rule comes first
            // You might need to order your results in the query if that's not the case
            return rules.get(0);
        }
        
        // If no specific rule is found, you might want to return a default rule or null
        return null; // Or handle this case as appropriate for your business logic
    }
    
}
