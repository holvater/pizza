package mx.jfnm.ejemplo.pizza.i18n;

import org.jboss.seam.international.status.builder.BundleKey;

public class DefaultBundleKey extends BundleKey {

    public static final String DEFAULT_BUNDLE_NAME = "ApplicationMessages";

    public DefaultBundleKey(String key) {
        super(DEFAULT_BUNDLE_NAME, key);
    }
}
