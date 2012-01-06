/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.jfnm.ejemplo.pizza.security;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;
import mx.jfnm.ejemplo.pizza.log.SecurityLog;
import org.jboss.seam.security.Identity;

/**
 *
 * @author Juan Fco. Navarrete
 */

@Named
public class PassportAgent implements Serializable {
 
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
