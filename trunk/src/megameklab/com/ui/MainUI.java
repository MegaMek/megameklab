/*
 * MegaMekLab - Copyright (C) 2008 
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

package megameklab.com.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import megameklab.com.ui.dialog.UnitViewerDialog;
import megameklab.com.ui.tabs.ArmorTab;
import megameklab.com.ui.tabs.BuildTab;
import megameklab.com.ui.tabs.EquipmentTab;
import megameklab.com.ui.tabs.StructureTab;
import megameklab.com.ui.tabs.WeaponTab;
import megameklab.com.ui.util.RefreshListener;
import megameklab.com.ui.util.SaveMechToMTF;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.common.BipedMech;
import megamek.common.Engine;
import megamek.common.EquipmentType;
import megamek.common.Mech;
import megamek.common.QuadMech;
import megamek.common.TechConstants;

public class MainUI extends JFrame implements RefreshListener {

    /**
     * 
     */
    private static final long serialVersionUID = -5836932822468918198L;
    private static final String VERSION = "0.0.0.5-dev";
    private static int numberOfPages = 1;

    Mech entity = null;
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JTabbedPane ConfigPane = new JTabbedPane(SwingConstants.TOP);
    JPanel contentPane;
    private StructureTab structureTab;
    private ArmorTab armorTab;
    private EquipmentTab equipmentTab;
    private WeaponTab weaponTab;
    private BuildTab buildTab;
    private Header header;
    private StatusBar statusbar;
    JPanel masterPanel = new JPanel();
    JScrollPane scroll = new JScrollPane();

    public MainUI() {

        System.out.println("Staring MegaMekLab version: " + VERSION);
        file.setMnemonic('F');
        JMenuItem item = new JMenuItem();

        item.setText("Load");
        item.setMnemonic('L');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuLoadEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem();
        item.setText("Save");
        item.setMnemonic('S');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SaveMechToMTF.getInstance(entity, entity.getChassis() + " " + entity.getModel() + ".mtf").save();
            }
        });
        file.add(item);

        item = new JMenuItem("Reset");
        item.setMnemonic('R');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuResetEntity_actionPerformed(e);
            }
        });
        file.add(item);

        item = new JMenuItem("Print");
        item.setMnemonic('P');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuPrint_actionPerformed(e);
            }
        });
        file.add(item);

        file.addSeparator();

        item = new JMenuItem();
        item.setText("Exit");
        item.setMnemonic('x');
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuExit_actionPerformed(e);
            }
        });
        file.add(item);
        menuBar.add(file);

        setLocation(getLocation().x + 10, getLocation().y);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

        // ConfigPane.setMinimumSize(new Dimension(300, 300));
        createNewMech(false);
        this.setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
        setJMenuBar(menuBar);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(masterPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        this.add(scroll);
        Dimension maxSize = new Dimension(800, 600);
        // masterPanel.setPreferredSize(new Dimension(600,400));
        scroll.setPreferredSize(maxSize);
        setResizable(true);
        setSize(maxSize);
        setMaximumSize(maxSize);
        setPreferredSize(maxSize);
        setExtendedState(Frame.NORMAL);

        reloadTabs();
        this.setVisible(true);
        repaint();
        refreshAll();
    }

    private void loadUnit() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(this);
        UnitViewerDialog viewer = new UnitViewerDialog(this, unitLoadingDialog, 0);
        viewer.setVisible(true);

        if (viewer != null) {

            if (!(viewer.getSelectedEntity() instanceof Mech))
                return;
            entity = (Mech) viewer.getSelectedEntity();
            viewer.setVisible(false);
            viewer.dispose();
            this.setVisible(false);
            reloadTabs();
            this.setVisible(true);
            this.repaint();
            refreshAll();
        }
    }

    public void jMenuLoadEntity_actionPerformed(ActionEvent event) {
        loadUnit();
    }

    public void jMenuResetEntity_actionPerformed(ActionEvent event) {
        createNewMech(false);
        structureTab.updateMech(entity);
        refreshStructure();
        refreshAll();
    }

    public void jMenuPrint_actionPerformed(ActionEvent event) {

        SimplePrint sp = new SimplePrint();

        sp.print();

        /*
         * Grab the image. fImg = getToolkit().getImage(fImageName); JLabel printLabel = new JLabel(); printLabel.setIcon(new ImageIcon(fImg)); JFrame printFrame = new JFrame(); printFrame.add(printLabel); printFrame.pack(); printFrame.repaint(); printFrame.setVisible(true); // Create the PrintJob object PrintJob pjb = printFrame.getToolkit().getPrintJob(printFrame, "Print Test", null); if (pjb != null) { Graphics pg = pjb.getGraphics(); if (pg != null) { paint(pg); // Paint all components on the frame pg.dispose(); // flush page } pjb.end(); } printFrame.setVisible(false);
         */

    }

    public void reloadTabs() {
        masterPanel.removeAll();
        ConfigPane.removeAll();

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        structureTab = new StructureTab(entity);
        armorTab = new ArmorTab(entity);
        header = new Header(entity);
        statusbar = new StatusBar(entity);
        equipmentTab = new EquipmentTab(entity);
        weaponTab = new WeaponTab(entity);
        buildTab = new BuildTab(entity, equipmentTab, weaponTab);
        header.addRefreshedListener(this);
        structureTab.addRefreshedListener(this);
        armorTab.addRefreshedListener(this);
        equipmentTab.addRefreshedListener(this);
        weaponTab.addRefreshedListener(this);
        buildTab.addRefreshedListener(this);

        ConfigPane.addTab("Structure", structureTab);
        ConfigPane.addTab("Armor", armorTab);
        ConfigPane.addTab("Equipment", equipmentTab);
        ConfigPane.addTab("Weapons", weaponTab);
        ConfigPane.addTab("Build", buildTab);

        masterPanel.add(header);
        masterPanel.add(ConfigPane);
        masterPanel.add(statusbar);

        refreshHeader();
        this.repaint();
    }

    public void jMenuExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    public void createNewMech(boolean isQuad) {

        if (isQuad) {
            entity = new QuadMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD);
        } else {
            entity = new BipedMech(Mech.GYRO_STANDARD, Mech.COCKPIT_STANDARD);
        }

        entity.setYear(2075);
        entity.setTechLevel(TechConstants.T_INTRO_BOXSET);
        entity.setWeight(25);
        entity.setEngine(new Engine(25, Engine.NORMAL_ENGINE, 0));
        entity.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        entity.setStructureType(EquipmentType.T_STRUCTURE_STANDARD);

        entity.addGyro();
        entity.addEngineCrits();
        entity.addCockpit();
        entity.addEngineSinks(entity.getEngine().integralHeatSinkCapacity(), false);

        entity.autoSetInternal();
        for (int loc = 0; loc <= Mech.LOC_LLEG; loc++) {
            entity.setArmor(0, loc);
            entity.setArmor(0, loc, true);
        }

        entity.setChassis("New");
        entity.setModel("Mek");

    }

    public void refreshAll() {

        if ((structureTab.isQuad() && !(entity instanceof QuadMech)) || (!structureTab.isQuad() && entity instanceof QuadMech)) {
            createNewMech(structureTab.isQuad());
            structureTab.updateMech(entity);
            armorTab.updateMech(entity);
            equipmentTab.updateMech(entity);
            weaponTab.updateMech(entity);
            buildTab.updateMech(entity);
            statusbar.updateMech(entity);
            header.updateMech(entity);
        }
        statusbar.refresh();
        structureTab.refresh();
        armorTab.refresh();
        equipmentTab.refresh();
        weaponTab.refresh();
        buildTab.refresh();
    }

    public void refreshArmor() {
        armorTab.refresh();
    }

    public void refreshBuild() {
        buildTab.refresh();
    }

    public void refreshEquipment() {
        equipmentTab.refresh();

    }

    public void refreshHeader() {
        this.setTitle(entity.getChassis() + " " + entity.getModel() + ".mtf");
    }

    public void refreshStatus() {
        statusbar.refresh();
    }

    public void refreshStructure() {
        structureTab.refresh();
    }

    public void refreshWeapons() {
        weaponTab.refresh();
    }

}

