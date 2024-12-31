/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

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
import megameklab.util.UnitUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MegaMekLabTabbedUI extends JFrame implements MenuBarOwner, ChangeListener {
    private List<MegaMekLabMainUI> editors = new ArrayList<>();

    private JTabbedPane tabs = new JTabbedPane();

    private MenuBar menuBar;

    public MegaMekLabTabbedUI(MegaMekLabMainUI... entities) {
        super("MegaMekLab");

                if (entities.length == 0) {
            throw new IllegalArgumentException("At least one entity must be provided");
        }

        tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        for (MegaMekLabMainUI e : entities) {
            addTab(e);

        }
        addNewButton();
        tabs.addChangeListener(this);
        setContentPane(tabs);

        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);

        setTransferHandler(new MMLFileDropTransferHandler(this));


        pack();
        restrictToScrenSize();
        setLocationRelativeTo(null);

        CConfig.getMainUiWindowSize(this).ifPresent(this::setSize);
        CConfig.getMainUiWindowPosition(this).ifPresent(this::setLocation);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitOnWindowClosingListener(this));
        setExtendedState(CConfig.getIntParam(CConfig.GUI_FULLSCREEN));
    }

    public MegaMekLabMainUI currentEditor() {
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if (tabs.getSelectedIndex() == editors.size() - 1) {
            tabs.setTabComponentAt(
                tabs.getSelectedIndex(),
                new ClosableTab(currentEditor().getEntity().getDisplayName(), currentEditor())
            );

            addNewButton();
        }
    }

    public void setTabName(String tabName) {
        tabs.setTabComponentAt(tabs.getSelectedIndex(), new ClosableTab(tabName, currentEditor()) );
    }

    private void addTab(MegaMekLabMainUI editor) {
        editors.add(editor);
        editor.refreshAll();
        editor.setOwner(this);
        tabs.addTab(editor.getEntity().getDisplayName(), editor.getContentPane());
        tabs.setTabComponentAt(tabs.getTabCount() - 1, new ClosableTab(editor.getEntity().getDisplayName(), editor));
    }

    private void addNewButton() {
        var editor = new BMMainUI(false, false);
        editors.add(editor);
        editor.refreshAll();
        editor.setOwner(this);
        tabs.addTab("➕", editor.getContentPane());
        tabs.setTabComponentAt(tabs.getTabCount() - 1, new NewTabButton());
    }

    private void newUnit(long type, boolean primitive, boolean industrial) {
        var oldUi = editors.get(tabs.getSelectedIndex());
        var newUi = UiLoader.getUI(type, primitive, industrial);
        editors.set(tabs.getSelectedIndex(), newUi);
        tabs.setComponentAt(tabs.getSelectedIndex(), newUi.getContentPane());
        tabs.setTabComponentAt(tabs.getSelectedIndex(), new ClosableTab(newUi.getEntity().getDisplayName(), newUi));

        oldUi.dispose();
    }

    @Override
    public void newUnit(long type, boolean primitive) {
        newUnit(type, primitive, false);
    }

    public void addEditor(Entity entity, String filename) {
        addNewButton();
        tabs.setSelectedIndex(tabs.getTabCount() - 2);
        newUnit(UnitUtil.getEditorTypeForEntity(entity), entity.isPrimitive(), entity.isIndustrialMek());
        currentEditor().setEntity(entity, filename);
        currentEditor().reloadTabs();
        currentEditor().refreshAll();
        tabs.setTabComponentAt(tabs.getSelectedIndex(), new ClosableTab(entity.getDisplayName(), currentEditor()));
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

    private static class NewTabButton extends JPanel {
        public NewTabButton() {
            setOpaque(false);
            var label = new JLabel("➕");
            label.setForeground(Color.GREEN);
            add(label);
        }
    }

    private class ClosableTab extends JPanel {
        JLabel unitName;
        JButton closeButton;
        MegaMekLabMainUI editor;

        public ClosableTab(String name, MegaMekLabMainUI mainUI) {
            unitName = new JLabel(name);
            editor = mainUI;

            setOpaque(false);

            closeButton = new JButton("❌");
            closeButton.setForeground(Color.RED);
            closeButton.setFocusable(false);
            closeButton.setBorder(BorderFactory.createEmptyBorder());
            closeButton.setToolTipText("Shift-click to skip the save confirmation dialog");
            add(unitName);
            add(closeButton);
            closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.isShiftDown() || editor.safetyPrompt()) {
                        tabs.remove(editor.getContentPane());
                        if (tabs.getSelectedIndex() == tabs.getTabCount() - 1 && tabs.getTabCount() > 1) {
                            tabs.setSelectedIndex(tabs.getSelectedIndex() - 1);
                        }
                        editors.remove(editor);
                        stateChanged(new ChangeEvent(tabs));
                        editor.dispose();
                    }
                }
            });
        }
    }
}
