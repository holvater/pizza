package mx.jfnm.ejemplo.pizza.order;

import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.domain.Catalog;
import mx.jfnm.ejemplo.pizza.util.CatalogIndex;

/**
 *
 * @author Juan Fco. Navarrete
 */

public class PizzaComponentsProducer {
    
    @Inject
    private EntityManager em;
    
    @Named
    @Produces
    @ConversationScoped    
    public List<Catalog> getPizzaSizes() {
        return em.createNamedQuery(Catalog.FIND_BY_GROUP).setParameter("groupId", CatalogIndex.PIZZA_SIZES).getResultList();
    }
    
    @Named
    @Produces
    @ConversationScoped    
    public List<Catalog> getPizzaCrusts() {
        return em.createNamedQuery(Catalog.FIND_BY_GROUP).setParameter("groupId", CatalogIndex.PIZZA_CRUSTS).getResultList();
    }
    
    @Named
    @Produces
    @ConversationScoped    
    public List<Catalog> getPizzaToppings() {
        return em.createNamedQuery(Catalog.FIND_BY_GROUP).setParameter("groupId", CatalogIndex.PIZZA_TOPPINGS).getResultList();
    }
    
}
