package mx.jfnm.ejemplo.pizza.order;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.account.Authenticated;
import mx.jfnm.ejemplo.pizza.domain.Address;
import mx.jfnm.ejemplo.pizza.domain.Order;
import mx.jfnm.ejemplo.pizza.domain.Pizza;
import mx.jfnm.ejemplo.pizza.domain.User;
import org.jboss.seam.faces.context.conversation.Begin;
import org.jboss.seam.faces.context.conversation.End;

/**
 *
 * @author Juan Fco
 */

@Named
@Stateful
@ConversationScoped
public class PizzaOrderAgent implements Serializable {

    @Inject
    @Authenticated
    private User user;
    
    private Order order;
    private Pizza currentPizza;   
    
    @Inject
    private Conversation conversation;  
    
    @Inject
    private EntityManager em;
    
    @Named
    @Produces
    @ConversationScoped    
    public Order getOrder() {
        return order;
    }

    public Pizza getCurrentPizza() {
        return currentPizza;
    }

    public void setCurrentPizza(Pizza currentPizza) {
        this.currentPizza = currentPizza;
    }             
    
    @Begin
    public void begin() {                           
        order = new Order(user);            
        currentPizza = new Pizza(order);
    }
    
    @End
    public String confirmOrder() {
        order.setCreationDate(new Date());
        for(Pizza p: order.getPizzas())
            em.persist(p);
        em.persist(order);
        return "completed";
    }

    @End
    public String cancelOrder() {        
        return "index";
    }   
    
    public void removeFromOrder(Pizza pizza) {
        order.getPizzas().remove(pizza);
    }
    
    public String editPizza(Pizza pizza) {
        currentPizza = pizza;
        return "createOrder";
    }
    
    public String addPizzaToOrder() {        
        order.getPizzas().remove(currentPizza);
        order.getPizzas().add(currentPizza);
        currentPizza = new Pizza(order);        
        return "createOrder";
    }        
    
    public String addAddressToOrder(Address address) {
        order.setAddress(address);
        return "confirmOrder";
    }

}
