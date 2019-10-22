/*
 * MegaMekLab - Copyright (C) 2011
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */

package megameklab.com.ui;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megameklab.com.MegaMekLab;
import megameklab.com.util.CConfig;
import megameklab.com.util.MenuBarCreator;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public abstract class MegaMekLabMainUI extends JFrame implements
        RefreshListener, EntitySource {

    /**
     *
     */
    private static final long serialVersionUID = 3971760390511127766L;

    private Entity entity = null;
    protected JPanel masterPanel = new JPanel();
    protected JScrollPane scroll = new JScrollPane();
    protected MenuBarCreator menubarcreator;
    
    public MegaMekLabMainUI() {

        EquipmentType.initializeTypes();
        MechSummaryCache.getInstance();
        new CConfig();
        UnitUtil.loadFonts();
        System.out.println("Starting MegaMekLab version: " + MegaMekLab.VERSION);        
        setLookAndFeel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });
        setResizable(true);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
    }

    protected void finishSetup() {
        
        /* menu bar */
        menubarcreator = new MenuBarCreator(this);
        setJMenuBar(menubarcreator);
        
        /* scroll bar */
        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);

        /* load tabs, resize and make visible */
        reloadTabs();
        setSizeAndLocation();
        setVisible(true);
        repaint();
        refreshAll();
    }
    
    protected void setSizeAndLocation() {
        DisplayMode currentMonitor = getGraphicsConfiguration().getDevice().getDisplayMode();
        
        //figure out size dimensions
        pack();
        int w = getSize().width;
        int h = getSize().height;
        if(currentMonitor.getWidth() < w) {
            w = currentMonitor.getWidth();
        }
        if(currentMonitor.getHeight() < h) {
            h = currentMonitor.getHeight();
        }
        Dimension size = new Dimension(w, h);
        setSize(size);
        setPreferredSize(size);
        
        //set location - put in center not top left
        int x = (currentMonitor.getWidth()-w)/2;
        int y = (currentMonitor.getHeight()-h)/2;
        setLocation(x, y);
    }
    
    /**
     * Sets the look and feel for the application.
     * 
     * @param plaf The look and feel to use for the application.
     */
    public void changeTheme(LookAndFeelInfo plaf) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(plaf.getClassName());
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this,
                        "Can't change look and feel", "Invalid PLAF",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
    }

    private void setLookAndFeel() {
        try {
            String plaf = CConfig.getParam(CConfig.CONFIG_PLAF, UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(plaf);
        } catch (Exception e) {
            MegaMekLab.getLogger().error(getClass(), "setLookAndFeel()", e);
       }
    }
    
    public void exit() {
        String quitMsg = "Do you really want to quit MegaMekLab?"; 
        int response = JOptionPane.showConfirmDialog(null, quitMsg,
                "Quit?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE); 
        
        if (response == JOptionPane.YES_OPTION) {
            CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
            // Only save position and size if not maximized or minimized.
            if (getExtendedState() == Frame.NORMAL) {
                /*
                 * Not saving this anymore because this is set always to the center of
                 * screen and big enough to fit all the components for a given MainUI
                CConfig.setParam("WINDOWHEIGHT", Integer.toString(getHeight()));
                CConfig.setParam("WINDOWWIDTH", Integer.toString(getWidth()));
                CConfig.setParam("WINDOWLEFT", Integer.toString(getX()));
                CConfig.setParam("WINDOWTOP", Integer.toString(getY()));
                */
            }
            CConfig.setParam(CConfig.CONFIG_PLAF, UIManager.getLookAndFeel().getClass().getName());
            CConfig.saveConfig();

            System.exit(0);
        }
    }
    
    public abstract void reloadTabs();

    public abstract void refreshAll();

    public abstract void refreshArmor();

    public abstract void refreshBuild();

    public abstract void refreshEquipment();

    public abstract void refreshHeader();

    public abstract void refreshStatus();

    public abstract void refreshStructure();

    public abstract void refreshWeapons();

    public abstract void refreshPreview();

    public void setEntity(Entity en) {
        entity = en;
    }

    public Entity getEntity() {
        return entity;
    }

}