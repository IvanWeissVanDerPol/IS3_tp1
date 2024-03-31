package py.com.progweb.prueba.dao.PointWallet;


import py.com.progweb.prueba.model.PointWallet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class PointWalletDAOImpl implements PointWalletDAO {

    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    @Override
    public void create(PointWallet pointWallet) {
        em.persist(pointWallet);
    }

    @Override
    public PointWallet update(PointWallet pointWallet) {
        return em.merge(pointWallet);
    }

    @Override
    public void delete(Long id) {
        PointWallet pointWallet = findById(id);
        if (pointWallet != null) {
            em.remove(pointWallet);
        }
    }

    @Override
    public PointWallet findById(Long id) {
        return em.find(PointWallet.class, id);
    }

    @Override
    public List<PointWallet> findAll() {
        return em.createQuery("SELECT p FROM PointWallet p", PointWallet.class).getResultList();
    }
}
