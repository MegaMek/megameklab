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
package megameklab.util;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.*;
import megamek.common.util.C3Util;
import megameklab.printing.*;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import org.apache.logging.log4j.LogManager;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class UnitPrintManager {

    private static final ResourceBundle menuResources = ResourceBundle.getBundle("megameklab.resources.Menu");

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

    public static void printMUL(JFrame parent, boolean printToPdf) {
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
            loadedUnits = new MULParser(f.getSelectedFile(), null).getEntities();
            loadedUnits.trimToSize();
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
            return;
        }

        // Dummy player and game allow bonus BV from C3 and TAG to be calculated
        Game g = new Game();
        Player p = new Player(1, "Nobody");
        for (Entity e : loadedUnits) {
            e.setOwner(p);
            g.addEntity(e);
            C3Util.wireC3(g, e);
        }

        new PrintQueueDialog(parent, printToPdf, loadedUnits, true).setVisible(true);
    }

    public static File getExportFile(Frame parent) {
        return getExportFile(parent, "");
    }

    private static File getExportFile(Frame parent, String suggestedFileName) {
        JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
        f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        f.setDialogTitle("Choose export file name");
        f.setMultiSelectionEnabled(false);
        if (!suggestedFileName.isEmpty()) {
            f.setSelectedFile(new File(suggestedFileName));
        }
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");

        // Add a filter for mul files
        f.setFileFilter(filter);

        int returnVal = f.showSaveDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            // I want a file, y'know!
            return null;
        }
        return f.getSelectedFile();
    }

    private static List<PrintRecordSheet> createSheets(List<? extends BTObject> entities, boolean singlePrint,
                                                       RecordSheetOptions options) {
        List<PrintRecordSheet> sheets = new ArrayList<>();
        List<Infantry> infList = new ArrayList<>();
        List<BattleArmor> baList = new ArrayList<>();
        List<Protomech> protoList = new ArrayList<>();
        List<BTObject> unprintable = new ArrayList<>();
        Tank tank1 = null;

        int pageCount = 0;
        for (BTObject object : entities) {
            if (object instanceof Entity) {
                Entity unit = (Entity) object;
                if (unit instanceof Mech) {
                    UnitUtil.removeOneShotAmmo(unit);
                    MekUtil.expandUnitMounts((Mech) unit);
                    sheets.add(new PrintMech((Mech) unit, pageCount++, options));
                } else if ((unit instanceof Tank) && unit.getMovementMode().isMarine()) {
                    sheets.add(new PrintTank((Tank) unit, pageCount++, options));
                } else if (unit instanceof Tank) {
                    if (singlePrint || options.showReferenceCharts()) {
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
                        sheets.add(new PrintAero((Aero) unit, pageCount++, options));
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
                    if (singlePrint || infList.size() > (options.showReferenceCharts() ? 2 : 3)) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(infList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        infList = new ArrayList<>();
                    }
                } else if (unit instanceof Protomech) {
                    protoList.add((Protomech) unit);
                    if (singlePrint || protoList.size() > 4) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(protoList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        protoList = new ArrayList<>();
                    }
                } else {
                    unprintable.add(unit);
                }
            } else if (object instanceof PageBreak) {
                if (!singlePrint) {
                    if (!baList.isEmpty()) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(baList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        baList = new ArrayList<>();
                    }
                    if (!infList.isEmpty()) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(infList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        infList = new ArrayList<>();
                    }
                    if (!protoList.isEmpty()) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(protoList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        protoList = new ArrayList<>();
                    }
                    if (null != tank1) {
                        sheets.add(new PrintCompositeTankSheet(tank1, null, pageCount++, options));
                        tank1 = null;
                    }
                }
            } else {
                unprintable.add(object);
            }
        }

        if (!unprintable.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Exporting is not currently supported for the following units:\n"
                    + unprintable.stream().map(en -> en.generalName() + ' ' + en.specificName())
                    .collect(Collectors.joining("\n")));
        }

        if (null != tank1) {
            sheets.add(new PrintCompositeTankSheet(tank1, null, pageCount++));
        }

        if (!baList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(baList, pageCount++));
        }

        if (!infList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(infList, pageCount++));
        }

        if (!protoList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(protoList, pageCount));
        }
        return sheets;
    }

    public static void exportUnits(List<? extends BTObject> units, File exportFile, boolean singlePrint) {
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
    public static void printAllUnits(List<? extends BTObject> loadedUnits, boolean singlePrint) {
        printAllUnits(loadedUnits, singlePrint, new RecordSheetOptions());
    }

    /**
     * Creates and runs a print job using the provided record sheet options
     *
     * @param loadedUnits The units to print
     * @param singlePrint Whether to limit each record sheet to a single unit
     * @param options     The options to use for this print job
     */
    public static void printAllUnits(List<? extends BTObject> loadedUnits, boolean singlePrint,
                                     RecordSheetOptions options) {
        HashPrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(options.getPaperSize().sizeName);
        aset.add(options.getPaperSize().printableArea);
        aset.add(DialogTypeSelection.COMMON);
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
            String name;
            if (loadedUnits.get(0) instanceof Entity) {
                name = ((Entity) loadedUnits.get(0)).getShortNameRaw();
            } else {
                name = loadedUnits.get(0).generalName();
            }
            masterPrintJob.setJobName(name + " etc");
        } else if (!loadedUnits.isEmpty()) {
            String name;
            if (loadedUnits.get(0) instanceof Entity) {
                name = ((Entity) loadedUnits.get(0)).getShortNameRaw();
            } else {
                name = loadedUnits.get(0).generalName();
            }
            masterPrintJob.setJobName(name);
        }

        RecordSheetTask task = RecordSheetTask.createPrintTask(sheets, masterPrintJob, aset, pageFormat);
        task.execute(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));
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
        String filePathName = System.getProperty("user.dir") + "/data/mechfiles/"; // TODO : Remove inline file path

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
            LogManager.getLogger().error("", ex);
        }
    }
}
