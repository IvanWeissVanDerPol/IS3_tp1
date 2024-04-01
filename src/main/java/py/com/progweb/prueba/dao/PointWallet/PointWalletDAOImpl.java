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
        PointWallet updatedPointWallet = em.merge(pointWallet);
        em.flush(); // Ensure changes are persisted.
        return updatedPointWallet;
    }

    @Override
    public void delete(Integer id) {
        PointWallet pointWallet = findById(id);
        if (pointWallet != null) {
            em.remove(pointWallet);
        }
    }

    @Override
    public PointWallet findById(Integer id) {
        return em.find(PointWallet.class, id);
    }

    @Override
    public List<PointWallet> findAll() {
        return em.createQuery("SELECT p FROM PointWallet p", PointWallet.class).getResultList();
    }

    @Override
    public List<PointWallet> findAllByCustomerId(Integer customerId) {
        return em.createQuery("SELECT p FROM PointWallet p WHERE p.customerId = :customerId AND p.expirationDate >= CURRENT_DATE", PointWallet.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }
}
