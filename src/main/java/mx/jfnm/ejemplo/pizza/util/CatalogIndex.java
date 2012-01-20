package mx.jfnm.ejemplo.pizza.util;


public class CatalogIndex {
    
    public static final Long PIZZA_SIZES = Long.valueOf(CatalogIndexPropertyHelper.getProperty("pizza.sizes"));        
    public static final Long PIZZA_CRUSTS = Long.valueOf(CatalogIndexPropertyHelper.getProperty("pizza.crusts"));        
    public static final Long PIZZA_TOPPINGS = Long.valueOf(CatalogIndexPropertyHelper.getProperty("pizza.toppings"));        
    public static final Long ADDRESS_COUNTRIES = Long.valueOf(CatalogIndexPropertyHelper.getProperty("address.countries"));        
    
}
