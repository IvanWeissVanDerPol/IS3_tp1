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

    public void delete(Integer id) {
        pointUseConceptDAO.delete(id);
    }

    public PointUseConcept findById(Integer conceptId) {
        return pointUseConceptDAO.findById(conceptId);
    }

    public List<PointUseConcept> findAll() {
        return pointUseConceptDAO.findAll();
    }
}
