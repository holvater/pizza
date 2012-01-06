package mx.jfnm.ejemplo.pizza.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.jfnm.ejemplo.pizza.domain.Catalog;

@RequestScoped
@FacesConverter("catalogConverter")
public class CatalogConverter implements Converter {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id = Long.parseLong(string);
        return em.find(Catalog.class, id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((Catalog)o).getId().toString();
    }
    
}
