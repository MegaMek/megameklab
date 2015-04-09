package megameklab.com.ui;

import megamek.common.Entity;

/**
 * In order to track when changes are made to the unit, we will have one
 * ultimate holder of the Entity instance, and pass that around.  That way, when
 * the Entity is accessed, we can set a dirty flag so we know changes were made.
 * 
 * @author nwalczak
 *
 */
public interface EntitySource {

    public Entity getEntity();
}
