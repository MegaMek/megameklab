/**
 * 
 */
package megameklab.com.ui.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Base class that has some common layout methods for main ui component views.
 * 
 * @author Neoancient
 *
 */
public abstract class MainUIView extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8823653930022761924L;
    
    final protected Dimension labelSize = new Dimension(110, 25);
    final protected Dimension controlSize = new Dimension(180, 25);
    final protected Dimension spinnerSize = new Dimension(55, 25);
    final protected Dimension spinnerEditorSize = new Dimension(40, 25);

    public JLabel createLabel(String text, Dimension maxSize) {
        JLabel label = new JLabel(text, SwingConstants.RIGHT);
        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

}
