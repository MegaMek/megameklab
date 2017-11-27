/**
 * 
 */
package megameklab.com.ui.view.listeners;

/**
 * Listener for views used by aerospace units.
 *
 * @author Neoancient
 *
 */
public interface DropshipBuildListener extends AeroVesselBuildListener {

    void tonnageChanged(double tonnage);
    void militaryChanged(boolean military);
    void kfBoomChanged(boolean hasBoom);
    void baseTypeChanged(int type);
    void chassisTypeChanged(int type);
    void siChanged(int si);

}
