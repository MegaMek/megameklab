/**
 * 
 */
package megameklab.com.ui.view;

import megameklab.com.ui.view.listeners.BuildListener;

/**
 * Listener for views used by aerospace units.
 *
 * @author Neoancient
 *
 */
public interface DropshipBuildListener extends BuildListener {

    void tonnageChanged(double tonnage);
    void militaryChanged(boolean military);
    void baseTypeChanged(int type);
    void chassisTypeChanged(int type);
    void siChanged(int si);

}
