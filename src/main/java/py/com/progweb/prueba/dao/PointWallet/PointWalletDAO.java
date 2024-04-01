package py.com.progweb.prueba.dao.PointWallet;

import py.com.progweb.prueba.model.PointWallet;
import java.util.List;

public interface PointWalletDAO {
    void create(PointWallet pointWallet);
    PointWallet update(PointWallet pointWallet);
    void delete(Integer id);
    PointWallet findById(Integer id);
    List<PointWallet> findAll();
    List<PointWallet> findAllByCustomerId(Integer customerId);
}
