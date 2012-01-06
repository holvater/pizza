package mx.jfnm.ejemplo.pizza.security;

import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

public class Restrictions {
    
    @Secures
    @Admin
    public boolean isAdmin(Identity identity   ) {
        System.out.println("isAdmin?");
        return false;
    }

    @Secures
    @Customer
    public boolean isCustomer(Identity identity) {
        System.out.println("isCustomer?");
        if(identity != null) {
            return identity.hasRole("REGULAR", "CUSTOMERS", "USERS");
        }            
        return false;  
    }
    
}
 