package mx.jfnm.ejemplo.pizza.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Entity
@Table(name = "address")
@NamedQueries({
        @NamedQuery(name = Address.FINDBYUSERNAME, query = "select a from Address a where a.user.username = :username and a.active = true")
})
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "exterior_number")
    private String exteriorNumber;
    
    @Column(name = "interior_number")
    private String interiorNumber;
    
    @Column(name = "zip_code")
    private String zipCode;
    
    @ManyToOne
    @JoinColumn(name = "id_state")
    private Catalog state;
    
    @ManyToOne
    @JoinColumn(name = "id_country")
    private Catalog country;
    
    @Column(name = "active")
    private Boolean active;
    
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    
    public static final String FINDBYUSERNAME = "Address.findByUsername";

    public Address() {
    }

    public Address(String street, String exteriorNumber, String interiorNumber, String zipCode, Catalog state, Catalog country, Boolean active, User user) {
        this.street = street;
        this.exteriorNumber = exteriorNumber;
        this.interiorNumber = interiorNumber;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
        this.active = active;
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Catalog getCountry() {
        return country;
    }

    public void setCountry(Catalog country) {
        this.country = country;
    }

    public String getExteriorNumber() {
        return exteriorNumber;
    }

    public void setExteriorNumber(String exteriorNumber) {
        this.exteriorNumber = exteriorNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInteriorNumber() {
        return interiorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
    }

    public Catalog getState() {
        return state;
    }

    public void setState(Catalog state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
