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

package megameklab.com.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.MegaMek;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.Infantry;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.Tank;
import megamek.common.UnitType;
import megamek.common.loaders.BLKFile;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.dialog.UnitViewerDialog;
import megameklab.com.ui.dialog.UnitViewerDialog;

public class MenuBarCreator extends JMenuBar {

    /**
     *
     */
    private static final long serialVersionUID = -3998342610654551481L;
    private JMenu file = new JMenu("File");
    private JMenu help = new JMenu("Help");
    private JMenu validate = new JMenu("Validate");
    private Entity unit = null;
    private MegaMekLabMainUI parentFrame = null;

    public MenuBarCreator(Entity entity, MegaMekLabMainUI parent) {

        unit = entity;

        parentFrame = parent;

        loadFileMenuOptions();

        JMenuItem item = new JMenuItem();
        item.setText("About");
        item.setMnemonic(KeyEvent.VK_A);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpAbout_actionPerformed();
            }
        });
        help.add(item);

        item = new JMenuItem();
        item.setText("Record Sheet Images");
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuHelpFluff_actionPerformed();
            }
        });
        help.add(item);

        item = new JMenuItem();
        item.setText("Equipment Database");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuCreateEquipmentFile_actionPerformed();
            }
        });
        help.add(item);

        item = new JMenuItem();
        item.setText("Insert Image To File");
        item.setMnemonic(KeyEvent.VK_I);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuInsertImageFile_actionPerformed();
            }
        });
        help.add(item);

        validate.add(loadBVMenuOptions());

        validate.add(loadValidateMenuOptions());

        validate.add(loadSpecsMenuOptions());

        validate.add(loadUnitCostBreakdownMenuOptions());

        this.add(file);
        this.add(validate);
        this.add(help);

    }

    private JMenu loadBVMenuOptions() {
        JMenu bv = new JMenu("BV Calculations");
        JMenuItem item = new JMenuItem();
        item.setText("Current Units BV Calculations");
        item.setMnemonic(KeyEvent.VK_B);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuBVCalculations_actionPerformed();
            }
        });
        bv.add(item);

        item = new JMenuItem();
        item.setText("BV Calculations From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBVFromFile_actionPerformed();
            }
        });
        bv.add(item);

        item = new JMenuItem();
        item.setText("BV Calculations From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBVFromCache_actionPerformed();
            }
        });
        bv.add(item);
        return bv;
    }

    private JMenu loadValidateMenuOptions() {
        JMenu entityValidation = new JMenu("Unit Validation");
        JMenuItem item = new JMenuItem();
        item.setText("Validate Current Unit");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuValidateUnit_actionPerformed();
            }
        });
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText("Validate Unit From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitValidationFromFile_actionPerformed();
            }
        });
        entityValidation.add(item);

        item = new JMenuItem();
        item.setText("Validate Unit From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitValidationFromCache_actionPerformed();
            }
        });
        entityValidation.add(item);
        return entityValidation;
    }

    private JMenu loadUnitCostBreakdownMenuOptions() {
        JMenu entityBreakdown = new JMenu("Unit Cost Breakdown");
        JMenuItem item = new JMenuItem();
        item.setText("Breakdown Current Unit");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuUnitCostBreakdown_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBreakdownFromFile_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitBreakdownFromCache_actionPerformed();
            }
        });
        entityBreakdown.add(item);
        return entityBreakdown;
    }

    private JMenu loadSpecsMenuOptions() {
        JMenu unitSpecs = new JMenu("Unit Specs");
        JMenuItem item = new JMenuItem();
        item.setText("Current Unit Specs");
        item.setMnemonic(KeyEvent.VK_V);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuUnitSpecs_actionPerformed();
            }
        });
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText("Unit Specs From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitSpecsFromFile_actionPerformed();
            }
        });
        unitSpecs.add(item);

        item = new JMenuItem();
        item.setText("Unit Specs From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitSpecsFromCache_actionPerformed();
            }
        });
        unitSpecs.add(item);
        return unitSpecs;
    }

    private void loadFileMenuOptions() {

        file.removeAll();

        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem();

        item = new JMenuItem("Reset Current Unit");
        item.setMnemonic(KeyEvent.VK_R);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuResetEntity_actionPerformed(e);
            }
        });
        file.add(item);

        JMenu unitMenu = new JMenu("Switch Unit Type");
        unitMenu.setMnemonic(KeyEvent.VK_S);

        if (!(unit instanceof Mech)) {
            item = new JMenuItem();
            item.setText("Mech");
            item.setMnemonic(KeyEvent.VK_M);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadMech();
                }

            });
            unitMenu.add(item);
        }

        if (!(unit instanceof Tank)) {
            item = new JMenuItem();
            item.setText("Tank");
            item.setMnemonic(KeyEvent.VK_T);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadVehicle();
                }

            });
            unitMenu.add(item);
        }

        if (!(unit instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText("BattleArmor");
            item.setMnemonic(KeyEvent.VK_A);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadBattleArmor();
                }

            });
            unitMenu.add(item);
        }
        
        if (!(unit instanceof Infantry) || (unit instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText("Infantry");
            item.setMnemonic(KeyEvent.VK_I);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadInfantry();
                }

            });
            unitMenu.add(item);
        }
        
        file.add(unitMenu);

        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic(KeyEvent.VK_L);
        item = new JMenuItem();

        item.setText("From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        item = new JMenuItem();
        item.setText("From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntityFromFile_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        file.add(loadMenu);

        item = new JMenuItem(String.format("Current Unit"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuPrintCurrentUnit();
            }
        });

        file.add(UnitPrintManager.printMenu(parentFrame, item));

        item = new JMenuItem();
        item.setText("Save");
        item.setMnemonic(KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem();
        item.setText("Save As");
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveAsEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Configuration");
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuConfiguration_actionPerformed(e);
            }
        });
        file.add(item);

        int fileNumber = 1;
        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1).length() > 1) {
            file.addSeparator();
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(1);
                }
            });

            file.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(2);
                }
            });

            file.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(3);
                }
            });

            file.add(item);
            fileNumber++;
        }

        if (CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4).length() > 1) {
            item = new JMenuItem();
            String newFile = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4);
            if (newFile.length() > 35) {
                item.setText(fileNumber + ". .." + newFile.substring(newFile.length() - 36));
            } else {
                item.setText(fileNumber + ". " + newFile);
            }
            item.setMnemonic(fileNumber);
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadEntityFromFile_actionPerformed(4);
                }
            });

            file.add(item);
            fileNumber++;
        }

        file.addSeparator();

        item = new JMenuItem();
        item.setText("Exit");
        item.setMnemonic(KeyEvent.VK_X);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExit_actionPerformed(e);
            }
        });
        file.add(item);

    }

    private void jMenuGetUnitBVFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        tempEntity.calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(tempEntity.getBVText(), parentFrame);

    }

    private void jMenuGetUnitValidationFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showValidation(tempEntity, parentFrame);

    }

    private void jMenuGetUnitSpecsFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitSpecs(tempEntity, parentFrame);

    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.MEK);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitCostBreakDown(tempEntity, parentFrame);

    }

    private void jMenuGetUnitBVFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parentFrame);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            tempEntity.calculateBattleValue(true, true);
            UnitUtil.showBVCalculations(tempEntity.getBVText(), parentFrame);
        }

    }

    private void jMenuGetUnitValidationFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parentFrame);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showValidation(tempEntity, parentFrame);
        }
    }

    private void jMenuGetUnitBreakdownFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parentFrame);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showUnitCostBreakDown(tempEntity, parentFrame);
        }
    }

    private void jMenuGetUnitSpecsFromFile_actionPerformed() {

        Entity tempEntity = null;
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";
        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
        f.setDialogTitle("Choose Unit");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parentFrame);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            tempEntity = new MechFileParser(unitFile).getEntity();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        } finally {
            UnitUtil.showUnitSpecs(tempEntity, parentFrame);
        }
    }

    private void jMenuInsertImageFile_actionPerformed() {

        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

        File unitFile = new File(filePathName);
        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
        f.setDialogTitle("Load Mech");
        f.setDialogType(JFileChooser.OPEN_DIALOG);
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parentFrame);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        unitFile = f.getSelectedFile();

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            if (UnitUtil.validateUnit(unit).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame, "Warning:Invalid unit, it might load incorrectly!");
            }

            FileDialog fDialog = new FileDialog(parentFrame, "Image Path", FileDialog.LOAD);

            if (unit.getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(unit.getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "mech" + File.separatorChar);
                fDialog.setFile(unit.getChassis() + " " + unit.getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                unit.getFluff().setMMLImagePath(relativeFilePath);
                BLKFile.encode(unitFile.getAbsolutePath(), tempEntity);
            }
        } catch (Exception ex) {

        }
        return;
    }

    // Show BV Calculations

    public void jMenuBVCalculations_actionPerformed() {
        unit.calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(unit.getBVText(), parentFrame);
    }

    public void jMenuUnitCostBreakdown_actionPerformed() {
        UnitUtil.showUnitCostBreakDown(unit, parentFrame);
    }

    public void jMenuUnitSpecs_actionPerformed() {
        UnitUtil.showUnitSpecs(unit, parentFrame);
    }

    // Show Validation data.
    public void jMenuValidateUnit_actionPerformed() {
        UnitUtil.showValidation(unit, parentFrame);
    }

    // Show data about MegaMekLab
    public void jMenuHelpAbout_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(parentFrame, "MegaMekLab Info");

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JLabel mekwars = new JLabel("MegaMekLab Version: " + MegaMekLab.VERSION);
        JLabel version = new JLabel("MegaMek Version: " + MegaMek.VERSION);
        JLabel license1 = new JLabel("MegaMekLab software is under GPL. See");
        JLabel license2 = new JLabel("license.txt in ./Docs/licenses for details.");
        JLabel license3 = new JLabel("Project Info:");
        JLabel license4 = new JLabel("       http://www.sourceforge.net/projects/megameklab       ");

        // center everything
        mekwars.setAlignmentX(Component.CENTER_ALIGNMENT);
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        license1.setAlignmentX(Component.CENTER_ALIGNMENT);
        license2.setAlignmentX(Component.CENTER_ALIGNMENT);
        license3.setAlignmentX(Component.CENTER_ALIGNMENT);
        license4.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(new JLabel("\n"));
        child.add(mekwars);
        child.add(version);
        child.add(new JLabel("\n"));
        child.add(license1);
        child.add(license2);
        child.add(new JLabel("\n"));
        child.add(license3);
        child.add(license4);
        child.add(new JLabel("\n"));

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);

        // set the location of the dialog
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    // Show how to create fluff images for Record Sheets
    public void jMenuHelpFluff_actionPerformed() {

        // make the dialog
        JDialog dlg = new JDialog(parentFrame, "Image Help");

        // set up the contents
        JPanel child = new JPanel();
        child.setLayout(new BoxLayout(child, BoxLayout.Y_AXIS));

        // set the text up.
        JTextArea recordSheetImageHelp = new JTextArea();

        recordSheetImageHelp.setEditable(false);

        recordSheetImageHelp.setText("To add a fluff image to a record sheet the following steps need to be taken\nPlease Note that currently only \'Mechs use fluff Images\nPlace the image you want to use in the data/images/fluff folder\nMegaMekLab will attempt to match the name of the \'Mech your are printing\nwith the images in the fluff folder.\nThe following is an example of how MegaMekLab look for the image\nExample\nYour \'Mech is called Archer ARC-7Q\nMegaMekLab would look for the following\n\nArcher ARC-7Q.jpg/png/gif\nARC-7Q.jpg/png/gif\nArcher.jpg/png/gif\nhud.png\n");
        // center everything
        recordSheetImageHelp.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add to child panel
        child.add(recordSheetImageHelp);

        // then add child panel to the content pane.
        dlg.getContentPane().add(child);

        // set the location of the dialog
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    public void jMenuConfiguration_actionPerformed(ActionEvent event) {
        new ConfigurationDialog();
    }

    public void jMenuExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    private void jMenuLoadVehicle() {
        new megameklab.com.ui.Vehicle.MainUI();
        parentFrame.dispose();
    }

    private void jMenuLoadBattleArmor() {
        new megameklab.com.ui.BattleArmor.MainUI();
        parentFrame.dispose();
    }

    private void jMenuLoadMech() {
        new megameklab.com.ui.Mek.MainUI();
        parentFrame.dispose();
    }
    
    private void jMenuLoadInfantry() {
    	new megameklab.com.ui.Infantry.MainUI();
        parentFrame.dispose();
    }

    private void jMenuPrintCurrentUnit() {
        UnitPrintManager.printEntity(unit);
    }

    public void jMenuLoadEntity_actionPerformed(ActionEvent event) {
        loadUnit();
    }

    public void jMenuLoadEntityFromFile_actionPerformed(ActionEvent event) {
        loadUnitFromFile();
    }

    public void jMenuLoadEntityFromFile_actionPerformed(int fileNumber) {
        loadUnitFromFile(fileNumber);
    }

    public void jMenuResetEntity_actionPerformed(ActionEvent event) {
        CConfig.updateSaveFiles("Reset Unit");
        CConfig.setParam(CConfig.CONFIG_SAVE_FILE_1, "");

        parentFrame.createNewUnit(false);
        unit = parentFrame.getEntity();
        setVisible(true);
        reload();
        refresh();
        parentFrame.setVisible(true);
        parentFrame.repaint();
    }

    public void jMenuSaveEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(unit).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        String unitName = unit.getChassis() + " " + unit.getModel();
        UnitUtil.compactCriticals(unit);
        UnitUtil.reIndexCrits(unit);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if ((filePathName.trim().length() < 1) || !filePathName.contains(unitName)) {
            FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

            filePathName = new File(System.getProperty("user.dir").toString() + "/data/mechfiles/").getAbsolutePath();

            fDialog.setDirectory(filePathName);
            fDialog.setFile(unitName + (unit instanceof Mech?".mtf":".blk"));
            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
            } else {
                return;
            }
        }
        try {
            if (unit instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) unit).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, unit);
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame, unit.getChassis() + " " + unit.getModel() + " saved to " + filePathName);

    }

    public void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(unit).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        UnitUtil.compactCriticals(unit);
        UnitUtil.reIndexCrits(unit);

        FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

        String filePathName = new File(System.getProperty("user.dir").toString() + "/data/mechfiles/").getAbsolutePath();

        fDialog.setDirectory(filePathName);
        fDialog.setFile(unit.getChassis() + " " + unit.getModel() + (unit instanceof Mech?".mtf":".blk"));
        fDialog.setLocationRelativeTo(parentFrame);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            if (unit instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) unit).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, unit);
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame, unit.getChassis() + " " + unit.getModel() + " saved to " + filePathName);

    }

    private void loadUnit() {

        if (unit instanceof Mech) {
            UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
            unitLoadingDialog.setVisible(true);
            UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.MEK);

            unit = viewer.getChosenEntity();
            viewer.setVisible(false);
            viewer.dispose();
            
            if(null == unit) {
                return;
            }
            
            CConfig.updateSaveFiles("");
            UnitUtil.updateLoadedMech(unit);
            
            if (viewer.getChosenMechSummary().getSourceFile().getName().endsWith(".zip")) {
                String fileName = viewer.getChosenMechSummary().getSourceFile().getAbsolutePath();
                fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
                fileName = fileName + viewer.getChosenMechSummary().getName() + ".mtf";
                CConfig.updateSaveFiles(fileName);
            } else {
                CConfig.updateSaveFiles(viewer.getChosenMechSummary().getSourceFile().getAbsolutePath());
            }
        } else if (unit instanceof Tank) {
            UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
            unitLoadingDialog.setVisible(true);
            UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.TANK);
            
            unit = viewer.getChosenEntity();
            viewer.setVisible(false);
            viewer.dispose();
            
            if(null == unit) {
                return;
            }
            
            if (!(unit instanceof Tank)) {
                return;
            }
        } else if (unit instanceof BattleArmor) {
            UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
            unitLoadingDialog.setVisible(true);
            UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.BATTLE_ARMOR);

            unit = viewer.getChosenEntity();
            
            viewer.setVisible(false);
            viewer.dispose();
            
            if(null == unit) {
                return;
            }
            
            if (!(unit instanceof BattleArmor)) {
                return;
            }            
        }
        else if (unit instanceof Infantry) {
            UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
            unitLoadingDialog.setVisible(true);
            UnitViewerDialog viewer = new UnitViewerDialog(parentFrame, unitLoadingDialog, UnitType.INFANTRY);

            unit = viewer.getChosenEntity();
            viewer.setVisible(false);
            viewer.dispose();
            
            if(null == unit) {
                return;
            }
            
            if (!(unit instanceof Infantry)) {
                return;
            }       
        }
        parentFrame.setEntity(unit);
        reload();
        refresh();
        parentFrame.setVisible(true);
    }

    private void loadUnitFromFile() {
        loadUnitFromFile(-1);
    }

    private void loadUnitFromFile(int fileNumber) {

        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

        if (fileNumber > 0) {
            switch (fileNumber) {
                case 1:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);
                    break;
                case 2:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_2);
                    break;
                case 3:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_3);
                    break;
                case 4:
                    filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_4);
                    break;
            }
        }

        File unitFile = new File(filePathName);
        if (!(unitFile.isFile())) {
            JFileChooser f = new JFileChooser(filePathName);
            f.setLocation(parentFrame.getLocation().x + 150, parentFrame.getLocation().y + 100);
            f.setDialogTitle("Load Mech");
            f.setDialogType(JFileChooser.OPEN_DIALOG);
            f.setMultiSelectionEnabled(false);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf", "hmp");

            // Add a filter for mul files
            f.setFileFilter(filter);

            int returnVal = f.showOpenDialog(parentFrame);
            if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
                // I want a file, y'know!
                return;
            }
            unitFile = f.getSelectedFile();
        }

        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();

            unit = tempEntity;
            UnitUtil.updateLoadedMech(unit);
            if (UnitUtil.validateUnit(unit).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame, "Warning:Invalid unit, it might load incorrectly!");
            }

            CConfig.updateSaveFiles(unitFile.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format("Warning:Invalid unit, it might load incorrectly!\n%1$s", ex.getMessage()));
        }
        parentFrame.setEntity(unit);
        reload();
        refresh();
        parentFrame.setVisible(true);
    }

    private void refresh() {
        parentFrame.refreshAll();
    }

    private void reload() {
        parentFrame.reloadTabs();
    }

    private void jMenuCreateEquipmentFile_actionPerformed() {
       // new EquipmentViewerDialog(parentFrame);
    }

    public void setUnit(Entity unit) {
        this.unit = unit;
    }

}