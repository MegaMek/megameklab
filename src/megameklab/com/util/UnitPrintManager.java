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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

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
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityListFile;
import megamek.common.EntityMovementMode;
import megamek.common.Infantry;
import megamek.common.Jumpship;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.Protomech;
import megamek.common.Tank;
import megameklab.com.printing.*;
import megameklab.com.ui.BattleArmor.Printing.PrintBattleArmor;
import megameklab.com.ui.dialog.UnitPrintQueueDialog;
import megameklab.com.ui.protomek.printing.PrintProtomech;

public class UnitPrintManager {

    public static boolean printEntity(Entity entity) {

        Vector<Entity> unitList = new Vector<>();

        unitList.add(entity);

        return printAllUnits(unitList, false);
    }

    public static void selectUnitToPrint(JFrame parent) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        UnitSelectorDialog viewer = new UnitSelectorDialog(parent, unitLoadingDialog, true);

        Entity entity;

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
        Vector<Entity> loadedUnits;
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

        PageFormat pageFormat  = masterPrintJob.getPageFormat(null);

        Paper p = pageFormat.getPaper();
        p.setImageableArea(0, 0, p.getWidth(), p.getHeight());

        pageFormat.setPaper(p);

        Tank tank1 = null;
        for (Entity unit : loadedUnits) {
            if (unit instanceof Mech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);
                book.append(new PrintMech((Mech) unit, book.getNumberOfPages()), pageFormat);
            } else if ((unit instanceof Tank) && ((unit.getMovementMode() == EntityMovementMode.NAVAL) || (unit.getMovementMode() == EntityMovementMode.SUBMARINE) || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL))) {
                book.append(new PrintTank((Tank) unit, book.getNumberOfPages()), pageFormat);
            } else if (unit instanceof Tank) {
                if (singlePrint) {
                    book.append(new PrintCompositeTankSheet((Tank) unit, null, book.getNumberOfPages()), pageFormat);
                } else if (null != tank1) {
                    book.append(new PrintCompositeTankSheet(tank1, (Tank) unit, book.getNumberOfPages()), pageFormat);
                    tank1 = null;
                } else {
                    tank1 = (Tank) unit;
                }
            } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
                if (unit instanceof Jumpship) {
                    PrintCapitalShip pcs = new PrintCapitalShip((Jumpship) unit, book.getNumberOfPages());
                    book.append(pcs, pageFormat, pcs.getPageCount());
                } else if (unit instanceof Dropship) {
                    PrintDropship pds = new PrintDropship((Aero) unit, book.getNumberOfPages());
                    book.append(pds, pageFormat, pds.getPageCount());
                } else {
                    book.append(new PrintAero((Aero) unit, book.getNumberOfPages()), pageFormat);
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
                    book.append(new PrintSmallUnitSheet(infList, book.getNumberOfPages()),  pageFormat);
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
        
        if (unprintable.size() > 0) {
            JOptionPane.showMessageDialog(null, "Printing is not currently supported for the following units:\n"
                    + unprintable.stream().map(en -> en.getChassis() + " " + en.getModel())
                    .collect(Collectors.joining("\n")));
        }
        
        if (null != tank1) {
            book.append(new PrintCompositeTankSheet(tank1, null, book.getNumberOfPages()), pageFormat);
        }
        if (baList.size() > 0) {
            book.append(new PrintBattleArmor(baList), pageFormat);
        }
        if (infList.size() > 0) {
            book.append(new PrintSmallUnitSheet(infList, book.getNumberOfPages()), pageFormat);
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

        PrintTask task = new PrintTask(masterPrintJob, aset);
        task.execute();

        return true;
    }

    public static JMenu printMenu(final JFrame parent, JMenuItem item) {
        JMenu printMenu = new JMenu("Print");
        printMenu.setMnemonic(KeyEvent.VK_P);

        printMenu.add(item);

        printMenu.addSeparator();

        item = new JMenuItem("Queue Units to Print");
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(e -> new UnitPrintQueueDialog(parent));

        printMenu.add(item);
        printMenu.addSeparator();

        item = new JMenuItem("Other Unit");
        item.setMnemonic(KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printSelectedUnit(parent));
        printMenu.add(item);

        item = new JMenuItem("From File");
        item.setMnemonic(KeyEvent.VK_I);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent));
        printMenu.add(item);

        item = new JMenuItem("From File (Single Unit Per RS)");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent, true));
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem("From MUL");
        item.setMnemonic(KeyEvent.VK_M);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printMuls(parent, false));
        printMenu.add(item);

        item = new JMenuItem("From MUL (Single Unit Per RS)");
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(e -> UnitPrintManager.printMuls(parent, true));
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
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/";

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

            Vector<Entity> unitList = new Vector<>();

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