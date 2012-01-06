/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan Fco. Navarrete
 */

public class Pizza implements Serializable {
    
    private Long id;
    
    @NotNull
    private Catalog crust;
    
    @NotNull
    private Catalog size;
    private List<Catalog> toppings;

    public Pizza() {
    }

    public Pizza(Catalog crust, Catalog size) {
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<Catalog>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Catalog getCrust() {
        return crust;
    }

    public void setCrust(Catalog crust) {
        this.crust = crust;
    }

    public Catalog getSize() {
        return size;
    }

    public void setSize(Catalog size) {
        this.size = size;
    }

    public List<Catalog> getToppings() {
        return toppings;
    }

    public void setToppings(List<Catalog> toppings) {
        this.toppings = toppings;
    }        
    
}
