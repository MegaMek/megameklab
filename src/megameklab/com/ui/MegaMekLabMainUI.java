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
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.MechSummaryCache;
import megameklab.com.MegaMekLab;
import megameklab.com.util.CConfig;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.UnitUtil;

public abstract class MegaMekLabMainUI extends JFrame implements
        RefreshListener, EntitySource {

    /**
     *
     */
    private static final long serialVersionUID = 3971760390511127766L;

    private Entity entity = null;

    public MegaMekLabMainUI() {

        EquipmentType.initializeTypes();
        MechSummaryCache.getInstance();
        UnitUtil.loadFonts();
        new CConfig();
        System.out.println("Starting MegaMekLab version: " + MegaMekLab.VERSION);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            System.out.println("Setting look and feel failed: ");
            e.printStackTrace();
        }

        setLocation(getLocation().x + 10, getLocation().y);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                CConfig.setParam("WINDOWSTATE", Integer.toString(getExtendedState()));
                // Only save position and size if not maximized or minimized.
                if (getExtendedState() == Frame.NORMAL) {
                    CConfig.setParam("WINDOWHEIGHT", Integer.toString(getHeight()));
                    CConfig.setParam("WINDOWWIDTH", Integer.toString(getWidth()));
                    CConfig.setParam("WINDOWLEFT", Integer.toString(getX()));
                    CConfig.setParam("WINDOWTOP", Integer.toString(getY()));
                }
                CConfig.saveConfig();

                String quitMsg = "Do you really want to quit MegaMekLab?"; 
                int response = JOptionPane.showConfirmDialog(null, quitMsg,
                        "Quit?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE); 
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        Dimension maxSize = new Dimension(CConfig.getIntParam("WINDOWWIDTH"), CConfig.getIntParam("WINDOWHEIGHT"));
        // masterPanel.setPreferredSize(new Dimension(600,400));
        // scroll.setPreferredSize(maxSize);
        setResizable(true);
        setSize(maxSize);
        setPreferredSize(maxSize);
        setExtendedState(CConfig.getIntParam("WINDOWSTATE"));
        setLocation(CConfig.getIntParam("WINDOWLEFT"), CConfig.getIntParam("WINDOWTOP"));
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