/*
 * Copyright (C) 2009-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.util;

import static megamek.common.options.OptionsConstants.RPG_MANEI_DOMINI;
import static megamek.common.options.OptionsConstants.RPG_PILOT_ADVANTAGES;

import java.awt.Frame;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.equipment.HandheldWeapon;
import megamek.common.loaders.MULParser;
import megamek.common.loaders.MekFileParser;
import megamek.common.options.GameOptions;
import megamek.common.units.Aero;
import megamek.common.units.BTObject;
import megamek.common.units.Dropship;
import megamek.common.units.Entity;
import megamek.common.units.Infantry;
import megamek.common.units.Jumpship;
import megamek.common.units.Mek;
import megamek.common.units.ProtoMek;
import megamek.common.units.Tank;
import megamek.logging.MMLogger;
import megameklab.printing.*;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.PrintQueueDialog;
import org.apache.commons.io.FilenameUtils;

public class UnitPrintManager {
    private static final MMLogger LOGGER = MMLogger.create(UnitPrintManager.class);

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
            return;
        }
        printMUL(parent, printToPdf, f.getSelectedFile());
    }

    public static void printMUL(JFrame parent, boolean printToPdf, File file) {
        Vector<Entity> loadedUnits;
        try {
            var options = new GameOptions();
            options.initialize();
            options.getOption(RPG_MANEI_DOMINI).setValue(true);
            options.getOption(RPG_PILOT_ADVANTAGES).setValue(true);
            loadedUnits = new MULParser(file, options).getEntities();
            loadedUnits.trimToSize();
        } catch (Exception ex) {
            LOGGER.error("", ex);
            return;
        }

        new PrintQueueDialog(parent, printToPdf, loadedUnits, true, file.getName()).setVisible(true);
    }

    public static File getExportFile(Frame parent) {
        return getExportFile(parent, "");
    }

    public static File getExportFile(Frame parent, String suggestedFileName) {
        JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
        if (parent != null) {
            f.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        }
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
            return null;
        }

        var file = f.getSelectedFile();

        if (FilenameUtils.getExtension(file.getName()).isEmpty()) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }

        return file;
    }

    public static List<PrintRecordSheet> createSheets(List<? extends BTObject> entities, boolean singlePrint,
          RecordSheetOptions options) {
        return createSheets(entities, singlePrint, options, false);
    }

    public static List<PrintRecordSheet> createSheets(List<? extends BTObject> entities, boolean singlePrint,
          RecordSheetOptions options, boolean noWarningsOnUnprintable) {
        List<PrintRecordSheet> sheets = new ArrayList<>();
        List<Infantry> infList = new ArrayList<>();
        List<BattleArmor> baList = new ArrayList<>();
        List<ProtoMek> protoList = new ArrayList<>();
        List<HandheldWeapon> hhwList = new ArrayList<>();
        List<BTObject> unprintable = new ArrayList<>();
        Tank tank1 = null;

        int pageCount = 0;
        for (BTObject object : entities) {
            if (object instanceof Entity entity) {
                Entity unit;
                // assign base unit and override only if damage should be hidden and entity is damaged
                if (!options.showDamage() && UnitUtil.isDamaged(entity, options.showPilotData())) {
                    unit = UnitUtil.cloneUnit(entity);
                    if (unit != null) {
                        UnitUtil.resetUnit(unit);
                    }
                } else {
                    unit = entity;
                }
                if (unit instanceof Mek) {
                    UnitUtil.removeOneShotAmmo(unit);
                    MekUtil.expandUnitMounts((Mek) unit);
                    sheets.add(new PrintMek((Mek) unit, pageCount++, options));
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
                } else if (unit != null && unit.hasETypeFlag(Entity.ETYPE_AERO)) {
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
                    if (singlePrint || PrintSmallUnitSheet.fillsSheet(baList, options)) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(baList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        baList = new ArrayList<>();
                    }
                } else if (unit instanceof Infantry) {
                    infList.add((Infantry) unit);
                    if (singlePrint || PrintSmallUnitSheet.fillsSheet(infList, options)) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(infList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        infList = new ArrayList<>();
                    }
                } else if (unit instanceof ProtoMek) {
                    protoList.add((ProtoMek) unit);
                    if (singlePrint || PrintSmallUnitSheet.fillsSheet(protoList, options)) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(protoList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        protoList = new ArrayList<>();
                    }
                } else if (unit instanceof HandheldWeapon) {
                    if (!singlePrint) {
                        final PrintHandheldWeapon phw = new PrintHandheldWeapon((HandheldWeapon) unit,
                              pageCount,
                              options);
                        final int reservedSpace = phw.isLargeLayout() ? 1 : 0;
                        if (reservedSpace > 0 && PrintSmallUnitSheet.fillsSheet(hhwList, options, reservedSpace)) {
                            PrintRecordSheet prs = new PrintSmallUnitSheet(hhwList, pageCount, options);
                            pageCount += prs.getPageCount();
                            sheets.add(prs);
                            hhwList = new ArrayList<>();
                        }
                    }
                    hhwList.add((HandheldWeapon) unit);
                    if (singlePrint || PrintSmallUnitSheet.fillsSheet(hhwList, options)) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(hhwList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        hhwList = new ArrayList<>();
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
                    if (!hhwList.isEmpty()) {
                        PrintRecordSheet prs = new PrintSmallUnitSheet(hhwList, pageCount, options);
                        pageCount += prs.getPageCount();
                        sheets.add(prs);
                        hhwList = new ArrayList<>();
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

        if (!noWarningsOnUnprintable) {
            if (!unprintable.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Exporting is not currently supported for the following units:\n"
                      + unprintable.stream().map(en -> en.generalName() + ' ' + en.specificName())
                      .collect(Collectors.joining("\n")));
            }
        }

        if (null != tank1) {
            sheets.add(new PrintCompositeTankSheet(tank1, null, pageCount++, options));
        }

        if (!baList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(baList, pageCount++, options));
        }

        if (!infList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(infList, pageCount++, options));
        }

        if (!protoList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(protoList, pageCount++, options));
        }
        if (!hhwList.isEmpty()) {
            sheets.add(new PrintSmallUnitSheet(hhwList, pageCount++, options));
        }
        return sheets;
    }

    public static void exportUnits(List<? extends BTObject> units, File exportFile, boolean singlePrint) {
        exportUnits(units, exportFile, singlePrint, new RecordSheetOptions());
    }

    public static void exportUnits(List<? extends BTObject> units, File exportFile, boolean singlePrint,
          RecordSheetOptions options) {
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
    public static boolean printAllUnits(List<? extends BTObject> loadedUnits, boolean singlePrint,
          RecordSheetOptions options) {
        HashPrintRequestAttributeSet hashPrintRequestAttributeSet = new HashPrintRequestAttributeSet();
        hashPrintRequestAttributeSet.add(options.getPaperSize().sizeName);
        hashPrintRequestAttributeSet.add(options.getPaperSize().printableArea);
        hashPrintRequestAttributeSet.add(DialogTypeSelection.COMMON);
        PrinterJob masterPrintJob = PrinterJob.getPrinterJob();
        if (!masterPrintJob.printDialog(hashPrintRequestAttributeSet)) {
            return false;
        }

        PageFormat pageFormat = masterPrintJob.getPageFormat(hashPrintRequestAttributeSet);
        // If something besides letter and A4 is selected, use the template that's
        // closest to the aspect
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

        RecordSheetTask task = RecordSheetTask.createPrintTask(sheets,
              masterPrintJob,
              hashPrintRequestAttributeSet,
              pageFormat);
        task.execute(CConfig.getBooleanParam(CConfig.RS_PROGRESS_BAR));
        return true;
    }

    public static void printSelectedUnit(JFrame parent, boolean pdf) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(parent, unitLoadingDialog, false);

        viewer.setVisible(false);
        Entity entity = viewer.getChosenEntity();

        try {
            if (entity != null) {
                if (pdf) {
                    exportEntity(entity, parent);
                } else {
                    printEntity(entity);
                }
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
    }

    public static void printUnitFile(JFrame parent, boolean singleUnit, boolean pdf) {
        String filePathName = System.getProperty("user.dir") + "/data/mekfiles/"; // TODO : Remove inline file path

        JFileChooser jFileChooser = new JFileChooser(filePathName);
        jFileChooser.setLocation(parent.getLocation().x + 150, parent.getLocation().y + 100);
        jFileChooser.setDialogTitle("Print Unit File");
        jFileChooser.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Unit Files", "blk", "mtf");

        // Add a filter for mul files
        jFileChooser.setFileFilter(filter);

        int returnVal = jFileChooser.showOpenDialog(parent);
        if ((returnVal != JFileChooser.APPROVE_OPTION) || (jFileChooser.getSelectedFile() == null)) {
            return;
        }

        try {
            List<Entity> unitList = new ArrayList<>();

            for (File entityFile : jFileChooser.getSelectedFiles()) {
                Entity tempEntity = new MekFileParser(entityFile).getEntity();
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
            LOGGER.error("", ex);
        }
    }
}
