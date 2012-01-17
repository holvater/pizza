package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Entity
@Table(name = "delivery_order")
@NamedQueries({
    @NamedQuery(name = Order.FIND_BY_DATE, query = "select o from Order o where o.creationDate > :date order by o.creationDate desc")
})
public class Order implements Serializable{

    @Id    
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)   
    @Column(name = "creation_date")
    private Date creationDate;
        
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;
    
    @Size(min = 1)
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)    
    private List<Pizza> pizzas;
    
    public static final String FIND_BY_DATE = "Order.findByDate";

    public Order() {
    }

    public Order(User user) {
        this.user = user;            
        this.pizzas = new ArrayList<Pizza>();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

