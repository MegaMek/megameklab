/*
 * MekWars - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import ui.dialog.UnitViewerDialog;
import ui.tabs.ArmorTab;
import ui.tabs.StructureTab;
import ui.util.RefreshListener;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.BipedMech;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.TechConstants;

public class MainUI extends JFrame implements RefreshListener {

    /**
     * 
     */
    private static final long serialVersionUID = -5836932822468918198L;
    Mech entity = null;
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private ArmorTab armorTab;
    private Header header;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    
    public MainUI() {

        file.setMnemonic('F');
        JMenuItem item = new JMenuItem();

        item.setText("Load");
        item.setMnemonic('L');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        file.add(item);

        file.addSeparator();

        item = new JMenuItem();
        item.setText("Exit");
        item.setMnemonic('x');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExit_actionPerformed(e);
            }
        });
        file.add(item);
        menuBar.add(file);

        /*pane = new JOptionPane(ConfigPane, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, null, null);

        pane.setVisible(false);
        scrollPane = new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);*/
        repaint();
        setLocation(getLocation().x + 10, getLocation().y);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                    System.exit(0);
            }
        });

        //ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewMech();
        reloadTabs();
        setJMenuBar(menuBar);
        this.add(masterPanel);
        masterPanel.setPreferredSize(new Dimension(640,480));
        setResizable(true);
        setSize(new Dimension(640, 480));
        setMinimumSize(new Dimension(640, 480));
        setPreferredSize(new Dimension(640, 480));
        setExtendedState(Frame.NORMAL);

        this.setVisible(true);
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog,0);
        viewer.setVisible(true);

        if (viewer != null) {
            
            if ( !(viewer.getSelectedEntity() instanceof Mech) )
                return;
            entity = (Mech)viewer.getSelectedEntity();
            viewer.setVisible(false);
            reloadTabs();
        }
    }

    public void jMenuLoadEntity_actionPerformed(ActionEvent event) {
        loadUnit();
    }

    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();
        
        masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.Y_AXIS));
        structureTab = new StructureTab(entity);
        armorTab = new ArmorTab(entity);
        header = new Header(entity);
        statusbar = new StatusBar(entity);
        header.addRefreshedListener(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        
        ConfigPane.addTab("Structure", structureTab);
        ConfigPane.addTab("Armor", armorTab);
        
        masterPanel.add(header);
        masterPanel.add(ConfigPane);
        masterPanel.add(statusbar);
        refreshHeader();
        this.repaint();
    }

    public void jMenuExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    public void createNewMech() {
        
        entity = new BipedMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD);

        entity.setYear(2075);
        entity.setTechLevel(TechConstants.T_IS_LEVEL_1);
        entity.setWeight(25);
        entity.setEngine(new Engine(25,Engine.NORMAL_ENGINE,0));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        entity.addGyro();
        entity.addEngineCrits();
        entity.addCockpit();
        entity.addEngineSinks(entity.getEngine().integralHeatSinkCapacity(), false);
        
        entity.autoSetInternal();
        for ( int loc = 0; loc <= Mech.LOC_LLEG; loc++ ) {
            entity.setArmor(0, loc);
            entity.setArmor(0, loc,true);
        }
        
    }

    public void refreshAll() {
        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        
        this.setTitle(entity.getChassis()+" "+entity.getModel()+".mtf");
        header = new Header(entity);
    }

    public void refreshArmor() {
        armorTab.refresh();
    }

    public void refreshBuild() {
        // TODO Auto-generated method stub
        
    }

    public void refreshEquipment() {
        // TODO Auto-generated method stub
        
    }

    public void refreshHeader() {
        this.setTitle(entity.getChassis()+" "+entity.getModel()+".mtf");
    }

    public void refreshStatus() {
        statusbar.refresh();
    }

    public void refreshStructure() {
        structureTab.refresh();
    }

    public void refreshWeapons() {
        // TODO Auto-generated method stub
        
    }

    
}