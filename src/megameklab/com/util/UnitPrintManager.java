/*
 * MegaMekLab - Copyright (C) 2009
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

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.UnitSelectorDialog;
import megamek.common.Aero;
import megamek.common.BattleArmor;
import megamek.common.ConvFighter;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityListFile;
import megamek.common.EntityMovementMode;
import megamek.common.FixedWingSupport;
import megamek.common.Infantry;
import megamek.common.Jumpship;
import megamek.common.LargeSupportTank;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.Protomech;
import megamek.common.SmallCraft;
import megamek.common.Tank;
import megamek.common.VTOL;
import megameklab.com.printing.PrintMech;
import megameklab.com.ui.Aero.Printing.PrintAero;
import megameklab.com.ui.Aero.Printing.PrintConventionalFighter;
import megameklab.com.ui.Aero.Printing.PrintFixedWingSupport;
import megameklab.com.ui.Aero.Printing.PrintSmallCraftAerodyne;
import megameklab.com.ui.Aero.Printing.PrintSmallCraftSpheroid;
import megameklab.com.ui.BattleArmor.Printing.PrintBattleArmor;
import megameklab.com.ui.Dropship.Printing.PrintAerodyne;
import megameklab.com.ui.Dropship.Printing.PrintSpheroid;
import megameklab.com.ui.Infantry.Printing.PrintInfantry;
import megameklab.com.ui.ProtoMek.Printing.PrintProtomech;
import megameklab.com.ui.Vehicle.Printing.PrintDualTurretVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintLargeSupportVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintNavalVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintVTOL;
import megameklab.com.ui.Vehicle.Printing.PrintVehicle;
import megameklab.com.ui.dialog.UnitPrintQueueDialog;

public class UnitPrintManager {

    public static boolean printEntity(Entity entity) {

        Vector<Entity> unitList = new Vector<Entity>();

        unitList.add(entity);

        return printAllUnits(unitList, false);
    }

    public static void selectUnitToPrint(JFrame parent) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parent, unitLoadingDialog, true);

        Entity entity = null;

        entity = viewer.getChosenEntity();

        viewer.setVisible(false);
        viewer.dispose();
        if(null != entity) {
            UnitPrintManager.printEntity(entity);
        }
    }

    public static void printMuls(Frame parent, boolean singlePrint) {
        JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Print From MUL");
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Mul Files", "mul");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parent);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        Vector<Entity> loadedUnits = new Vector<Entity>();
        try {
            loadedUnits = EntityListFile.loadFrom(f.getSelectedFile());
            loadedUnits.trimToSize();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        printAllUnits(loadedUnits, singlePrint);
    }

    public static boolean printAllUnits(Vector<Entity> loadedUnits, boolean singlePrint) {
        Book book = new Book();
        
        List<Infantry> infList = new ArrayList<>();
        List<BattleArmor> baList = new ArrayList<>();
        List<Protomech> protoList = new ArrayList<>();
        List<Entity> unprintable = new ArrayList<>();

        HashPrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(MediaSizeName.NA_LETTER);
        aset.add(new MediaPrintableArea(0, 0, 8.5f, 11, MediaPrintableArea.INCH));
        PrinterJob masterPrintJob = PrinterJob.getPrinterJob();
        if (!masterPrintJob.printDialog(aset)) {
            return true;
        }

        PageFormat pageFormat = new PageFormat();
        pageFormat = masterPrintJob.getPageFormat(null);

        Paper p = pageFormat.getPaper();
        p.setImageableArea(0, 0, p.getWidth(), p.getHeight());

        pageFormat.setPaper(p);

        Tank tank1 = null;
        Tank wige1 = null;
        Tank dualTurret1 = null;
        for (Entity unit : loadedUnits) {
            if (unit instanceof Mech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);
                book.append(new PrintMech((Mech) unit, book.getNumberOfPages()), pageFormat);
            } else if ((unit instanceof LargeSupportTank) || ((unit instanceof Tank) && (unit.getMovementMode() != EntityMovementMode.VTOL) && ((Tank)unit).isSuperHeavy())) {
                book.append(new PrintLargeSupportVehicle((Tank) unit), pageFormat);
            } else if (unit instanceof VTOL) {
                book.append(new PrintVTOL((VTOL) unit), pageFormat);
            } else if (unit.getMovementMode() == EntityMovementMode.WIGE) {
                if (singlePrint) {
                    book.append(new PrintVehicle((Tank) unit,  null), pageFormat);
                } else if (null != wige1) {
                    book.append(new PrintVehicle(wige1, (Tank) unit), pageFormat);
                    wige1 = null;
                } else {
                    wige1 = (Tank) unit;
                }
            } else if ((unit instanceof Tank) && ((unit.getMovementMode() == EntityMovementMode.NAVAL) || (unit.getMovementMode() == EntityMovementMode.SUBMARINE) || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL))) {
                book.append(new PrintNavalVehicle((Tank) unit), pageFormat);
            } else if (unit instanceof Tank) {
                if (!((Tank) unit).hasNoDualTurret()) {
                    if (singlePrint) {
                        book.append(new PrintDualTurretVehicle((Tank) unit,  null), pageFormat);
                    } else if (null != dualTurret1) {
                        book.append(new PrintDualTurretVehicle(dualTurret1, (Tank) unit), pageFormat);
                        dualTurret1 = null;
                    } else {
                        dualTurret1 = (Tank) unit;
                    }
                } else {
                    if (singlePrint) {
                        book.append(new PrintVehicle((Tank) unit,  null), pageFormat);
                    } else if (null != tank1) {
                        book.append(new PrintVehicle(tank1, (Tank) unit), pageFormat);
                        tank1 = null;
                    } else {
                        tank1 = (Tank) unit;
                    }
                }
            } else if (unit instanceof Aero) {
                if (unit instanceof Dropship) {
                    if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                        book.append(new PrintAerodyne((Dropship) unit), pageFormat);
                    } else {
                        book.append(new PrintSpheroid((Dropship) unit), pageFormat);
                    }
                } else if (unit instanceof FixedWingSupport) {
                    book.append(new PrintFixedWingSupport((FixedWingSupport) unit), pageFormat);
                } else if (unit instanceof ConvFighter) {
                    book.append(new PrintConventionalFighter((ConvFighter) unit), pageFormat);
                } else if (unit instanceof SmallCraft) {
                    if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                        book.append(new PrintSmallCraftAerodyne((SmallCraft) unit), pageFormat);
                    } else {
                        book.append(new PrintSmallCraftSpheroid((SmallCraft) unit), pageFormat);
                    }
                } else if (!(unit instanceof Jumpship)) {
                    book.append(new PrintAero((Aero) unit), pageFormat);
                }
            } else if (unit instanceof BattleArmor) {
                baList.add((BattleArmor) unit);
                if (singlePrint || baList.size() > 4) {
                    book.append(new PrintBattleArmor(baList),  pageFormat);
                    baList = new ArrayList<>();
                }
            } else if (unit instanceof Infantry) {
                infList.add((Infantry) unit);
                if (singlePrint || infList.size() > 3) {
                    book.append(new PrintInfantry(infList),  pageFormat);
                    infList = new ArrayList<>();
                }
            } else if (unit instanceof Protomech) {
                protoList.add((Protomech) unit);
                if (singlePrint || protoList.size() > 4) {
                    book.append(new PrintProtomech(protoList),  pageFormat);
                    protoList = new ArrayList<>();
                }
            } else {
                //TODO: show a message dialog that lists the unprintable units
                unprintable.add(unit);
            }
        }
        if (null != wige1) {
            book.append(new PrintVehicle(wige1, null), pageFormat);
        }
        if (null != tank1) {
            book.append(new PrintVehicle(tank1, null), pageFormat);
        }
        if (null != dualTurret1) {
            book.append(new PrintDualTurretVehicle(dualTurret1, null), pageFormat);
        }
        if (baList.size() > 0) {
            book.append(new PrintBattleArmor(baList), pageFormat);
        }
        if (infList.size() > 0) {
            book.append(new PrintInfantry(infList), pageFormat);
        }
        if (protoList.size() > 0) {
            book.append(new PrintProtomech(protoList), pageFormat);
        }
        
        masterPrintJob.setPageable(book);
        if (loadedUnits.size() > 1) {
            masterPrintJob.setJobName(loadedUnits.get(0).getShortNameRaw() + " etc");
        } else if (loadedUnits.size() > 0) {
            masterPrintJob.setJobName(loadedUnits.get(0).getShortNameRaw());
        }
        try {
            masterPrintJob.print(aset);
        } catch (PrinterException e) {
            // printing cancelled
            return false;
        }

        return true;
    }

    public static JMenu printMenu(final JFrame parent, JMenuItem item) {
        JMenu printMenu = new JMenu("Print");
        printMenu.setMnemonic(KeyEvent.VK_P);

        printMenu.add(item);

        printMenu.addSeparator();

        item = new JMenuItem("Queue Units to Print");
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UnitPrintQueueDialog(parent);
            }
        });

        printMenu.add(item);
        printMenu.addSeparator();

        item = new JMenuItem("Other Unit");
        item.setMnemonic(KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printSelectedUnit(parent);
            }
        });
        printMenu.add(item);

        item = new JMenuItem("From File");
        item.setMnemonic(KeyEvent.VK_I);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printUnitFile(parent);
            }
        });
        printMenu.add(item);

        item = new JMenuItem("From File (Single Unit Per RS)");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printUnitFile(parent, true);
            }
        });
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem("From MUL");
        item.setMnemonic(KeyEvent.VK_M);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printMuls(parent, false);
            }
        });
        printMenu.add(item);

        item = new JMenuItem("From MUL (Single Unit Per RS)");
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printMuls(parent, true);
            }
        });
        printMenu.add(item);

        return printMenu;
    }

    public static void printSelectedUnit(JFrame parent) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        unitLoadingDialog.setVisible(true);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parent, unitLoadingDialog, true);

        viewer.setVisible(false);
        Entity entity = viewer.getChosenEntity();

        if (entity != null) {
            boolean printed = UnitPrintManager.printEntity(entity);
            viewer.dispose();

            if (!printed) {
                JOptionPane.showMessageDialog(parent, "Unable to print that unit type!");
            }
        }
    }

    public static void printUnitFile(JFrame parent) {
        printUnitFile(parent, false);
    }

    public static void printUnitFile(JFrame parent, boolean singleUnit) {
        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

        JFileChooser f = new JFileChooser(filePathName);
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Print Unit File");
        f.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parent);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }

        try {

            Vector<Entity> unitList = new Vector<Entity>();

            for (File entityFile : f.getSelectedFiles()) {
                Entity tempEntity = new MechFileParser(entityFile).getEntity();
                unitList.add(tempEntity);
            }
            printAllUnits(unitList, singleUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}