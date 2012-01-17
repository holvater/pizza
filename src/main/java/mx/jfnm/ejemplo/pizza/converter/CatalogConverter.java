package mx.jfnm.ejemplo.pizza.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import mx.jfnm.ejemplo.pizza.domain.Catalog;

@RequestScoped
@FacesConverter(value = "catalogConverter")
public class CatalogConverter implements Converter {

    @Inject
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
