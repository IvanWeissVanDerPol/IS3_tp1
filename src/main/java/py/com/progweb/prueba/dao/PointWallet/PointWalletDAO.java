package py.com.progweb.prueba.dao.PointWallet;

import py.com.progweb.prueba.model.PointWallet;
import java.util.List;

public interface PointWalletDAO {
    void create(PointWallet pointWallet);
    PointWallet update(PointWallet pointWallet);
    void delete(Long id);
    PointWallet findById(Long id);
    List<PointWallet> findAll();
}
