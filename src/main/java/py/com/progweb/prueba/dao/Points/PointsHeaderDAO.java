package py.com.progweb.prueba.dao.Points;

import py.com.progweb.prueba.model.PointsHeader;
import java.util.List;

public interface PointsHeaderDAO {
    void create(PointsHeader pointsHeader);
    PointsHeader update(PointsHeader pointsHeader);
    void delete(Long id);
    PointsHeader findById(Long id);
    List<PointsHeader> findAll();
    List<PointsHeader> findByCustomerId(Long customerId);

}
