/*
 * MegaMekLab - Copyright (C) 2010
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

package megameklab.com.ui.BattleArmor.tabs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.EntityWeightClass;
import megamek.common.TechConstants;
import megameklab.com.ui.BattleArmor.views.CriticalView;
import megameklab.com.util.ITab;
import megameklab.com.util.ImageHelper;
import megameklab.com.util.ImagePanel;
import megameklab.com.util.RefreshListener;
import megameklab.com.util.SpringLayoutHelper;

public class StructureTab extends ITab implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = -6756011847500605874L;

    JComboBox walkMP;
    JComboBox weightClass;
    String[] techTypes =
        { "I.S.", "Clan", "Mixed I.S.", "Mixed Clan" };
    JComboBox techType = new JComboBox(techTypes);
    String[] isTechLevels =
        { "Intro", "Standard", "Advanced", "Experimental", "Unoffical" };
    String[] clanTechLevels =
        { "Standard", "Advanced", "Experimental", "Unoffical" };
    JComboBox techLevel = new JComboBox(isTechLevels);
    JTextField era = new JTextField(3);
    JTextField source = new JTextField(3);
    RefreshListener refresh = null;
    Dimension maxSize = new Dimension();
    JPanel masterPanel;

    private CriticalView critView = null;
    private ImagePanel unitImage = null;
    private JButton browseButton = null;

    public StructureTab(BattleArmor unit) {
        JScrollPane scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().setUnitIncrement(20);

        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, enginePanel(), scroll);

        this.unit = unit;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        // this.add(enginePanel());
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.X_AXIS));
        critView = new CriticalView(getBattleArmor(), false, refresh);

        JPanel imagePanel = new JPanel();

        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        unitImage = new ImagePanel(getBattleArmor(), ImageHelper.imageBattleArmor);
        browseButton = new JButton("Browse");
        browseButton.addActionListener(this);

        imagePanel.add(unitImage);
        imagePanel.add(browseButton);

        scrollPanel.add(critView);
        scrollPanel.add(imagePanel);
        scroll.setViewportView(scrollPanel);

        this.add(splitter);
        refresh();
    }

    public JPanel enginePanel() {
        masterPanel = new JPanel(new SpringLayout());

        Vector<String> walkMPs = new Vector<String>(26, 1);

        for (int pos = 1; pos <= 25; pos++) {
            walkMPs.add(Integer.toString(pos));
        }

        walkMP = new JComboBox(walkMPs.toArray());

        Vector<String> weightClasses = new Vector<String>(1, 1);

        for (int weight = 0; weight < 5; weight++) {
            weightClasses.add(EntityWeightClass.getClassName(weight));
        }

        weightClass = new JComboBox(weightClasses.toArray());

        maxSize.setSize(110, 20);

        masterPanel.add(createLabel("Year:", maxSize));
        masterPanel.add(era);

        masterPanel.add(createLabel("Source/Era:", maxSize));
        masterPanel.add(source);

        masterPanel.add(createLabel("Tech:", maxSize));
        masterPanel.add(techType);
        masterPanel.add(createLabel("Tech Level:", maxSize));
        masterPanel.add(techLevel);

        masterPanel.add(createLabel("Walk MP:", maxSize));
        masterPanel.add(walkMP);
        masterPanel.add(createLabel("Weight:", maxSize));
        masterPanel.add(weightClass);

        setFieldSize(walkMP, maxSize);
        setFieldSize(era, maxSize);
        setFieldSize(source, maxSize);
        setFieldSize(techType, maxSize);
        setFieldSize(techLevel, maxSize);
        setFieldSize(weightClass, maxSize);

        SpringLayoutHelper.setupSpringGrid(masterPanel, 2);

        masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        return masterPanel;
    }

    public void refresh() {
        removeAllActionListeners();
        era.setText(Integer.toString(getBattleArmor().getYear()));
        source.setText(getBattleArmor().getSource());

        if (getBattleArmor().isOmni()) {
            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        } else {
            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        }

        if (getBattleArmor().isClan()) {
            techLevel.removeAllItems();
            for (String item : clanTechLevels) {
                techLevel.addItem(item);
            }
        } else {
            techLevel.removeAllItems();
            for (String item : isTechLevels) {
                techLevel.addItem(item);
            }
        }

        if (getBattleArmor().isMixedTech()) {
            if (getBattleArmor().isClan()) {

                techType.setSelectedIndex(3);
                if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                    techLevel.setSelectedIndex(3);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(2);
                } else {
                    techLevel.setSelectedIndex(1);
                }
            } else {
                techType.setSelectedIndex(2);

                if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                    techLevel.setSelectedIndex(4);
                } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                    techLevel.setSelectedIndex(3);
                } else {
                    techLevel.setSelectedIndex(2);
                }
            }
        } else if (getBattleArmor().isClan()) {

            techType.setSelectedIndex(1);
            if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_UNOFFICIAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_EXPERIMENTAL) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_CLAN_ADVANCED) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }
        } else {
            techType.setSelectedIndex(0);

            if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_UNOFFICIAL) {
                techLevel.setSelectedIndex(4);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_EXPERIMENTAL) {
                techLevel.setSelectedIndex(3);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_ADVANCED) {
                techLevel.setSelectedIndex(2);
            } else if (getBattleArmor().getTechLevel() >= TechConstants.T_IS_TW_NON_BOX) {
                techLevel.setSelectedIndex(1);
            } else {
                techLevel.setSelectedIndex(0);
            }

        }

        walkMP.setSelectedIndex(getBattleArmor().getWalkMP() - 1);

        critView.updateUnit(getBattleArmor());
        critView.refresh();

        unitImage.updateUnit(getBattleArmor());
        unitImage.refresh();

        addAllActionListeners();

    }

    public JLabel createLabel(String text, Dimension maxSize) {

        JLabel label = new JLabel(text, SwingConstants.TRAILING);

        setFieldSize(label, maxSize);
        return label;
    }

    public void setFieldSize(JComponent box, Dimension maxSize) {
        box.setPreferredSize(maxSize);
        box.setMaximumSize(maxSize);
        box.setMinimumSize(maxSize);
    }

    public void actionPerformed(ActionEvent e) {

        if ((e.getSource() instanceof JButton) && e.getSource().equals(browseButton)) {
            FileDialog fDialog = new FileDialog(new JFrame(), "Image Path", FileDialog.LOAD);

            if (getBattleArmor().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(getBattleArmor().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "BattleArmor" + File.separatorChar);
                fDialog.setFile(getBattleArmor().getChassis() + " " + getBattleArmor().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(this);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                getBattleArmor().getFluff().setMMLImagePath(relativeFilePath);
                refresh();
            }
            return;
        }

        if (e.getSource() instanceof JComboBox) {
            JComboBox combo = (JComboBox) e.getSource();
            removeAllActionListeners();

            try {
                // we need to do cockpit also here, because cockpitType
                // determines
                // if a BattleArmor is primitive and thus needs a larger engine
                if (combo.equals(weightClass)) {

                } else if (combo.equals(techLevel)) {
                    int unitTechLevel = techLevel.getSelectedIndex();

                    if (getBattleArmor().isClan()) {
                        switch (unitTechLevel) {
                            case 0:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                addAllActionListeners();
                                techType.setSelectedIndex(1);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                                break;
                            case 2:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_EXPERIMENTAL);
                                break;
                            case 3:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_UNOFFICIAL);
                                break;
                            default:
                                getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                                break;
                        }

                    } else {
                        switch (unitTechLevel) {
                            case 0:
                                getBattleArmor().setTechLevel(TechConstants.T_INTRO_BOXSET);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 1:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_TW_NON_BOX);
                                addAllActionListeners();
                                techType.setSelectedIndex(0);
                                removeAllActionListeners();
                                break;
                            case 2:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                                break;
                            case 3:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
                                break;
                            default:
                                getBattleArmor().setTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                getBattleArmor().setArmorTechLevel(TechConstants.T_IS_UNOFFICIAL);
                                break;
                        }

                    }

                    refresh.refreshArmor();
                    refresh.refreshEquipment();
                    refresh.refreshWeapons();
                    addAllActionListeners();
                    return;
                } else if (combo.equals(techType)) {
                    if ((techType.getSelectedIndex() == 1) && (!getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }

                        getBattleArmor().setTechLevel(TechConstants.T_CLAN_TW);
                        getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_TW);
                        getBattleArmor().setMixedTech(false);
                    } else if ((techType.getSelectedIndex() == 0) && (getBattleArmor().isClan() || getBattleArmor().isMixedTech())) {
                        techLevel.removeAllItems();

                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }

                        getBattleArmor().setTechLevel(TechConstants.T_INTRO_BOXSET);
                        getBattleArmor().setArmorTechLevel(TechConstants.T_INTRO_BOXSET);
                        getBattleArmor().setMixedTech(false);

                    } else if ((techType.getSelectedIndex() == 2) && (!getBattleArmor().isMixedTech() || getBattleArmor().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : isTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to advanced if
                        // we're not already experimental or unofficial
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_IS_EXPERIMENTAL) && (getBattleArmor().getTechLevel() != TechConstants.T_IS_UNOFFICIAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_IS_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_IS_ADVANCED);
                        }
                        getBattleArmor().setMixedTech(true);

                    } else if ((techType.getSelectedIndex() == 3) && (!getBattleArmor().isMixedTech() || !getBattleArmor().isClan())) {
                        techLevel.removeAllItems();
                        for (String item : clanTechLevels) {
                            techLevel.addItem(item);
                        }
                        // only set techlevel and armor techlevel to advanced if
                        // we're not already experimental or unofficial
                        if ((getBattleArmor().getTechLevel() != TechConstants.T_CLAN_EXPERIMENTAL) && (getBattleArmor().getTechLevel() != TechConstants.T_CLAN_UNOFFICIAL)) {
                            getBattleArmor().setTechLevel(TechConstants.T_CLAN_ADVANCED);
                            getBattleArmor().setArmorTechLevel(TechConstants.T_CLAN_ADVANCED);
                        }
                        getBattleArmor().setMixedTech(true);
                    } else {
                        addAllActionListeners();
                        return;
                    }
                    addAllActionListeners();
                    removeAllActionListeners();
                }
                addAllActionListeners();
                refresh.refreshAll();
            } catch (Exception ex) {
                ex.printStackTrace();
                addAllActionListeners();
            }
        } else if (e.getSource() instanceof JCheckBox) {
            JCheckBox check = (JCheckBox) e.getSource();

            SpringLayoutHelper.setupSpringGrid(masterPanel, 2);
            masterPanel.setVisible(false);
            masterPanel.setVisible(true);
        }
        refresh.refreshAll();

    }

    public void removeSystemCrits(int systemType) {

        for (int loc = 0; loc < getBattleArmor().locations(); loc++) {
            for (int slot = 0; slot < getBattleArmor().getNumberOfCriticals(loc); slot++) {
                CriticalSlot cs = getBattleArmor().getCritical(loc, slot);

                if ((cs == null) || (cs.getType() != CriticalSlot.TYPE_SYSTEM)) {
                    continue;
                }

                if (cs.getIndex() == systemType) {
                    getBattleArmor().setCritical(loc, slot, null);
                }
            }
        }
    }

    public void removeAllActionListeners() {
        weightClass.removeActionListener(this);
        walkMP.removeActionListener(this);
        techLevel.removeActionListener(this);
        techType.removeActionListener(this);
        era.removeKeyListener(this);
        source.removeKeyListener(this);
    }

    public void addAllActionListeners() {
        weightClass.addActionListener(this);
        walkMP.addActionListener(this);
        techLevel.addActionListener(this);
        techType.addActionListener(this);
        era.addKeyListener(this);
        source.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

        if (e.getSource().equals(era)) {
            try {
                getBattleArmor().setYear(Integer.parseInt(era.getText()));
            } catch (Exception ex) {
                getBattleArmor().setYear(2075);
            }
        } else if (e.getSource().equals(source)) {
            getBattleArmor().setSource(source.getText());
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

}
