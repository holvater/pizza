package mx.jfnm.ejemplo.pizza.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

public class Restrictions {
    
    @Secures
    @Admin
    public boolean isAdmin(Identity identity) {        
        if(identity != null) {
            return identity.hasRole("ROOT", "ADMINISTRATORS", "USERS");
        }  
        return false;
    }

    @Secures
    @Customer
    public boolean isCustomer(Identity identity) {        
        if(identity != null) {
            return identity.hasRole("REGULAR", "CUSTOMERS", "USERS");
        }            
        return false;  
    }
    
}
 