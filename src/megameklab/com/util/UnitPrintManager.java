/*
 * MegaMekLab - Copyright (C) 2009
 *
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 */

package megameklab.com.util;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.Aero;
import megamek.common.BattleArmor;
import megamek.common.BipedMech;
import megamek.common.Entity;
import megamek.common.EntityListFile;
import megamek.common.Infantry;
import megamek.common.Mech;
import megamek.common.MechFileParser;
import megamek.common.QuadMech;
import megamek.common.Tank;
import megamek.common.VTOL;
import megameklab.com.ui.Aero.Printing.PrintAero;
import megameklab.com.ui.BattleArmor.Printing.PrintBattleArmor;
import megameklab.com.ui.Mek.Printing.PrintMech;
import megameklab.com.ui.Mek.Printing.PrintQuad;
import megameklab.com.ui.VTOL.Printing.PrintVTOL;
import megameklab.com.ui.Vehicle.Printing.PrintVehicle;
import megameklab.com.ui.dialog.UnitViewerDialog;

public class UnitPrintManager {

    public static boolean printEntity(Entity entity) {

        if (entity instanceof Mech) {
            if (entity instanceof QuadMech) {
                ArrayList<Mech> mechList = new ArrayList<Mech>();
                mechList.add((Mech) entity);

                PrintQuad sp = new PrintQuad(mechList);

                sp.print();
            } else {
                ArrayList<Mech> mechList = new ArrayList<Mech>();
                mechList.add((Mech) entity);

                PrintMech sp = new PrintMech(mechList);

                sp.print();
            }
        } else if (entity instanceof Tank) {

            if (entity instanceof VTOL) {
                ArrayList<VTOL> vtolList = new ArrayList<VTOL>();
                vtolList.add((VTOL) entity);

                PrintVTOL sp = new PrintVTOL(vtolList, false);

                sp.print();
            } else {
                ArrayList<Tank> tankList = new ArrayList<Tank>();
                tankList.add((Tank) entity);

                PrintVehicle sp = new PrintVehicle(tankList, false);

                sp.print();

            }
        } else if (entity instanceof Infantry) {
            if (entity instanceof BattleArmor) {
                ArrayList<BattleArmor> baList = new ArrayList<BattleArmor>();
                baList.add((BattleArmor) entity);

                PrintBattleArmor sp = new PrintBattleArmor(baList);

                sp.print();
            }
        } else if (entity instanceof Aero) {
            ArrayList<Aero> aeroList = new ArrayList<Aero>();
            aeroList.add((Aero) entity);

            PrintAero sp = new PrintAero(aeroList);

            sp.print();
        } else {
            System.err.println("Unknown Entity type: " + entity.toString());
            return false;
        }

        return true;
    }

    public static void selectUnitToPrint(int unitType, JFrame parent) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(parent);
        UnitViewerDialog viewer = new UnitViewerDialog(parent, unitLoadingDialog, unitType);

        viewer.run();

        Entity entity = null;

        entity = viewer.getSelectedEntity();

