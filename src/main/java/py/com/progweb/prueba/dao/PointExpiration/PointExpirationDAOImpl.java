package py.com.progweb.prueba.dao.PointExpiration;

import py.com.progweb.prueba.model.PointExpiration;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PointExpirationDAOImpl implements PointExpirationDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointExpiration pointExpiration) {
        em.persist(pointExpiration);
    }

    @Override
    public PointExpiration update(PointExpiration pointExpiration) {
        return em.merge(pointExpiration);
    }

    @Override
    public void delete(Long id) {
        PointExpiration pointExpiration = findById(id);
        if (pointExpiration != null) {
            em.remove(pointExpiration);
        }
    }

    @Override
    public PointExpiration findById(Long id) {
        return em.find(PointExpiration.class, id);
    }

    @Override
    public List<PointExpiration> findAll() {
        return em.createQuery("SELECT p FROM PointExpiration as p", PointExpiration.class).getResultList();
    }
}
