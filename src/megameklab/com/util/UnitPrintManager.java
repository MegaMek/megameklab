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
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

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
import megamek.common.BipedMech;
import megamek.common.ConvFighter;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityListFile;
import megamek.common.EntityMovementMode;
import megamek.common.FixedWingSupport;
import megamek.common.LargeSupportTank;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.Protomech;
import megamek.common.QuadMech;
import megamek.common.SmallCraft;
import megamek.common.Tank;
import megamek.common.VTOL;
import megameklab.com.ui.Aero.Printing.PrintAero;
import megameklab.com.ui.Aero.Printing.PrintConventionalFighter;
import megameklab.com.ui.Aero.Printing.PrintFixedWingSupport;
import megameklab.com.ui.Aero.Printing.PrintSmallCraftAerodyne;
import megameklab.com.ui.Aero.Printing.PrintSmallCraftSpheroid;
import megameklab.com.ui.BattleArmor.Printing.PrintBattleArmor;
import megameklab.com.ui.Dropship.Printing.PrintAerodyne;
import megameklab.com.ui.Dropship.Printing.PrintSpheroid;
import megameklab.com.ui.Mek.Printing.PrintMech;
import megameklab.com.ui.Mek.Printing.PrintQuad;
import megameklab.com.ui.ProtoMek.Printing.PrintProtomech;
import megameklab.com.ui.Vehicle.Printing.PrintDualTurretVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintLargeSupportVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintNavalVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintVTOL;
import megameklab.com.ui.Vehicle.Printing.PrintVehicle;
import megameklab.com.ui.Vehicle.Printing.PrintWiGE;
import megameklab.com.ui.dialog.UnitPrintQueueDialog;
import megameklab.com.ui.dialog.UnitViewerDialog;

public class UnitPrintManager {

    public static boolean printEntity(Entity entity) {

        Vector<Entity> unitList = new Vector<Entity>();

        unitList.add(entity);

        return printAllUnits(unitList, false);
    }

    public static void selectUnitToPrint(int unitType, JFrame parent) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        UnitViewerDialog viewer = new UnitViewerDialog(parent, unitLoadingDialog, unitType);

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
        ArrayList<Mech> quadList = new ArrayList<Mech>();
        ArrayList<Mech> bipedList = new ArrayList<Mech>();
        ArrayList<Tank> tankList = new ArrayList<Tank>();
        ArrayList<Tank> wigeList = new ArrayList<Tank>();
        ArrayList<Tank> dualTurretList = new ArrayList<Tank>();
        ArrayList<Tank> navalList = new ArrayList<Tank>();
        ArrayList<VTOL> VTOLList = new ArrayList<VTOL>();
        ArrayList<Aero> aeroList = new ArrayList<Aero>();
        ArrayList<ConvFighter> convFighterList = new ArrayList<ConvFighter>();
        ArrayList<FixedWingSupport> fixedWingSupportList = new ArrayList<FixedWingSupport>();
        ArrayList<Dropship> aerodyneList = new ArrayList<Dropship>();
        ArrayList<Dropship> spheroidList = new ArrayList<Dropship>();
        ArrayList<BattleArmor> baList = new ArrayList<BattleArmor>();
        ArrayList<Protomech> protoList = new ArrayList<Protomech>();
        ArrayList<Tank> largeSupportTankList = new ArrayList<Tank>();
        ArrayList<SmallCraft> smallCraftAerodyneList = new ArrayList<SmallCraft>();
        ArrayList<SmallCraft> smallCraftSpheroidList = new ArrayList<SmallCraft>();

        for (Entity unit : loadedUnits) {
            if (unit instanceof QuadMech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);

                quadList.add((Mech) unit);
            } else if (unit instanceof BipedMech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);