        viewer.setVisible(false);
        viewer.dispose();
        UnitPrintManager.printEntity(entity);
    }

    public static void printMuls(Frame parent, boolean singlePrint) {
        ArrayList<Mech> quadList = new ArrayList<Mech>();
        ArrayList<Mech> bipedList = new ArrayList<Mech>();
        ArrayList<Tank> tankList = new ArrayList<Tank>();
        ArrayList<VTOL> VTOLList = new ArrayList<VTOL>();
        ArrayList<Aero> aeroList = new ArrayList<Aero>();
        ArrayList<BattleArmor> baList = new ArrayList<BattleArmor>();

        FileDialog f = new FileDialog(parent, "Load Mul");
        f.setDirectory(System.getProperty("user.dir"));
        f.setFile("*.mul");
        /*
         * f.setFilenameFilter(new FilenameFilter() { public boolean
         * accept(final File dir, final String name) { return (null != name &&
         * name.endsWith(".mul")); } });
         */

        f.setVisible(true);

        if (f.getFile() != null) {
            Vector<Entity> loadedUnits = new Vector<Entity>();
            try {
                loadedUnits = EntityListFile.loadFrom(new File(f.getDirectory() + f.getFile()));
                loadedUnits.trimToSize();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }

            for (Entity unit : loadedUnits) {
                if (unit instanceof QuadMech) {
                    UnitUtil.removeOneShotAmmo(unit);
                    UnitUtil.expandUnitMounts(unit);

                    quadList.add((Mech) unit);
                } else if (unit instanceof BipedMech) {
                    UnitUtil.removeOneShotAmmo(unit);
                    UnitUtil.expandUnitMounts(unit);

                    bipedList.add((Mech) unit);
                } else if (unit instanceof VTOL) {
                    VTOLList.add((VTOL) unit);
                } else if (unit instanceof Tank) {
                    tankList.add((Tank) unit);
                } else if (unit instanceof Aero) {
                    aeroList.add((Aero) unit);
                } else if (unit instanceof BattleArmor) {
                    baList.add((BattleArmor) unit);
                }
            }

            if (bipedList.size() > 0) {
                PrintMech printMech = new PrintMech(bipedList);

                printMech.print();
            }

            if (quadList.size() > 0) {
                PrintQuad printQuad = new PrintQuad(quadList);

                printQuad.print();
            }

            if (tankList.size() > 0) {
                PrintVehicle printTank = new PrintVehicle(tankList, singlePrint);

                printTank.print();
            }

            if (aeroList.size() > 0) {
                PrintAero printAero = new PrintAero(aeroList);

                printAero.print();
            }

            if (VTOLList.size() > 0) {
                PrintVTOL printVTOL = new PrintVTOL(VTOLList, singlePrint);

                printVTOL.print();
            }

            if (baList.size() > 0) {
                PrintBattleArmor printBA = new PrintBattleArmor(baList);

                printBA.print();
            }
        }
    }

    public static JMenu printMenu(final JFrame parent, JMenuItem item) {
        JMenu printMenu = new JMenu("Print");
        printMenu.setMnemonic('P');

        printMenu.add(item);

        item = new JMenuItem("Other Unit");
        item.setMnemonic('O');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printSelectedUnit(parent);
            }
        });
        printMenu.add(item);

        item = new JMenuItem("From File");
        item.setMnemonic('i');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printUnitFile(parent);
            }
        });
        printMenu.add(item);

        printMenu.addSeparator();
        item = new JMenuItem("From MUL");
        item.setMnemonic('M');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UnitPrintManager.printMuls(parent, false);
            }
        });
        printMenu.add(item);

        item = new JMenuItem("From MUL(Single Unit Per RS)");
        item.setMnemonic('R');
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

        viewer.run();

        viewer.setVisible(false);
        Entity entity = viewer.getSelectedEntity();

        if (entity != null) {
            boolean printed = UnitPrintManager.printEntity(entity);
            viewer.dispose();

            if (!printed) {
                JOptionPane.showMessageDialog(parent, "Unable to print that unit type!");
            }
        }
    }

    public static void printUnitFile(JFrame parent) {
        FileDialog fDialog = new FileDialog(parent, "Load Unit", FileDialog.LOAD);

        String filePathName = System.getProperty("user.dir").toString() + "/data/mechfiles/";

        fDialog.setDirectory(filePathName);
        fDialog.setFile("*.blk;*.mtf");
        fDialog.setLocationRelativeTo(parent);

        fDialog.setVisible(true);

        if (fDialog.getFile() == null) {
            return;
        }

        try {
            Entity tempEntity = new MechFileParser(new File(fDialog.getDirectory(), fDialog.getFile())).getEntity();

            UnitPrintManager.printEntity(tempEntity);
            // UnitUtil.updateLoadedTank(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}