package mx.jfnm.ejemplo.pizza.account;

import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.domain.Address;
import mx.jfnm.ejemplo.pizza.domain.User;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Stateful
@SessionScoped
public class AddressManager {
    
    @Inject
    private EntityManager em;
    
    @Inject
    @Authenticated
    private User user;
    
    private List<Address> userAddresses;
        
    @Named
    @Produces
    public List<Address> getUserAddresses() {
        if(userAddresses == null)
            userAddresses = em.createNamedQuery(Address.FINDBYUSERNAME).setParameter("username", user.getUsername()).getResultList();
        return userAddresses;
    }
   
}
