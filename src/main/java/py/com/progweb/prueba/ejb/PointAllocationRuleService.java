package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.PointAllocationRule.PointAllocationRuleDAO;
import py.com.progweb.prueba.model.PointAllocationRule;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PointAllocationRuleService {

    @Inject
    private PointAllocationRuleDAO pointAllocationRuleDAO;

    public void create(PointAllocationRule rule) {
        pointAllocationRuleDAO.create(rule);
    }

    public PointAllocationRule update(PointAllocationRule rule) {
        return pointAllocationRuleDAO.update(rule);
    }

    public void delete(Long id) {
        pointAllocationRuleDAO.delete(id);
    }

    public PointAllocationRule findById(Long id) {
        return pointAllocationRuleDAO.findById(id);
    }

    public List<PointAllocationRule> findAll() {
        return pointAllocationRuleDAO.findAll();
    }

    public int calculatePointsForAmount(Long amount) {
        List<PointAllocationRule> rules = findAll();
        // Assuming rules are sorted by lowerLimit in ascending order.
        for (PointAllocationRule rule : rules) {
            if ((rule.getLowerLimit() <= amount) && (amount <= rule.getUpperLimit())) {
                return (int) (amount / rule.getPointsPerUnit());
            }
        }
        // If no rule fits, return zero or throw an exception depending on business logic.
        return 0;
    }
}
