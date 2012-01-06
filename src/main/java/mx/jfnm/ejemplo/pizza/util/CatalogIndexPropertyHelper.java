package mx.jfnm.ejemplo.pizza.util;

import java.util.ResourceBundle;

public class CatalogIndexPropertyHelper {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("mx.jfnm.ejemplo.pizza.util.CatalogIndex");    

    public static String getProperty(String key) {
        return resourceBundle.getString(key);        
    }
}
