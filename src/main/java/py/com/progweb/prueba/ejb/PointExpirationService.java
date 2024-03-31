package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.dao.PointExpiration.PointExpirationDAO;
import py.com.progweb.prueba.model.PointExpiration;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PointExpirationService {

    @Inject
    private PointExpirationDAO pointExpirationDAO;

    public void create(PointExpiration pointExpiration) {
        pointExpirationDAO.create(pointExpiration);
    }

    public PointExpiration update(PointExpiration pointExpiration) {
        return pointExpirationDAO.update(pointExpiration);
    }

    public void delete(Long id) {
        pointExpirationDAO.delete(id);
    }

    public PointExpiration findById(Long id) {
        return pointExpirationDAO.findById(id);
    }

    public List<PointExpiration> findAll() {
        return pointExpirationDAO.findAll();
    }
}
