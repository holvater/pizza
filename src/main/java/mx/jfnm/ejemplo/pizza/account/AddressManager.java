package mx.jfnm.ejemplo.pizza.account;

import java.util.Currency;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
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

@Named
@Stateful
@ConversationScoped
public class AddressManager {
    
    @Inject
    private EntityManager em;
    
    @Inject
    @Authenticated
    private User user;
    
    private List<Address> userAddresses;
    
    private Address currentAddress = new Address();
    
    @Named   
    @Produces
    @CurrentAddress
    public Address getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(Address currentAddress) {        
        this.currentAddress = currentAddress;
    }
    
    public void saveAddress(Address currentAddress) {
        em.merge(currentAddress);
    }
        
    @Named
    @Produces
    @ConversationScoped
    public List<Address> getUserAddresses() {        
        userAddresses = em.createNamedQuery(Address.FIND_BY_USERNAME).setParameter("username", user.getUsername()).getResultList();
        return userAddresses;
    }
   
}
