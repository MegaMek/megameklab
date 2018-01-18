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
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
import megamek.client.ui.swing.UnitSelectorDialog;
import megamek.common.Aero;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.FixedWingSupport;
import megamek.common.GunEmplacement;
import megamek.common.Infantry;
import megamek.common.Jumpship;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.MechView;
import megamek.common.SmallCraft;
import megamek.common.Tank;
import megamek.common.loaders.BLKFile;
import megameklab.com.MegaMekLab;
import megameklab.com.ui.MegaMekLabMainUI;

public class MenuBarCreator extends JMenuBar implements ClipboardOwner {

    /**
     *
     */
    private static final long serialVersionUID = -3998342610654551481L;
    private JMenu file = new JMenu("File");
    private JMenu help = new JMenu("Help");
    private JMenu validate = new JMenu("Validate");
    private MegaMekLabMainUI parentFrame = null;

    public MenuBarCreator(MegaMekLabMainUI parent) {

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
        
        validate.add(loadUnitWeightBreakdownMenuOptions());

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
    
    private JMenu loadUnitWeightBreakdownMenuOptions() {
        JMenu entityBreakdown = new JMenu("Unit Weight Breakdown");
        JMenuItem item = new JMenuItem();
        item.setText("Breakdown Current Unit");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuUnitWeightBreakdown_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From File");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitWeightBreakdownFromFile_actionPerformed();
            }
        });
        entityBreakdown.add(item);

        item = new JMenuItem();
        item.setText("Unit Breakdown From Cache");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuGetUnitWeightBreakdownFromCache_actionPerformed();
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
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuResetEntity_actionPerformed(e);
            }
        });
        file.add(item);

        JMenu unitMenu = new JMenu("Switch Unit Type");
        unitMenu.setMnemonic(KeyEvent.VK_S);
        Entity en = parentFrame.getEntity();

        if (!(en instanceof Mech)
                || ((Mech)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText("Mech");
            item.setMnemonic(KeyEvent.VK_M);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadMech());
            unitMenu.add(item);
        }

        if (!(en.isFighter()
                || (en.isFighter() && ((Aero)en).isPrimitive()))) {
            item = new JMenuItem();
            item.setText("Aero/Conv Fighter");
            item.setMnemonic(KeyEvent.VK_A);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadAero();
                }

            });
            unitMenu.add(item);
        }

        if (!(en instanceof SmallCraft)
                || ((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText("Dropship/Small Craft");
            item.setMnemonic(KeyEvent.VK_D);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(e -> jMenuLoadDropship());
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof Tank)) {
            item = new JMenuItem();
            item.setText("Combat Vehicle");
            item.setMnemonic(KeyEvent.VK_T);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadVehicle();
                }

            });
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText("BattleArmor");
            item.setMnemonic(KeyEvent.VK_B);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadBattleArmor();
                }

            });
            unitMenu.add(item);
        }

        if (!(parentFrame.getEntity() instanceof Infantry) || (parentFrame.getEntity() instanceof BattleArmor)) {
            item = new JMenuItem();
            item.setText("Infantry");
            item.setMnemonic(KeyEvent.VK_I);
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                    Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jMenuLoadInfantry();
                }

            });
            unitMenu.add(item);
        }
        
        JMenu pMenu = new JMenu("Primitive/Retro");
        if (!(en instanceof Mech)
                || !((Mech)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText("Mech");
            item.addActionListener(e ->jMenuLoadPrimitiveMech());
            pMenu.add(item);
        }
        
        if (!(en.isFighter())
                || !((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText("Aero");
            item.addActionListener(e ->jMenuLoadPrimitiveAero());
            pMenu.add(item);
        }
        
        if (!(en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT))
                || !((Aero)en).isPrimitive()) {
            item = new JMenuItem();
            item.setText("Dropship/Small Craft");
            item.addActionListener(e ->jMenuLoadPrimitiveDropship());
            pMenu.add(item);
        }
        
        unitMenu.add(pMenu);

        file.add(unitMenu);

        JMenu loadMenu = new JMenu("Load");
        loadMenu.setMnemonic(KeyEvent.VK_L);
        item = new JMenuItem();

        item.setText("From Cache");
        item.setMnemonic(KeyEvent.VK_C);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        item = new JMenuItem();
        item.setText("From File");
        item.setMnemonic(KeyEvent.VK_F);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntityFromFile_actionPerformed(e);
            }
        });
        loadMenu.add(item);

        file.add(loadMenu);

        item = new JMenuItem(String.format("Current Unit"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem();
        item.setText("Save As");
        item.setMnemonic(KeyEvent.VK_A);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuSaveAsEntity_actionPerformed(e);
            }
        });
        file.add(item);

        JMenu exportMenu = new JMenu("Export");

        item = new JMenuItem("to HTML");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExportEntityHTML_actionPerformed(e);
            }
        });
        exportMenu.add(item);

        item = new JMenuItem("to Text");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExportEntityText_actionPerformed(e);
            }
        });
        exportMenu.add(item);

        item = new JMenuItem("to Clipboard (text)");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExportEntityClipboard_actionPerformed(e);
            }
        });
        exportMenu.add(item);

        file.add(exportMenu);

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
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

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
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showValidation(tempEntity, parentFrame);

    }

    private void jMenuGetUnitSpecsFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitSpecs(tempEntity, parentFrame);

    }

    private void jMenuGetUnitBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitCostBreakDown(tempEntity, parentFrame);

    }
    
    private void jMenuGetUnitWeightBreakdownFromCache_actionPerformed() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

        Entity tempEntity = viewer.getChosenEntity();
        if(null == tempEntity) {
            return;
        }
        UnitUtil.showUnitWeightBreakDown(tempEntity, parentFrame);

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
    
    private void jMenuGetUnitWeightBreakdownFromFile_actionPerformed() {

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
            UnitUtil.showUnitWeightBreakDown(tempEntity, parentFrame);
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

            if (UnitUtil.validateUnit(parentFrame.getEntity()).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame, "Warning:Invalid unit, it might load incorrectly!");
            }

            FileDialog fDialog = new FileDialog(parentFrame, "Image Path", FileDialog.LOAD);

            if (parentFrame.getEntity().getFluff().getMMLImagePath().trim().length() > 0) {
                String fullPath = new File(parentFrame.getEntity().getFluff().getMMLImagePath()).getAbsolutePath();
                String imageName = fullPath.substring(fullPath.lastIndexOf(File.separatorChar) + 1);
                fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separatorChar) + 1);
                fDialog.setDirectory(fullPath);
                fDialog.setFile(imageName);
            } else {
                fDialog.setDirectory(new File(ImageHelper.fluffPath).getAbsolutePath() + File.separatorChar + "mech" + File.separatorChar);
                fDialog.setFile(parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel() + ".png");
            }

            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                String relativeFilePath = new File(fDialog.getDirectory() + fDialog.getFile()).getAbsolutePath();
                relativeFilePath = "." + File.separatorChar + relativeFilePath.substring(new File(System.getProperty("user.dir").toString()).getAbsolutePath().length() + 1);
                parentFrame.getEntity().getFluff().setMMLImagePath(relativeFilePath);
                BLKFile.encode(unitFile.getAbsolutePath(), tempEntity);
            }
        } catch (Exception ex) {

        }
        return;
    }

    // Show BV Calculations

    public void jMenuBVCalculations_actionPerformed() {
        parentFrame.getEntity().calculateBattleValue(true, true);
        UnitUtil.showBVCalculations(parentFrame.getEntity().getBVText(), parentFrame);
    }

    public void jMenuUnitCostBreakdown_actionPerformed() {
        UnitUtil.showUnitCostBreakDown(parentFrame.getEntity(), parentFrame);
    }
    
    public void jMenuUnitWeightBreakdown_actionPerformed() {
        UnitUtil.showUnitWeightBreakDown(parentFrame.getEntity(), parentFrame);
    }

    public void jMenuUnitSpecs_actionPerformed() {
        UnitUtil.showUnitSpecs(parentFrame.getEntity(), parentFrame);
    }

    // Show Validation data.
    public void jMenuValidateUnit_actionPerformed() {
        UnitUtil.showValidation(parentFrame.getEntity(), parentFrame);
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
        JLabel license4 = new JLabel("       https://github.com/MegaMek/megameklab       ");

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
        dlg.setLocationRelativeTo(parentFrame);
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
        dlg.setLocationRelativeTo(parentFrame);
        dlg.setModal(true);
        dlg.setResizable(false);
        dlg.pack();
        dlg.setVisible(true);
    }

    public void jMenuConfiguration_actionPerformed(ActionEvent event) {
        new ConfigurationDialog(parentFrame).setVisible(true);
        parentFrame.refreshAll();
    }

    public void jMenuExit_actionPerformed(ActionEvent event) {
        String quitMsg = "Do you really want to quit MegaMekLab?"; 
        int response = JOptionPane.showConfirmDialog(null, quitMsg,
                "Quit?", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE); 
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
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
        new megameklab.com.ui.Mek.MainUI(false, false);
        parentFrame.dispose();
    }
    
    private void jMenuLoadPrimitiveMech() {
        new megameklab.com.ui.Mek.MainUI(true, false);
        parentFrame.dispose();
    }

    private void jMenuLoadAero() {
        new megameklab.com.ui.Aero.MainUI(false);
        parentFrame.dispose();
    }

    private void jMenuLoadPrimitiveAero() {
        new megameklab.com.ui.Aero.MainUI(true);
        parentFrame.dispose();
    }
    
    private void jMenuLoadDropship() {
        new megameklab.com.ui.Dropship.MainUI(false);
        parentFrame.dispose();
    }

    private void jMenuLoadPrimitiveDropship() {
        new megameklab.com.ui.Dropship.MainUI(true);
        parentFrame.dispose();
    }

    private void jMenuLoadInfantry() {
    	new megameklab.com.ui.Infantry.MainUI();
        parentFrame.dispose();
    }

    private void jMenuPrintCurrentUnit() {
        UnitPrintManager.printEntity(parentFrame.getEntity());
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
        Entity en = parentFrame.getEntity();
        if (en instanceof Tank) {
            parentFrame.createNewUnit(Entity.ETYPE_TANK);
        } else if (en instanceof Mech) {
            parentFrame.createNewUnit(Entity.ETYPE_BIPED_MECH, ((Mech)en).isPrimitive(), ((Mech)en).isIndustrial());
        } else if (en.hasETypeFlag(Entity.ETYPE_DROPSHIP)) {
            parentFrame.createNewUnit(Entity.ETYPE_DROPSHIP);
        } else if (en.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
            parentFrame.createNewUnit(Entity.ETYPE_SMALL_CRAFT, ((Aero)en).isPrimitive());
        } else if (parentFrame.getEntity() instanceof Aero) {
            parentFrame.createNewUnit(Entity.ETYPE_AERO, ((Aero)en).isPrimitive());
        } else if (parentFrame.getEntity() instanceof BattleArmor) {
            parentFrame.createNewUnit(Entity.ETYPE_BATTLEARMOR);
        } else if (parentFrame.getEntity() instanceof Infantry) {
            parentFrame.createNewUnit(Entity.ETYPE_INFANTRY);
        } else {
            System.out.println("util.MenuBarCreatoer: " +
                        "Received unknown entityType!");
        }
        setVisible(true);
        reload();
        refresh();
        parentFrame.setVisible(true);
        parentFrame.repaint();
    }

    public void jMenuSaveEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        String unitName = parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel();
        UnitUtil.compactCriticals(parentFrame.getEntity());

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_FILE_1);

        if ((filePathName.trim().length() < 1) || !filePathName.contains(unitName)) {
            FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

            filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

            fDialog.setDirectory(filePathName);
            fDialog.setFile(unitName + (parentFrame.getEntity() instanceof Mech?".mtf":".blk"));
            fDialog.setLocationRelativeTo(parentFrame);

            fDialog.setVisible(true);

            if (fDialog.getFile() != null) {
                filePathName = fDialog.getDirectory() + fDialog.getFile();
                CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
            } else {
                return;
            }
        }
        try {
            if (parentFrame.getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) parentFrame.getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, parentFrame.getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame, parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel() + " saved to " + filePathName);

    }

    public void jMenuSaveAsEntity_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: Saving an invalid unit, it might load incorrectly!");
        }

        UnitUtil.compactCriticals(parentFrame.getEntity());

        FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

        String filePathName = CConfig.getParam(CConfig.CONFIG_SAVE_LOC);

        fDialog.setDirectory(filePathName);
        fDialog.setFile(parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel() + (parentFrame.getEntity() instanceof Mech?".mtf":".blk"));
        fDialog.setLocationRelativeTo(parentFrame);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
            CConfig.setParam(CConfig.CONFIG_SAVE_LOC, fDialog.getDirectory());
        } else {
            return;
        }

        try {
            if (parentFrame.getEntity() instanceof Mech) {
                FileOutputStream out = new FileOutputStream(filePathName);
                PrintStream p = new PrintStream(out);

                p.println(((Mech) parentFrame.getEntity()).getMtf());
                p.close();
                out.close();
            } else {
                BLKFile.encode(filePathName, parentFrame.getEntity());
            }
            CConfig.updateSaveFiles(filePathName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(parentFrame, parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel() + " saved to " + filePathName);

    }

    public void jMenuExportEntityHTML_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: exporting an invalid unit!");
        }

        String unitName = parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel();
        MechView mview = new MechView(parentFrame.getEntity(), false);

        FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

        String filePathName = new File(System.getProperty("user.dir").toString()).getAbsolutePath();

        fDialog.setDirectory(filePathName);
        fDialog.setFile(unitName + ".html");
        fDialog.setLocationRelativeTo(parentFrame);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            FileOutputStream out = new FileOutputStream(filePathName);
            PrintStream p = new PrintStream(out);
            p.println(mview.getMechReadout());
            p.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void jMenuExportEntityText_actionPerformed(ActionEvent event) {

        if (UnitUtil.validateUnit(parentFrame.getEntity()).length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, "Warning: exporting an invalid unit!");
        }

        String unitName = parentFrame.getEntity().getChassis() + " " + parentFrame.getEntity().getModel();
        MechView mview = new MechView(parentFrame.getEntity(), true, true, false);

        FileDialog fDialog = new FileDialog(parentFrame, "Save As", FileDialog.SAVE);

        String filePathName = new File(System.getProperty("user.dir").toString()).getAbsolutePath();

        fDialog.setDirectory(filePathName);
        fDialog.setFile(unitName + ".txt");
        fDialog.setLocationRelativeTo(parentFrame);

        fDialog.setVisible(true);

        if (fDialog.getFile() != null) {
            filePathName = fDialog.getDirectory() + fDialog.getFile();
        } else {
            return;
        }

        try {
            FileOutputStream out = new FileOutputStream(filePathName);
            PrintStream p = new PrintStream(out);
            p.println(mview.getMechReadout());
            p.close();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void jMenuExportEntityClipboard_actionPerformed(ActionEvent event) {
        MechView mview = new MechView(parentFrame.getEntity(), true, true, false);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(mview.getMechReadout());
        clipboard.setContents(stringSelection, this);
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parentFrame);
        unitLoadingDialog.setVisible(true);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parentFrame, unitLoadingDialog, true);

        Entity newUnit = viewer.getChosenEntity();
        viewer.setVisible(false);
        viewer.dispose();

        if (null == newUnit) {
            return;
        }

        if (UnitUtil.validateUnit(newUnit).trim().length() > 0) {
            JOptionPane.showMessageDialog(parentFrame, String.format(
                    "Warning:Invalid unit, it might load incorrectly!\n%1$s",
                            UnitUtil.validateUnit(newUnit)));
        }

        if (newUnit.getEntityType() != parentFrame.getEntity().getEntityType()) {
            MegaMekLabMainUI newUI = null;
            if (newUnit.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                newUI = new megameklab.com.ui.Dropship.MainUI(((Aero)newUnit).isPrimitive());
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_AERO)
                    && !(newUnit.hasETypeFlag(Entity.ETYPE_JUMPSHIP)
                    || newUnit.hasETypeFlag(Entity.ETYPE_FIXED_WING_SUPPORT))) {
                newUI = new megameklab.com.ui.Aero.MainUI(((Aero)newUnit).isPrimitive());
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_BATTLEARMOR)) {
                newUI = new megameklab.com.ui.BattleArmor.MainUI();
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_INFANTRY)) {
                newUI = new megameklab.com.ui.Infantry.MainUI();
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_MECH)) {
                newUI = new megameklab.com.ui.Mek.MainUI();
            } else if (newUnit.hasETypeFlag(Entity.ETYPE_TANK)
                    && !newUnit.hasETypeFlag(Entity.ETYPE_GUN_EMPLACEMENT)) {
                newUI = new megameklab.com.ui.Vehicle.MainUI();
            }
            if (null == newUI) {
                JOptionPane.showMessageDialog(parentFrame,
                        "Warning: Could not create new UI, aborting unit load!"
                        +System.lineSeparator()
                        +"Probable cause: Unsupported unit type.");
                return;
            }
            parentFrame.dispose();
            UnitUtil.updateLoadedUnit(newUnit);
            newUI.setEntity(newUnit);
            newUI.reloadTabs();
            newUI.repaint();
            newUI.refreshAll();
            return;
        }

        CConfig.updateSaveFiles("");
        UnitUtil.updateLoadedUnit(newUnit);

        if (viewer.getChosenMechSummary().getSourceFile().getName().endsWith(".zip")) {
            String fileName = viewer.getChosenMechSummary().getSourceFile().getAbsolutePath();
            fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
            fileName = fileName + viewer.getChosenMechSummary().getName() + ".mtf";
            CConfig.updateSaveFiles(fileName);
        } else {
            CConfig.updateSaveFiles(viewer.getChosenMechSummary().getSourceFile().getAbsolutePath());
        }
        parentFrame.setEntity(newUnit);
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

        loadUnitFromFile(unitFile);
    }

    private void loadUnitFromFile(File unitFile) {
        try {
            Entity tempEntity = new MechFileParser(unitFile).getEntity();


            if (null == tempEntity) {
                return;
            }

            if (UnitUtil.validateUnit(tempEntity).trim().length() > 0) {
                JOptionPane.showMessageDialog(parentFrame, String.format(
                        "Warning:Invalid unit, it might load incorrectly!\n%1$s",
                                UnitUtil.validateUnit(tempEntity)));
            }

            if (tempEntity.getEntityType() != parentFrame.getEntity().getEntityType()) {
                MegaMekLabMainUI newUI = null;
                if (tempEntity.hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)) {
                    newUI = new megameklab.com.ui.Dropship.MainUI(((Aero)tempEntity).isPrimitive());
                } else if ((tempEntity instanceof Aero)
                        && !((tempEntity instanceof Jumpship)
                        || (tempEntity instanceof FixedWingSupport))) {
                    newUI = new megameklab.com.ui.Aero.MainUI(((Aero)tempEntity).isPrimitive());
                } else if (tempEntity instanceof BattleArmor) {
                    newUI = new megameklab.com.ui.BattleArmor.MainUI();
                } else if (tempEntity instanceof Infantry) {
                    newUI = new megameklab.com.ui.Infantry.MainUI();
                } else if (tempEntity instanceof Mech) {
                    newUI = new megameklab.com.ui.Mek.MainUI();
                } else if ((tempEntity instanceof Tank)
                        && !(tempEntity instanceof GunEmplacement)) {
                    newUI = new megameklab.com.ui.Vehicle.MainUI();
                }
                if (null == newUI) {
                    JOptionPane.showMessageDialog(parentFrame,
                            "Warning: Could not create new UI, aborting unit load!");
                    return;
                }
                parentFrame.dispose();
                UnitUtil.updateLoadedUnit(tempEntity);
                newUI.setEntity(tempEntity);
                newUI.reloadTabs();
                newUI.repaint();
                newUI.refreshAll();
                return;
            }
            parentFrame.setEntity(tempEntity);
            UnitUtil.updateLoadedUnit(parentFrame.getEntity());

            CConfig.updateSaveFiles(unitFile.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, String.format(
                    "Warning:Invalid unit, it might load incorrectly!\n%1$s",
                    ex.getMessage()));
        }
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

    @Override
    public void lostOwnership(Clipboard arg0, Transferable arg1) {

    }

}
