package mx.jfnm.ejemplo.pizza.account;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import mx.jfnm.ejemplo.pizza.domain.User;

@Stateful
@SessionScoped
public class UserManager implements Serializable {        
    
    private User currentUser;

    @Produces
    @Authenticated
    @Named
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void onLogin(@Observes @Authenticated User user, HttpServletRequest request) {        
        currentUser = user;
        request.getSession().setMaxInactiveInterval(3600);
    }
        
}