class SimplePrint implements Printable, ImageObserver {

    protected Image awtImage = null;
    String fImageName = "./data/images/TWBiPed.JPG";

    public SimplePrint() {
        awtImage = Toolkit.getDefaultToolkit().createImage(fImageName);
        System.out.println("Width: " + awtImage.getWidth(null));
        System.out.println("Height: " + awtImage.getHeight(null));
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1)
            return Printable.NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) graphics;
        // f.setPaper(this.paper);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        if (awtImage != null) {
            printImage(g2d, awtImage);
            return Printable.PAGE_EXISTS;
        } else
            return Printable.NO_SUCH_PAGE;
    }

    public void printImage(Graphics2D g2d, Image image) {
        System.out.println("printImage(Graphics2D g2d, Image image)");
        if ((image == null) || (g2d == null))
            return;
        int x = 0;
        int y = 0;
        g2d.drawImage(image, x, y, this);
        Font font = new Font("Serif", Font.PLAIN, 30);
        g2d.setFont(font);
        g2d.drawString("Batch Header - SALES", 100, 100);
        g2d.drawString("SALES Batch Header - 02353 BT: 00", 5, 500);
        font = new Font("Serif", Font.BOLD, 30);
        g2d.setFont(font);
        g2d.drawString("Batch Number - 02353", 100, 135);
        font = new Font("Serif", Font.BOLD, 15);
        g2d.setFont(font);
        g2d.drawString("12997001.063.00", 85, 155);
        g2d.drawString("03/04/2003 11:06 AM", 225, 155);
        g2d.drawString("12997001.063.00", 125, 525);
        g2d.drawString("03/04/2003 11:06 AM", 255, 525);
        font = new Font("Serif", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.drawString("RECEIPT DATE :", 15, 555);
        g2d.drawString("SCREENER ID :", 15, 575);
        g2d.drawString("TRANS CODE :", 15, 595);
        g2d.drawString("TOTAL COUPONS :", 15, 615);
        g2d.drawString("TOTAL CHECKS :", 215, 615);
        g2d.drawString("ITEM VALUE :", 15, 635);

        g2d.drawString("SSS4S012S02352S0228003S9997S00S02S0000000300S4", 85, 655);

        // g2d.drawString("SSS4S012S02352S0228003S9997S00S02S0000000300S4", printLoc1.x, printLoc1.y);

    }

    public void print() {

        // Image fImg;

        FileInputStream recordSheetStream = null;
        try {
            recordSheetStream = new FileInputStream(fImageName);
            if (recordSheetStream == null) {
                return;
            }

            DocFlavor myFormat = DocFlavor.INPUT_STREAM.JPEG;
            // Create a Doc
            Doc myDoc = new SimpleDoc(recordSheetStream, myFormat, null);
            PrinterJob pj = PrinterJob.getPrinterJob();

            if (pj.printDialog()) {
                Paper paper = new Paper();
                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.defaultPage();
                paper.setImageableArea(72, 72, 750, 900);
                paper.setSize(800, 1000);
                pageFormat.setPaper(paper);
                pageFormat.setOrientation(PageFormat.PORTRAIT);

                pj.setPrintable(this, pageFormat);
                // lookupPrintServices(myFormat, aset);
                // Create a print job from one of the print services
                // if (services.length > 0) {
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                aset.add(new Copies(pj.getCopies()));
                PrintService services = pj.getPrintService();
                // lookupPrintServices(myFormat, aset);
                // Create a print job from one of the print services
                // if (services.length > 0) {
                DocPrintJob job = services.createPrintJob();
                job.print(myDoc, aset);
                //pj.print();

            }
            recordSheetStream.close();
        } catch (FileNotFoundException ffne) {
            ffne.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        return false;
    }

}