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

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.ui.swing.UnitLoadingDialog;
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
import megamek.common.util.EncodeControl;
import megameklab.com.printing.*;
import megameklab.com.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.com.ui.MegaMekLabMainUI;
import megameklab.com.ui.dialog.UnitPrintQueueDialog;
import org.apache.commons.io.FilenameUtils;

public class UnitPrintManager {

    private static final ResourceBundle menuResources = ResourceBundle.getBundle("megameklab.resources.Menu", new EncodeControl());

    public static void printEntity(Entity entity) {
        List<Entity> unitList = Collections.singletonList(entity);
        printAllUnits(unitList, false);
    }

    public static void exportEntity(Entity entity, JFrame parent) {
        File exportFile = getExportFile(parent, entity.getShortNameRaw() + ".pdf");
        if (exportFile != null) {
            exportUnits(Collections.singletonList(entity), exportFile, false);
        }
    }

    public static void printMUL(Frame parent, boolean singlePrint) {
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

    public static void exportMUL(Frame parent, boolean singlePrint) {
        JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Export from MUL");
        f.setMultiSelectionEnabled(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Mul Files", "mul");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showOpenDialog(parent);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (f.getSelectedFile() == null)) {
            // I want a file, y'know!
            return;
        }
        File mulFile = f.getSelectedFile();
        Vector<Entity> loadedUnits;
        try {
            loadedUnits = EntityListFile.loadFrom(mulFile);
            loadedUnits.trimToSize();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        File exportFile = getExportFile(parent, FilenameUtils.removeExtension(mulFile.getPath()) + ".pdf");
        if (exportFile != null) {
            exportUnits(loadedUnits, exportFile, singlePrint);
        }
    }

    public static File getExportFile(Frame parent) {
        return getExportFile(parent, "");
    }

    private static File getExportFile(Frame parent, String suggestedFileName) {
        JFileChooser f;
        FileNameExtensionFilter filter;
        int returnVal;
        f = new JFileChooser(System.getProperty("user.dir"));
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Choose export file name");
        f.setMultiSelectionEnabled(false);
        if (!suggestedFileName.isEmpty()) {
            f.setSelectedFile(new File(suggestedFileName));
        }
        filter = new FileNameExtensionFilter("PDF files", "pdf");

        // Add a filter for mul files
        f.setFileFilter(filter);

        returnVal = f.showSaveDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            // I want a file, y'know!
            return null;
        }
        return f.getSelectedFile();
    }

    private static List<PrintRecordSheet> createSheets(List<Entity> entities, boolean singlePrint,
                                                       RecordSheetOptions options) {
        List<PrintRecordSheet> sheets = new ArrayList<>();
        List<Infantry> infList = new ArrayList<>();
        List<BattleArmor> baList = new ArrayList<>();
        List<Protomech> protoList = new ArrayList<>();
        List<Entity> unprintable = new ArrayList<>();
        Tank tank1 = null;

        int pageCount = 0;
        for (Entity unit : entities) {
            if (unit instanceof Mech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);
                sheets.add(new PrintMech((Mech) unit, pageCount++, options));
            } else if ((unit instanceof Tank) && ((unit.getMovementMode() == EntityMovementMode.NAVAL) || (unit.getMovementMode() == EntityMovementMode.SUBMARINE) || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL))) {
                sheets.add(new PrintTank((Tank) unit, pageCount++, options));
            } else if (unit instanceof Tank) {
                if (singlePrint) {
                    sheets.add(new PrintCompositeTankSheet((Tank) unit, null, pageCount++, options));
                } else if (null != tank1) {
                    sheets.add(new PrintCompositeTankSheet(tank1, (Tank) unit, pageCount++, options));
                    tank1 = null;
                } else {
                    tank1 = (Tank) unit;
                }
            } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
                if (unit instanceof Jumpship) {
                    PrintCapitalShip pcs = new PrintCapitalShip((Jumpship) unit, pageCount, options);
                    pageCount += pcs.getPageCount();
                    sheets.add(pcs);
                } else if (unit instanceof Dropship) {
                    PrintDropship pds = new PrintDropship((Aero) unit, pageCount, options);
                    pageCount += pds.getPageCount();
                    sheets.add(pds);
                } else {
                    sheets.add(new PrintAero((Aero) unit, pageCount, options));
                }
            } else if (unit instanceof BattleArmor) {
                baList.add((BattleArmor) unit);
                if (singlePrint || baList.size() > 4) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(baList, pageCount, options);
                    pageCount += prs.getPageCount();
                    sheets.add(prs);
                    baList = new ArrayList<>();
                }
            } else if (unit instanceof Infantry) {
                infList.add((Infantry) unit);
                if (singlePrint || infList.size() > 3) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(infList, pageCount, options);
                    pageCount += prs.getPageCount();
                    sheets.add(prs);
                    infList = new ArrayList<>();
                }
            } else if (unit instanceof Protomech) {
                protoList.add((Protomech) unit);
                if (singlePrint || protoList.size() > 3) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(protoList, pageCount, options);
                    pageCount += prs.getPageCount();
                    sheets.add(prs);
                    protoList = new ArrayList<>();
                }
            } else {
                //TODO: show a message dialog that lists the unprintable units
                unprintable.add(unit);
            }
        }

        if (unprintable.size() > 0) {
            JOptionPane.showMessageDialog(null, "Exporting is not currently supported for the following units:\n"
                    + unprintable.stream().map(en -> en.getChassis() + " " + en.getModel())
                    .collect(Collectors.joining("\n")));
        }

        if (null != tank1) {
            sheets.add(new PrintCompositeTankSheet(tank1, null, pageCount++));
        }
        if (baList.size() > 0) {
            sheets.add(new PrintSmallUnitSheet(baList, pageCount++));
        }
        if (infList.size() > 0) {
            sheets.add(new PrintSmallUnitSheet(infList, pageCount++));
        }
        if (protoList.size() > 0) {
            sheets.add(new PrintSmallUnitSheet(protoList, pageCount));
        }
        return sheets;
    }

    public static void exportUnits(List<Entity> units, File exportFile, boolean singlePrint) {
        RecordSheetOptions options = new RecordSheetOptions();
        List<PrintRecordSheet> sheets = createSheets(units, singlePrint, options);
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(options.getPaperSize().createPaper());
        RecordSheetTask task = RecordSheetTask.createExportTask(sheets, pageFormat, exportFile.getAbsolutePath());
        task.execute(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));
    }

    /**
     * Creates and runs a print job using the default record sheet options
     *
     * @param loadedUnits The units to print
     * @param singlePrint Whether to limit each record sheet to a single unit
     */
    public static void printAllUnits(List<Entity> loadedUnits, boolean singlePrint) {
        printAllUnits(loadedUnits, singlePrint, new RecordSheetOptions());
    }

    /**
     * Creates and runs a print job using the provided record sheet options
     *
     * @param loadedUnits The units to print
     * @param singlePrint Whether to limit each record sheet to a single unit
     * @param options     The options to use for this print job
     */
    public static void printAllUnits(List<Entity> loadedUnits, boolean singlePrint,
                                        RecordSheetOptions options) {
        HashPrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(options.getPaperSize().sizeName);
        aset.add(options.getPaperSize().printableArea);
        aset.add(DialogTypeSelection.NATIVE);
        PrinterJob masterPrintJob = PrinterJob.getPrinterJob();
        if (!masterPrintJob.printDialog(aset)) {
            return;
        }

        PageFormat pageFormat  = masterPrintJob.getPageFormat(aset);
        // If something besides letter and A4 is selected, use the template that's closest to the aspect
        // ratio of the paper size.
        options.setPaperSize(PaperSize.closestToAspect(pageFormat.getWidth(), pageFormat.getHeight()));
        List<PrintRecordSheet> sheets = createSheets(loadedUnits, singlePrint, options);

        if (loadedUnits.size() > 1) {
            masterPrintJob.setJobName(loadedUnits.get(0).getShortNameRaw() + " etc");
        } else if (loadedUnits.size() > 0) {
            masterPrintJob.setJobName(loadedUnits.get(0).getShortNameRaw());
        }

        RecordSheetTask task = RecordSheetTask.createPrintTask(sheets, masterPrintJob, aset, pageFormat);
        task.execute(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));
    }

    public static JMenu printMenu(final MegaMekLabMainUI parent) {
        JMenu printMenu = new JMenu(menuResources.getString("menu.file.print"));
        printMenu.setMnemonic(KeyEvent.VK_P);

        JMenuItem item = new JMenuItem(menuResources.getString("menu.file.print.currentUnit"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        item.setMnemonic(KeyEvent.VK_C);
        item.addActionListener(e -> printEntity(parent.getEntity()));
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem(menuResources.getString("menu.file.print.queueUnits"));
        item.setMnemonic(KeyEvent.VK_Q);
        item.addActionListener(e -> new UnitPrintQueueDialog(parent, false));

        printMenu.add(item);
        printMenu.addSeparator();

        item = new JMenuItem(menuResources.getString("menu.file.print.otherUnit"));
        item.setMnemonic(KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printSelectedUnit(parent, false));
        printMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromFile"));
        item.setMnemonic(KeyEvent.VK_I);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent, false, false));
        printMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromFileSingle"));
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent, true, false));
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem(menuResources.getString("menu.file.print.fromMUL"));
        item.setMnemonic(KeyEvent.VK_M);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        item.addActionListener(e -> UnitPrintManager.printMUL(parent, false));
        printMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromMULSingle"));
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(e -> UnitPrintManager.printMUL(parent, true));
        printMenu.add(item);

        return printMenu;
    }

    public static JMenu exportMenu(final MegaMekLabMainUI parent) {
        JMenu exportMenu = new JMenu(menuResources.getString("menu.file.exportPDF"));
        exportMenu.setMnemonic(KeyEvent.VK_E);

        JMenuItem item = new JMenuItem(menuResources.getString("menu.file.print.currentUnit"));
        item.addActionListener(e -> exportEntity(parent.getEntity(), parent));
        exportMenu.add(item);

        exportMenu.addSeparator();
        item = new JMenuItem(menuResources.getString("menu.file.print.queueUnits"));
        item.addActionListener(e -> new UnitPrintQueueDialog(parent, true));
        exportMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.otherUnit"));
        item.addActionListener(e -> UnitPrintManager.printSelectedUnit(parent, true));
        exportMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromFile"));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent, false, true));
        exportMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromFileSingle"));
        item.addActionListener(e -> UnitPrintManager.printUnitFile(parent, true, true));
        exportMenu.add(item);

        exportMenu.addSeparator();
        item = new JMenuItem(menuResources.getString("menu.file.print.fromMUL"));
        item.addActionListener(e -> UnitPrintManager.exportMUL(parent, false));
        exportMenu.add(item);

        item = new JMenuItem(menuResources.getString("menu.file.print.fromMULSingle"));
        item.setMnemonic(KeyEvent.VK_R);
        item.addActionListener(e -> UnitPrintManager.exportMUL(parent, true));
        exportMenu.add(item);

        return exportMenu;
    }

    public static void printSelectedUnit(JFrame parent, boolean pdf) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parent, unitLoadingDialog);

        viewer.setVisible(false);
        Entity entity = viewer.getChosenEntity();

        if (entity != null) {
            if (pdf) {
                exportEntity(entity, parent);
            } else {
                printEntity(entity);
            }
            viewer.dispose();
        }
    }

    public static void printUnitFile(JFrame parent, boolean singleUnit, boolean pdf) {
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
            List<Entity> unitList = new ArrayList<>();

            for (File entityFile : f.getSelectedFiles()) {
                Entity tempEntity = new MechFileParser(entityFile).getEntity();
                unitList.add(tempEntity);
            }
            if (pdf) {
                File exportFile = getExportFile(parent);
                if (exportFile != null) {
                    exportUnits(unitList, exportFile, singleUnit);
                }
            } else {
                printAllUnits(unitList, singleUnit);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}