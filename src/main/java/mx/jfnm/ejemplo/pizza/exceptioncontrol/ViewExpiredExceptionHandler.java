package mx.jfnm.ejemplo.pizza.exceptioncontrol;

import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
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
public class ViewExpiredExceptionHandler {    

    public void conversationEndedExceptionHandler(@Handles(precedence = 100) CaughtException<ViewExpiredException> event, Logger log, 
            HttpConversationContext conversationContext, FacesContext facesContext) {
        log.warn("View expired, redirecting to error page");
        conversationContext.activate(null); 
        event.handled();
        facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, "*", "viewExpired");
    }
    
}
