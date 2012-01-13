package mx.jfnm.ejemplo.pizza.i18n;

import javax.inject.Inject;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.solder.messages.Locale;

public class DefaultBundleKey extends BundleKey {
    
    @Inject
    private Locale lc;

    public static final String DEFAULT_BUNDLE_NAME = "ApplicationMessages";

    public DefaultBundleKey(String key) {
        super(DEFAULT_BUNDLE_NAME, key);
    }

    
}
