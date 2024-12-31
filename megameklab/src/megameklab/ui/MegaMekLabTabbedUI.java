package megameklab.ui;

import megamek.MegaMek;
import megamek.client.ui.swing.util.UIUtil;
import megamek.common.Entity;
import megamek.common.preference.PreferenceManager;
import megameklab.MMLConstants;
import megameklab.MegaMekLab;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.mek.BMMainUI;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MegaMekLabTabbedUI extends JFrame implements MenuBarOwner {
    private List<MegaMekLabMainUI> editors = new ArrayList<>();

    private JTabbedPane tabs = new JTabbedPane();

    private MenuBar menuBar;

    public MegaMekLabTabbedUI(MegaMekLabMainUI... entities) {
        super("MegaMekLab");

                if (entities.length == 0) {
            throw new IllegalArgumentException("At least one entity must be provided");
        }

        for (MegaMekLabMainUI e : entities) {
            addTab(e);

        }
        addTab(new BMMainUI(false, false), "+");
        tabs.addChangeListener(new TabsChangedListener());
        setContentPane(tabs);

        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        setTransferHandler(new MMLFileDropTransferHandler(this));


        pack();
        setSize(800, 600); // todo remember window size
        restrictToScrenSize();
        setLocationRelativeTo(null);

        CConfig.getMainUiWindowSize(this).ifPresent(this::setSize);
        CConfig.getMainUiWindowPosition(this).ifPresent(this::setLocation);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitOnWindowClosingListener(this));
        setExtendedState(CConfig.getIntParam(CConfig.GUI_FULLSCREEN));
    }

    private MegaMekLabMainUI currentEditor() {
        return editors.get(tabs.getSelectedIndex());
    }

    @Override
    public JFrame getFrame() {
        return this;
    }

    @Override
    public Entity getEntity() {
        return currentEditor().getEntity();
    }

    @Override
    public String getFileName() {
        return currentEditor().getFileName();
    }

    @Override
    public boolean hasEntityNameChanged() {
        return currentEditor().hasEntityNameChanged();
    }

    @Override
    public void refreshMenuBar() {
        menuBar.refreshMenuBar();
    }

    @Override
    public MenuBar getMMLMenuBar() {
        return menuBar;
    }

    private class TabsChangedListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (tabs.getSelectedIndex() == editors.size() - 1) {
                tabs.setTitleAt(tabs.getSelectedIndex(), editors.get(tabs.getSelectedIndex()).getEntity().getDisplayName());

                addTab(new BMMainUI(false, false), "+");
            }
        }
    }

    public void setTabName(String tabName) {
        tabs.setTitleAt(tabs.getSelectedIndex(), tabName);
    }

    private void addTab(MegaMekLabMainUI editor) {
        addTab(editor, editor.getEntity().getDisplayName());
    }

    private void addTab(MegaMekLabMainUI editor, String name) {
        editors.add(editor);
        editor.refreshAll();
        editor.setOwner(this);
        tabs.addTab(name, editor.getContentPane());
    }

    @Override
    public void newUnit(long type, boolean primitive) {
        var oldUi = editors.get(tabs.getSelectedIndex());
        var newUi = UiLoader.getUI(type, primitive, true);
        editors.set(tabs.getSelectedIndex(), newUi);
        tabs.setComponentAt(tabs.getSelectedIndex(), newUi.getContentPane());
        tabs.setTitleAt(tabs.getSelectedIndex(), newUi.getEntity().getDisplayName());

        oldUi.dispose();
    }

    public void addEditor(Entity entity, String filename) {
        var newUi = UiLoader.getUI(entity.getUnitType(), entity.isPrimitive(), entity.isIndustrialMek());
        newUi.setEntity(entity, filename);
        newUi.reloadTabs();
        newUi.refreshAll();
        tabs.setSelectedIndex(tabs.getTabCount() - 1);
        var oldUi = editors.get(tabs.getSelectedIndex());
        editors.set(tabs.getSelectedIndex(), newUi);
        tabs.setComponentAt(tabs.getSelectedIndex(), newUi.getContentPane());
        tabs.setTitleAt(tabs.getSelectedIndex(), newUi.getEntity().getDisplayName());
        oldUi.dispose();
    }

    @Override
    public boolean exit() {
        if (!currentEditor().safetyPrompt()) {
            return false;
        }

        CConfig.setParam(CConfig.GUI_FULLSCREEN, Integer.toString(getExtendedState()));
        CConfig.setParam(CConfig.GUI_PLAF, UIManager.getLookAndFeel().getClass().getName());
        CConfig.writeMainUiWindowSettings(this);
        CConfig.saveConfig();
        PreferenceManager.getInstance().save();
        MegaMek.getMMPreferences().saveToFile(MMLConstants.MM_PREFERENCES_FILE);
        MegaMekLab.getMMLPreferences().saveToFile(MMLConstants.MML_PREFERENCES_FILE);
        return true;
    }

    private void restrictToScrenSize() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        int scaledMonitorW = UIUtil.getScaledScreenWidth(currentMonitor);
        int scaledMonitorH = UIUtil.getScaledScreenHeight(currentMonitor);
        int w = Math.min(getSize().width, scaledMonitorW);
        int h = Math.min(getSize().height, scaledMonitorH);
        setSize(new Dimension(w, h));
    }
}
