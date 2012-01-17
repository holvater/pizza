package mx.jfnm.ejemplo.pizza.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.username = :username and u.active = true")
})
public class User implements Serializable{
    
    @Id
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")    
    private String password;
    
    @Column(name = "active")
    private Boolean active;
    
    public static final String FIND_BY_USERNAME = "User.findByUsername";        

    public User() {
    }

    public User(String username, String password, Boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActivo(Boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", active=" + active + '}';
    }
    
}
