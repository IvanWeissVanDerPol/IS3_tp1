package py.com.progweb.prueba.dao.PointAllocationRule;

import py.com.progweb.prueba.model.PointAllocationRule;
import java.util.List;

public interface PointAllocationRuleDAO {
    void create(PointAllocationRule rule);
    PointAllocationRule update(PointAllocationRule rule);
    void delete(Long id);
    PointAllocationRule findById(Long id);
    List<PointAllocationRule> findAll();
}
