package py.com.progweb.prueba.dao.PointExpiration;

import py.com.progweb.prueba.model.PointExpiration;

import java.util.List;

public interface PointExpirationDAO {
    void create(PointExpiration pointExpiration);
    PointExpiration update(PointExpiration pointExpiration);
    void delete(Long id);
    PointExpiration findById(Long id);
    List<PointExpiration> findAll();
}
