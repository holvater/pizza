package mx.jfnm.ejemplo.pizza.exceptioncontrol;

import javax.inject.Inject;

import mx.jfnm.ejemplo.pizza.domain.User;
import mx.jfnm.ejemplo.pizza.i18n.DefaultBundleKey;
import mx.jfnm.ejemplo.pizza.logs.SecurityLog;
import mx.jfnm.ejemplo.pizza.account.Authenticated;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

@HandlesExceptions
public class AuthorizationExceptionHandler {
        
    @Inject
    @Authenticated
    private User currentUser;
    
    @Inject
    private Messages messages;
    
    @Inject
    private SecurityLog log;   

    public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt) {
        log.authorizationDenied(currentUser == null ? "Anonymous" : currentUser.getUsername());
        messages.error(new DefaultBundleKey("security_not_allowed")).defaults("You do not have the necessary permissions to perform that operation");
        evt.handled();
    }
    
}
