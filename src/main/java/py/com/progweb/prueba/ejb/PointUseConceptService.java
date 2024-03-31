package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.PointUseConcept.PointUseConceptDAO;
import py.com.progweb.prueba.model.PointUseConcept;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PointUseConceptService {

    @Inject
    private PointUseConceptDAO pointUseConceptDAO;

    public void create(PointUseConcept concept) {
        pointUseConceptDAO.create(concept);
    }

    public PointUseConcept update(PointUseConcept concept) {
        return pointUseConceptDAO.update(concept);
    }

    public void delete(Long id) {
        pointUseConceptDAO.delete(id);
    }

    public PointUseConcept findById(Long id) {
        return pointUseConceptDAO.findById(id);
    }

    public List<PointUseConcept> findAll() {
        return pointUseConceptDAO.findAll();
    }
}
