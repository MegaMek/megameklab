package megameklab.com.ui.util;

import javax.swing.*;
import java.awt.*;

public class TabScrollPane extends JScrollPane {

    public TabScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        initialize();
    }

    public TabScrollPane(Component view) {
        super(view);
        initialize();
    }

    public TabScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        initialize();
    }

    public TabScrollPane() {
        super();
        initialize();
    }

    private void initialize() {
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        getVerticalScrollBar().setUnitIncrement(20);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
