package mx.jfnm.ejemplo.pizza.account;

import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.domain.Address;
import mx.jfnm.ejemplo.pizza.domain.Catalog;
import mx.jfnm.ejemplo.pizza.util.CatalogIndex;

/**
 *
 * @author Juan Fco. Navarrete
 */

public class AddressComponentsProducer {
    
    @Inject
    private EntityManager em;
    
    @Named
    @Produces
    @ConversationScoped    
    public List<Catalog> getAddressCountries() {
        return em.createNamedQuery(Catalog.FIND_BY_GROUP).setParameter("groupId", CatalogIndex.ADDRESS_COUNTRIES).getResultList();
    } 
    
    @Named
    @Produces    
    public List<Catalog> getCountryStates(@CurrentAddress Address address) {
        return em.createNamedQuery(Catalog.FIND_BY_GROUP).setParameter("groupId", address.getCountry().getId()).getResultList();
    } 
    
}
