package mx.jfnm.ejemplo.pizza.security;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 *
 * @author Juan Fco. Navarrete
 */

@ViewConfig
public interface ViewConfiguration {
    
    static enum Configuration{
        
        @ViewPattern("/admin/*")
        @Admin
        ADMIN,
        
        @ViewPattern("/order/*")
        @Customer
        PLACE_ORDER,
        
        @FacesRedirect
        @ViewPattern("/*")
        @AccessDeniedView("/denied.xhtml")
        @LoginView("/login.xhtml")
        ALL
    }
    
}
