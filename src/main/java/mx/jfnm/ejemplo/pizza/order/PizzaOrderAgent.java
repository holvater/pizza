package mx.jfnm.ejemplo.pizza.order;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Stateful;
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
import org.jboss.solder.logging.Logger;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Named
@Stateful
@ConversationScoped
public class PizzaOrderAgent implements Serializable {

    @Inject
    @Authenticated
    private User user;        
    
    @Inject
    private EntityManager em;
    
    @Inject
    private Logger log;
    
    private Order order;    
    private Pizza currentPizza;  
    
    @Named
    @Produces    
    public Order getOrder() {
        return order;
    }

    public Pizza getCurrentPizza() {
        return currentPizza;
    }

    public void setCurrentPizza(Pizza currentPizza) {
        this.currentPizza = currentPizza;
    }             
    
    @Begin(timeout = 15 * 60 * 1000) //milliseconds
    public void begin() {       
        log.infov("User {0} is creating an order", user.getUsername());
        order = new Order(user);           
        currentPizza = new Pizza(order);
    }
    
    @End
    public String confirmOrder() {
        order.setCreationDate(new Date());
        for(Pizza pizza : order.getPizzas())
            em.persist(pizza);
        em.persist(order);
        log.infov("Order {0} placed by {1} with {2} pizzas", order.getId(), user.getUsername(), order.getPizzas().size());        
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
    
    public String addNewPizza() {
        currentPizza = new Pizza(order);    
        return "createOrder";
    }
    
    public String addPizzaToOrder() {        
        order.getPizzas().remove(currentPizza);
        order.getPizzas().add(currentPizza);
        currentPizza = new Pizza(order);    
        return "createOrder";
    }        
    
    public String addressSelection(Address address) {        
        order.setAddress(address);
        return "confirmOrder";
    }

}
