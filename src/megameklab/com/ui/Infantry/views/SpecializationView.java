/**
 * 
 */
package megameklab.com.ui.Infantry.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import megamek.common.Infantry;
import megameklab.com.ui.EntitySource;
import megameklab.com.util.IView;
import megameklab.com.util.RefreshListener;

/**
 * View for selecting infantry specializations, including xenoplanetary conditions training (XCT).
 * 
 * @author Neoancient
 *
 */
public class SpecializationView extends IView {

    private RefreshListener refresh;
    
    private JCheckBox[] checks = new JCheckBox[Infantry.NUM_SPECIALIZATIONS];
    
    public SpecializationView(EntitySource eSource) {
        super(eSource);
        
        JPanel specPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.WEST;
        for (int i = 0; i < checks.length; i++) {
            int spec = 1 << i;
            JCheckBox chk = new JCheckBox(Infantry.getSpecializationName(spec));
            chk.setToolTipText(Infantry.getSpecializationTooltip(spec));
            chk.setActionCommand(String.valueOf(spec));
            specPanel.add(chk, gbc);
            checks[i] = chk;
            gbc.gridy++;
        }
        
        add(new JScrollPane(specPanel));
    }
    
    public void refresh() {
        
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

}
