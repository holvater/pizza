package mx.jfnm.ejemplo.pizza.log;

import org.jboss.solder.logging.Log;
import org.jboss.solder.logging.Logger.Level;
import org.jboss.solder.logging.MessageLogger;
import org.jboss.solder.messages.Message;

@MessageLogger
public interface SecurityLog {
    
    @Log(level= Level.INFO)
    @Message("Login for %s failed")
    void loginFailed(String username);
    
    @Log(level= Level.INFO)
    @Message("Login for %s succeeded")
    void loginSucceeded(String username);
    
    @Log(level= Level.INFO)
    @Message("Authorization not granted for %s")
    void authorizationDenied(String username);
    
    @Log(level= Level.INFO)
    @Message("User %s logged out")
    void loggedOut(String username);
    
}
