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

@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = Role.FIND_BY_USERNAME, query = "select r from Role r where r.user.username = :username and r.active = true")
})
public class Role implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Catalog role;
    
    @ManyToOne
    @JoinColumn(name = "id_group")
    private Catalog group;
    
    @ManyToOne
    @JoinColumn(name = "id_group_type")
    private Catalog groupType;
    
    @Column(name = "active")
    private Boolean active;
    
    @ManyToOne
    
   @JoinColumn(name = "username") private User user;
    
    public static final String FIND_BY_USERNAME = "Role.findByUsername";

    public Role() {
    }

    public Role(Catalog role, Catalog group, Catalog groupType, User user, Boolean active) {
        this.role = role;
        this.group = group;
        this.groupType = groupType;
        this.user = user;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Catalog getGroup() {
        return group;
    }

    public void setGroup(Catalog group) {
        this.group = group;
    }

    public Catalog getGroupType() {
        return groupType;
    }

    public void setGroupType(Catalog groupType) {
        this.groupType = groupType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Catalog getRole() {
        return role;
    }

    public void setRole(Catalog role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role=" + role + ", group=" + group + ", groupType=" + groupType + ", user=" + user + '}';
    }
            
}
