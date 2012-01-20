package mx.jfnm.ejemplo.pizza.admin;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.domain.Order;
import mx.jfnm.ejemplo.pizza.domain.Pizza;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Named
@Stateful
@RequestScoped
public class PizzaMonitor {
    
    @Inject
    private EntityManager em;         
    
    private Long todaysPizzaCount;
    
    private Date twoHoursAgo = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 2));
    
    @Named
    @Produces     
    @RequestScoped    
    public List<Order> getLastOrders() {        
        return em.createNamedQuery(Order.FIND_BY_DATE).setParameter("date", twoHoursAgo).getResultList();
    }
                
    public Long getTodaysPizzaCount() {        
        if(todaysPizzaCount == null)
            todaysPizzaCount = (Long)em.createNamedQuery(Pizza.COUNT_BY_DATE).getSingleResult();
        return todaysPizzaCount;
    }
    
}
