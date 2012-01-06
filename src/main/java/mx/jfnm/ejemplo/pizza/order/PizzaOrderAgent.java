package mx.jfnm.ejemplo.pizza.order;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
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
        currentPizza = new Pizza();
        order = new Order(user);            
    }
    
    @End
    public void confirm() {
        
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
        currentPizza = new Pizza();        
        return "createOrder";
    }        
    
    public String addAddressToOrder(Address address) {
        order.setAddress(address);
        return "confirmOrder";
    }

}
