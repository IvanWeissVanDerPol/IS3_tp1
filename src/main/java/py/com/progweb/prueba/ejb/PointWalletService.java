package py.com.progweb.prueba.ejb;


import py.com.progweb.prueba.dao.PointWallet.PointWalletDAO;
import py.com.progweb.prueba.model.PointWallet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class PointWalletService {

    @Inject
    private PointWalletDAO pointWalletDAO;

    public PointWallet create(PointWallet pointWallet) {
        pointWalletDAO.create(pointWallet);
        return pointWallet; // Assuming the create method in DAO flushes and attaches the ID to the entity
    }

    public PointWallet update(PointWallet pointWallet) {
        return pointWalletDAO.update(pointWallet);
    }

    public void delete(Long id) {
        pointWalletDAO.delete(id);
    }

    public PointWallet findById(Long id) {
        return pointWalletDAO.findById(id);
    }

    public List<PointWallet> findAll() {
        return pointWalletDAO.findAll();
    }

    // Additional business methods can be added here
    // For example, a method to calculate the expiration of points
    public void calculateExpiration() {
        // Logic to calculate expiration of points
    }

    // Method to handle point transactions (adding/subtracting points)
    public void processPointTransaction(Long walletId, Integer points) {
        PointWallet wallet = findById(walletId);
        if (wallet != null) {
            // Perform business logic to add/subtract points
            update(wallet);
        } else {
            // Handle the case where the wallet doesn't exist
        }
    }
}
