package mx.jfnm.ejemplo.pizza.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Fco. Navarrete
 */

public class Resources {
    
    @Produces    
    @PersistenceContext(unitName = "pizzaPU")    
    private EntityManager em;
    
}