                bipedList.add((Mech) unit);
            } else if ((unit instanceof LargeSupportTank) || ((unit instanceof Tank) && (unit.getMovementMode() != EntityMovementMode.VTOL) && ((Tank)unit).isSuperHeavy())) {
                largeSupportTankList.add((Tank)unit);
            } else if (unit instanceof VTOL) {
                VTOLList.add((VTOL) unit);
            } else if (unit.getMovementMode() == EntityMovementMode.WIGE) {
                wigeList.add((Tank) unit);
            } else if ((unit.getMovementMode() == EntityMovementMode.NAVAL) || (unit.getMovementMode() == EntityMovementMode.SUBMARINE) || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL)) {
                navalList.add((Tank) unit);
            } else if (unit instanceof Tank) {
                if (!((Tank) unit).hasNoDualTurret()) {
                    dualTurretList.add((Tank) unit);
                } else {
                    tankList.add((Tank) unit);
                }
            } else if (unit instanceof Aero) {
                if (unit instanceof Dropship) {
                    if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                        aerodyneList.add((Dropship) unit);
                    } else {
                        spheroidList.add((Dropship) unit);
                    }
                } else if (unit instanceof FixedWingSupport) {
                    fixedWingSupportList.add((FixedWingSupport) unit);
                } else if (unit instanceof ConvFighter) {
                    convFighterList.add((ConvFighter) unit);
                } else if (unit instanceof SmallCraft) {
                    if (unit.getMovementMode() == EntityMovementMode.AERODYNE) {
                        smallCraftAerodyneList.add((SmallCraft) unit);
                    } else {
                        smallCraftSpheroidList.add((SmallCraft) unit);
                    }
                } else {
                    aeroList.add((Aero) unit);
                }
            } else if (unit instanceof BattleArmor) {
                baList.add((BattleArmor) unit);
            } else if (unit instanceof Protomech) {
                protoList.add((Protomech) unit);
            } else {
                return false;
            }
        }

        PrinterJob masterPrintJob = PrinterJob.getPrinterJob();
        if (!masterPrintJob.printDialog()) {
            return true;
        }

        if (bipedList.size() > 0) {
            PrintMech printMech = new PrintMech(bipedList, masterPrintJob);

            printMech.print();
        }

        if (quadList.size() > 0) {
            PrintQuad printQuad = new PrintQuad(quadList, masterPrintJob);

            printQuad.print();
        }

        if (tankList.size() > 0) {
            PrintVehicle printTank = new PrintVehicle(tankList, singlePrint, masterPrintJob);

            printTank.print();
        }

        if (wigeList.size() > 0) {
            PrintWiGE printWiGE = new PrintWiGE(wigeList, singlePrint, masterPrintJob);

            printWiGE.print();
        }

        if (navalList.size() > 0) {
            PrintNavalVehicle printNaval = new PrintNavalVehicle(navalList, masterPrintJob);

            printNaval.print();
        }

        if (dualTurretList.size() > 0) {
            PrintDualTurretVehicle printDualTurret = new PrintDualTurretVehicle(dualTurretList, singlePrint, masterPrintJob);

            printDualTurret.print();
        }

        if (aeroList.size() > 0) {
            PrintAero printAero = new PrintAero(aeroList, masterPrintJob);
            printAero.print();
        }

        if (fixedWingSupportList.size() > 0) {
            PrintFixedWingSupport printFixedWingSupport = new PrintFixedWingSupport(fixedWingSupportList, masterPrintJob);
            printFixedWingSupport.print();
        }

        if (convFighterList.size() > 0) {
            PrintConventionalFighter printConventionalFighter = new PrintConventionalFighter(convFighterList, masterPrintJob);
            printConventionalFighter.print();
        }

        if (aerodyneList.size() > 0) {
            PrintAerodyne printAerodyne = new PrintAerodyne(aerodyneList, masterPrintJob);
            printAerodyne.print();
        }

        if (spheroidList.size() > 0) {
            PrintSpheroid printSpheroid = new PrintSpheroid(spheroidList, masterPrintJob);
            printSpheroid.print();
        }

        if (VTOLList.size() > 0) {
            PrintVTOL printVTOL = new PrintVTOL(VTOLList, masterPrintJob);

            printVTOL.print();
        }

        if (baList.size() > 0) {
            PrintBattleArmor printBA = new PrintBattleArmor(baList, masterPrintJob);

            printBA.print();
        }

        if (protoList.size() > 0) {
            PrintProtomech printProto = new PrintProtomech(protoList, masterPrintJob);

            printProto.print();
        }

        if (largeSupportTankList.size() > 0) {
            PrintLargeSupportVehicle sp = new PrintLargeSupportVehicle(largeSupportTankList, singlePrint, masterPrintJob);
            sp.print();
        }

        if (smallCraftAerodyneList.size() > 0) {
            PrintSmallCraftAerodyne sp = new PrintSmallCraftAerodyne(smallCraftAerodyneList, masterPrintJob);
            sp.print();
        }

        if (smallCraftSpheroidList.size() > 0) {
            PrintSmallCraftSpheroid sp = new PrintSmallCraftSpheroid(smallCraftSpheroidList, masterPrintJob);
            sp.print();
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

        item = new JMenuItem("From MUL(Single Unit Per RS)");
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
        UnitViewerDialog viewer = new UnitViewerDialog(parent, unitLoadingDialog, -1);

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