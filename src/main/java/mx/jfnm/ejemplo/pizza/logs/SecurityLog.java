package mx.jfnm.ejemplo.pizza.logs;

import org.jboss.solder.logging.Log;
import org.jboss.solder.logging.MessageLogger;
import org.jboss.solder.messages.Message;

@MessageLogger
public interface SecurityLog {
    
    @Log
    @Message("Login for %s failed")
    void loginFailed(String username);
    
    @Log
    @Message("Login for %s succeeded")
    void loginSucceeded(String username);
    
    @Log
    @Message("Authorization not granted for %s")
    void authorizationDenied(String username);
    
    @Log
    @Message("User %s logged out")
    void loggedOut(String username);
}
