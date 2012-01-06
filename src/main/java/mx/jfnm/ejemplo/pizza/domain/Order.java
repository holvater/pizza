package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Fco. Navarrete
 */

public class Order implements Serializable{

    private User user;
    private Address address;
    private List<Pizza> pizzas;

    public Order() {
    }

    public Order(User user) {
        this.user = user;        
        this.pizzas = new ArrayList<Pizza>();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}

