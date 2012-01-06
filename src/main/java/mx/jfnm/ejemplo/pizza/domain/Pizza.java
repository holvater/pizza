/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Entity
@Table(name="pizza")
public class Pizza implements Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_crust")
    private Catalog crust;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_size")
    private Catalog dimension;
    
    @ManyToOne
    @JoinColumn(name="id_order")
    private Order order;
    
    @ManyToMany
    @JoinTable(name="pizza_topping", 
            joinColumns=@JoinColumn(name="id_pizza"),
            inverseJoinColumns=@JoinColumn(name="id_topping"))
    private List<Catalog> toppings;

    public Pizza(Order order) {
        this.order = order;
    }

    public Pizza() {
    }

    public Pizza(Catalog crust, Catalog size, Order order) {
        this.crust = crust;
        this.dimension = size;
        this.order=order;
        this.toppings = new ArrayList<Catalog>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    

    public Catalog getCrust() {
        return crust;
    }

    public void setCrust(Catalog crust) {
        this.crust = crust;
    }

    public Catalog getSize() {
        return dimension;
    }

    public void setSize(Catalog size) {
        this.dimension = size;
    }

    public List<Catalog> getToppings() {
        return toppings;
    }

    public void setToppings(List<Catalog> toppings) {
        this.toppings = toppings;
    }        
    
}
