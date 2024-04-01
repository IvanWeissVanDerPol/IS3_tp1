package py.com.progweb.prueba.dao.PointUseConcept;

import py.com.progweb.prueba.model.PointUseConcept;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PointUseConceptDAOImpl implements PointUseConceptDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointUseConcept concept) {
        em.persist(concept);
    }

    @Override
    public PointUseConcept update(PointUseConcept concept) {
        return em.merge(concept);
    }

    @Override
    public void delete(Integer id) {
        PointUseConcept concept = findById(id);
        if (concept != null) {
            em.remove(concept);
        }
    }

    @Override
    public PointUseConcept findById(Integer id) {
        return em.find(PointUseConcept.class, id);
    }

    @Override
    public List<PointUseConcept> findAll() {
        return em.createQuery("SELECT p FROM PointUseConcept p", PointUseConcept.class).getResultList();
    }
}
