package mx.jfnm.ejemplo.pizza.exceptioncontrol;

import javax.enterprise.context.NonexistentConversationException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;
import org.jboss.weld.context.http.HttpConversationContext;

/**
 *
 * @author Juan Fco. Navarrete
 */

@HandlesExceptions
public class NonexistentConversationExceptionHandler {    

    public void conversationEndedExceptionHandler(@Handles(precedence = 100) CaughtException<NonexistentConversationException> event, Logger log, 
            HttpConversationContext conversationContext, FacesContext facesContext) {
        log.info("Conversation ended, redirecting to error page");
        conversationContext.activate(null); 
        event.handled();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, "*", "conversationEnded");
    }
    
}
