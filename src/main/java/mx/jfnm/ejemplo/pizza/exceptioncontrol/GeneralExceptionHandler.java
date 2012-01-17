package mx.jfnm.ejemplo.pizza.exceptioncontrol;

import org.jboss.solder.logging.Logger;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

@HandlesExceptions
public class GeneralExceptionHandler {

    public void printExceptionMessage(@Handles(precedence = 0) CaughtException<Throwable> event, Logger log) {
        log.error("Exception logged by seam-catch catcher", event.getException());
        event.rethrow();
    }
    
}
