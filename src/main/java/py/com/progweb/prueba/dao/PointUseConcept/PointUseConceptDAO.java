package py.com.progweb.prueba.dao.PointUseConcept;

import py.com.progweb.prueba.model.PointUseConcept;
import java.util.List;

public interface PointUseConceptDAO {
    void create(PointUseConcept concept);
    PointUseConcept update(PointUseConcept concept);
    void delete(Long id);
    PointUseConcept findById(Long id);
    List<PointUseConcept> findAll();
}
