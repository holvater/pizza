package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
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
@Table(name = "corporate_catalog")
@NamedQueries({
        @NamedQuery(name = Catalog.FINDBYGROUP, query = "select c from Catalog c where c.group.id = :groupId and c.active = true")
})
public class Catalog implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "catalog_group")
    private Catalog group;
    
    @Column(name = "short_description")
    private String shortDescription;
    
    @Column(name = "long_description")
    private String longDescription;
        
    @Column(name = "active")
    private Boolean active;
    
    public static final String FINDBYGROUP = "Catalog.findByGroup";        

    public Catalog() {
    }
    
    public Catalog(String shortDescription, String longDescription, Boolean active) {        
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.active = active;
    }

    public Catalog(Catalog group, String shortDescription, String longDescription, Boolean active) {
        this.group = group;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.active = active;
    }

    public Catalog getGroup() {
        return group;
    }

    public void setGroup(Catalog group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Catalog other = (Catalog) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
        
}
