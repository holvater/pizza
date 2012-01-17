package mx.jfnm.ejemplo.pizza.security;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;
import mx.jfnm.ejemplo.pizza.logs.SecurityLog;
import org.jboss.seam.security.Identity;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Named
public class LogoutAgent {
 
    @Inject
    private SecurityLog log;
    
    @Inject
    private Identity identity;
    
    @Inject
    private Conversation conversation;
    
    public String logout() {
        log.loggedOut(identity.getUser().getId());         
        identity.logout();
        if(conversation != null && !conversation.isTransient()) {
            conversation.end();
        }
        return "index";
    }
    
}
