package megameklab.com.ui.dialog;

import megamek.client.ui.baseComponents.AbstractDialog;
import megamek.common.util.EncodeControl;
import megameklab.com.MegaMekLab;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * This is the base class for dialogs in MML, inherited from MM. This class handles setting the
 * UI, managing the X button, managing the escape key, and saving the dialog preferences.
 *
 * Inheriting classes must call initialize() in their constructors and override createCenterPane().
 */
public abstract class AbstractMMLDialog extends AbstractDialog {

    /**
     * This creates a non-modal AbstractMMLDialog using the default MML dialog resource bundle. This is
     * the normal constructor to use for an AbstractMMLDialog.
     */
    protected AbstractMMLDialog(final JFrame frame, final String name, final String title) {
        this(frame, false, name, title);
    }

    /**
     * This creates an AbstractMMLDialog using the default MML resource bundle. It allows one
     * to create modal dialogs.
     */
    protected AbstractMMLDialog(final JFrame frame, final boolean modal, final String name, final String title) {
        this(frame, modal, ResourceBundle.getBundle("megameklab.resources.Dialogs", new EncodeControl()), name, title);
    }

    /**
     * This creates an AbstractMMLDialog using the specified resource bundle. This is not recommended
     * by default.
     */
    protected AbstractMMLDialog(final JFrame frame, final boolean modal, final ResourceBundle resources,
                                final String name, final String title) {
        super(frame, modal, resources, name, title);
    }

    /**
     * This override forces the preferences for this class to be tracked in MML instead of MegaMek
     */
    @Override
    protected void setPreferences() {
        setPreferences(MegaMekLab.getPreferences().forClass(getClass()));
    }
}
