package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Entity
@Table(name = "pizza")
@NamedQueries({
    @NamedQuery(name = Pizza.COUNT_BY_DATE, query = "select count(p.id) from Pizza p where p.order.creationDate >= CURRENT_DATE")
})
public class Pizza implements Serializable {
        
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        
    @NotNull 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_crust")
    private Catalog crust;
    
    @NotNull    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_size")
    private Catalog sizee;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_topping",
            joinColumns=@JoinColumn(name = "id_pizza"),
            inverseJoinColumns=@JoinColumn(name = "id_topping"))
    private List<Catalog> toppings;
    
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;
    
    public static final String COUNT_BY_DATE = "Pizza.countByDate";

    public Pizza() {
    }

    public Pizza(Order order) {                
        this.order = order;
        this.toppings = new ArrayList<Catalog>();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Catalog getSizee() {
        return sizee;
    }

    public void setSizee(Catalog size) {
        this.sizee = size;
    }

    public List<Catalog> getToppings() {
        return toppings;
    }

    public void setToppings(List<Catalog> toppings) {
        this.toppings = toppings;
    }        
    
}
