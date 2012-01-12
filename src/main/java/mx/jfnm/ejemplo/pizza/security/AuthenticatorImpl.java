package mx.jfnm.ejemplo.pizza.security;

import mx.jfnm.ejemplo.pizza.account.Authenticated;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import mx.jfnm.ejemplo.pizza.domain.Catalog;
import mx.jfnm.ejemplo.pizza.domain.Role;
import mx.jfnm.ejemplo.pizza.domain.User;
import mx.jfnm.ejemplo.pizza.i18n.DefaultBundleKey;
import mx.jfnm.ejemplo.pizza.logs.SecurityLog;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.TypedCategory;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

public class AuthenticatorImpl extends BaseAuthenticator implements Authenticator {

    @Inject
    private EntityManager em;
    
    @Inject
    private Messages messages;
    
    @Inject
    @TypedCategory(AuthenticatorImpl.class)
    private SecurityLog log;
    
    @Inject
    private Identity identity;
    
    @Inject
    private Credentials credentials;   
    
    @Inject
    @Authenticated
    private Event<User> loginEventSrc;        

    @Override
    public void authenticate() {
        if ((credentials.getUsername() == null) || (credentials.getCredential() == null)) {
            authenticationFailure();
            return;
        }
        
        try {
            User user = (User) em.createNamedQuery(User.FINDBYUSERNAME)
                    .setParameter("username", credentials.getUsername()).getSingleResult();
            if (credentials.getCredential() instanceof PasswordCredential
                    && user.getPassword()
                    .equals(((PasswordCredential) credentials.getCredential()).getValue())) {
                //Load role from DB
                Role role = (Role)em.createNamedQuery(Role.FINDBYUSERNAME)
                        .setParameter("username", credentials.getUsername()).getSingleResult();                
                String roleName = em.find(Catalog.class, 
                        role.getRole().getId()).getShortDescription();
                String group = em.find(Catalog.class, 
                        role.getGroup().getId()).getShortDescription();
                String groupType = em.find(Catalog.class,
                        role.getGroupType().getId()).getShortDescription();                
                //Add role to user                
                identity.addRole(roleName, group, groupType);                
                log.loginSucceeded(credentials.getUsername());                
                loginEventSrc.fire(user);                
                setStatus(AuthenticationStatus.SUCCESS);
                setUser(new SimpleUser(user.getUsername()));
                return;
            } else {
                authenticationFailure();
            }
        } catch (NoResultException nre) {
            authenticationFailure();
        }        
    }

    private void authenticationFailure() {
        log.loginFailed(credentials.getUsername());
        messages.error(new DefaultBundleKey("security_login_failed")).defaults("Incorrect username or password");
        setStatus(AuthenticationStatus.FAILURE);
    }
    
}
