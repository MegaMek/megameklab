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

package megameklab.com.ui.views;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import megameklab.com.ui.util.RefreshListener;

import megamek.common.Mech;

public class ArmorView extends JPanel implements KeyListener {

    /**
     * 
     */
    private static final long serialVersionUID = 799195356642563937L;

    // private EntityVerifier entityVerifier = new EntityVerifier(new
    // File("data/mechfiles/UnitVerifierOptions.xml"));
    private Mech unit;
    // private TestMech testMech;

    private JPanel mainPanel = new JPanel();

    private JPanel headPanel = new JPanel();
    private JPanel torsoPanel = new JPanel();
    private JPanel legPanel = new JPanel();

    private JPanel laPanel = new JPanel();
    private JPanel raPanel = new JPanel();
    private JPanel llPanel = new JPanel();
    private JPanel rlPanel = new JPanel();
    private JPanel ltPanel = new JPanel();
    private JPanel rtPanel = new JPanel();
    private JPanel ctPanel = new JPanel();

    private JTextField laArmorField = new JTextField(2);
    private JTextField raArmorField = new JTextField(2);
    private JTextField llArmorField = new JTextField(2);
    private JTextField rlArmorField = new JTextField(2);
    private JTextField ltArmorField = new JTextField(2);
    private JTextField rtArmorField = new JTextField(2);
    private JTextField ctArmorField = new JTextField(2);
    private JTextField hdArmorField = new JTextField(2);
    private JTextField rtrArmorField = new JTextField(2);
    private JTextField ltrArmorField = new JTextField(2);
    private JTextField ctrArmorField = new JTextField(2);

    private JLabel laArmorMaxLabel = new JLabel();
    private JLabel raArmorMaxLabel = new JLabel();
    private JLabel llArmorMaxLabel = new JLabel();
    private JLabel rlArmorMaxLabel = new JLabel();
    private JLabel ltArmorMaxLabel = new JLabel();
    private JLabel rtArmorMaxLabel = new JLabel();
    private JLabel ctArmorMaxLabel = new JLabel();

    private JLabel laISLabel = new JLabel();
    private JLabel raISLabel = new JLabel();
    private JLabel llISLabel = new JLabel();
    private JLabel rlISLabel = new JLabel();
    private JLabel rtISLabel = new JLabel();
    private JLabel ltISLabel = new JLabel();
    private JLabel ctISLabel = new JLabel();
    private JLabel hdISLabel = new JLabel();

    private RefreshListener refresh;

    public ArmorView(Mech unit) {

        this.unit = unit;

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.X_AXIS));
        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        laPanel.setLayout(new BoxLayout(laPanel, BoxLayout.Y_AXIS));
        raPanel.setLayout(new BoxLayout(raPanel, BoxLayout.Y_AXIS));
        ltPanel.setLayout(new BoxLayout(ltPanel, BoxLayout.Y_AXIS));
        ctPanel.setLayout(new BoxLayout(ctPanel, BoxLayout.Y_AXIS));
        rtPanel.setLayout(new BoxLayout(rtPanel, BoxLayout.Y_AXIS));

        mainPanel.add(headPanel);

        laPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        raPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        ltPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        rtPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
        ctPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));

        torsoPanel.add(laPanel);
        torsoPanel.add(ltPanel);
        torsoPanel.add(ctPanel);
        torsoPanel.add(rtPanel);
        torsoPanel.add(raPanel);
        mainPanel.add(torsoPanel);

        legPanel.add(llPanel);
        legPanel.add(rlPanel);
        mainPanel.add(legPanel);

        laArmorField.setToolTipText("Front Armor");
        raArmorField.setToolTipText("Front Armor");
        llArmorField.setToolTipText("Front Armor");
        rlArmorField.setToolTipText("Front Armor");
        ltArmorField.setToolTipText("Front Armor");
        rtArmorField.setToolTipText("Front Armor");
        ctArmorField.setToolTipText("Front Armor");
        hdArmorField.setToolTipText("Front Armor");
        rtrArmorField.setToolTipText("Rear Armor");
        ltrArmorField.setToolTipText("Rear Armor");
        ctrArmorField.setToolTipText("Rear Armor");

        laArmorField.setName(Integer.toString(Mech.LOC_LARM));
        raArmorField.setName(Integer.toString(Mech.LOC_RARM));
        llArmorField.setName(Integer.toString(Mech.LOC_LLEG));
        rlArmorField.setName(Integer.toString(Mech.LOC_RLEG));
        ltArmorField.setName(Integer.toString(Mech.LOC_LT));
        rtArmorField.setName(Integer.toString(Mech.LOC_RT));
        ctArmorField.setName(Integer.toString(Mech.LOC_CT));
        hdArmorField.setName(Integer.toString(Mech.LOC_HEAD));
        rtrArmorField.setName(Integer.toString(Mech.LOC_RT));
        ltrArmorField.setName(Integer.toString(Mech.LOC_LT));
        ctrArmorField.setName(Integer.toString(Mech.LOC_CT));

        torsoPanel.setLayout(new BoxLayout(torsoPanel, BoxLayout.X_AXIS));
        legPanel.setLayout(new BoxLayout(legPanel, BoxLayout.X_AXIS));

        JPanel masterPanel;

        synchronized (unit) {
            for (int location = 0; location < unit.locations(); location++) {

                switch (location) {
                case Mech.LOC_HEAD:
                    masterPanel = new JPanel();
                    JPanel topPanel = new JPanel();
                    JPanel bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(hdArmorField);
                    topPanel.add(new JLabel("/ 9", JLabel.TRAILING));
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    bottomPanel.add(hdISLabel);
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(masterPanel);
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    headPanel.add(new JPanel());
                    break;
                case Mech.LOC_LARM:
                    masterPanel = new JPanel();
                    masterPanel.add(laArmorField);
                    masterPanel.add(new JLabel("/", JLabel.TRAILING));
                    masterPanel.add(laArmorMaxLabel);
                    laPanel.add(masterPanel);
                    laPanel.add(laISLabel);
                    break;
                case Mech.LOC_RARM:
                    masterPanel = new JPanel();
                    masterPanel.add(raArmorField);
                    masterPanel.add(new JLabel("/", JLabel.TRAILING));
                    masterPanel.add(raArmorMaxLabel);
                    raPanel.add(masterPanel);
                    raPanel.add(raISLabel);
                    break;
                case Mech.LOC_CT:
                    masterPanel = new JPanel();
                    masterPanel.add(ctArmorField);
                    masterPanel.add(new JLabel("(", JLabel.TRAILING));
                    masterPanel.add(ctrArmorField);
                    masterPanel.add(new JLabel(")/", JLabel.TRAILING));
                    masterPanel.add(ctArmorMaxLabel);
                    ctPanel.add(masterPanel);
                    ctPanel.add(ctISLabel);
                    break;
                case Mech.LOC_LT:
                    masterPanel = new JPanel();
                    masterPanel.add(ltArmorField);
                    masterPanel.add(new JLabel("("));
                    masterPanel.add(ltrArmorField);
                    masterPanel.add(new JLabel(")/", JLabel.TRAILING));
                    masterPanel.add(ltArmorMaxLabel);
                    ltPanel.add(masterPanel);
                    ltPanel.add(ltISLabel);
                    break;
                case Mech.LOC_RT:
                    masterPanel = new JPanel();
                    masterPanel.add(rtArmorField);
                    masterPanel.add(new JLabel("("));
                    masterPanel.add(rtrArmorField);
                    masterPanel.add(new JLabel(")/", JLabel.TRAILING));
                    masterPanel.add(rtArmorMaxLabel);
                    rtPanel.add(masterPanel);
                    rtPanel.add(rtISLabel);
                    break;
                case Mech.LOC_LLEG:
                    masterPanel = new JPanel();
                    topPanel = new JPanel();
                    bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(llArmorField);
                    topPanel.add(new JLabel("/", JLabel.TRAILING));
                    topPanel.add(llArmorMaxLabel);
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    bottomPanel.add(llISLabel);
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    llPanel.add(new JPanel());
                    llPanel.add(new JPanel());
                    llPanel.add(masterPanel);
                    llPanel.add(new JPanel());
                    llPanel.add(new JPanel());
                    break;
                case Mech.LOC_RLEG:
                    masterPanel = new JPanel();
                    topPanel = new JPanel();
                    bottomPanel = new JPanel();
                    masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
                    topPanel.add(rlArmorField);
                    topPanel.add(new JLabel("/", JLabel.TRAILING));
                    topPanel.add(rlArmorMaxLabel);
                    masterPanel.add(topPanel);
                    bottomPanel = new JPanel();
                    bottomPanel.add(rlISLabel);
                    masterPanel.add(bottomPanel);
                    masterPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE.brighter(), Color.blue.darker()));
                    rlPanel.add(new JPanel());
                    rlPanel.add(new JPanel());
                    rlPanel.add(masterPanel);
                    rlPanel.add(new JPanel());
                    rlPanel.add(new JPanel());
                    break;
                }
            }
        }

        this.add(mainPanel);
        refresh();
        addAllListeners();
    }

    public void refresh() {

        // testMech = new TestMech(unit, entityVerifier.mechOption, null);

        for (int location = 0; location < unit.locations(); location++) {

            int maxArmor = unit.getOInternal(location) * 2;
            switch (location) {
            case Mech.LOC_HEAD:
                hdArmorField.setText(unit.getArmorString(location));
                hdISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) > 9)
                    hdArmorField.setBackground(Color.RED);
                else
                    hdArmorField.setBackground(Color.white);
                break;
            case Mech.LOC_LARM:
                laArmorField.setText(unit.getArmorString(location));
                laISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) > maxArmor)
                    laArmorField.setBackground(Color.RED);
                else
                    laArmorField.setBackground(Color.white);
                laArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_RARM:
                raArmorField.setText(unit.getArmorString(location));
                raISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) > maxArmor)
                    raArmorField.setBackground(Color.RED);
                else
                    raArmorField.setBackground(Color.white);
                raArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_CT:
                ctArmorField.setText(unit.getArmorString(location));
                ctrArmorField.setText(unit.getArmorString(location, true));
                ctISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) + unit.getArmor(location, true) > maxArmor)
                    ctArmorField.setBackground(Color.RED);
                else
                    ctArmorField.setBackground(Color.white);
                ctArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_LT:
                ltArmorField.setText(unit.getArmorString(location));
                ltrArmorField.setText(unit.getArmorString(location, true));
                ltISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) + unit.getArmor(location, true) > maxArmor)
                    ltArmorField.setBackground(Color.RED);
                else
                    ltArmorField.setBackground(Color.white);
                ltArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_RT:
                rtArmorField.setText(unit.getArmorString(location));
                rtrArmorField.setText(unit.getArmorString(location, true));
                rtISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) + unit.getArmor(location, true) > maxArmor)
                    rtArmorField.setBackground(Color.RED);
                else
                    rtArmorField.setBackground(Color.white);
                rtArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_LLEG:
                llArmorField.setText(unit.getArmorString(location));
                llISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) > maxArmor)
                    llArmorField.setBackground(Color.RED);
                else
                    llArmorField.setBackground(Color.white);
                llArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            case Mech.LOC_RLEG:
                rlArmorField.setText(unit.getArmorString(location));
                rlISLabel.setText(unit.getInternalString(location));
                if (unit.getArmor(location) > maxArmor)
                    rlArmorField.setBackground(Color.RED);
                else
                    rlArmorField.setBackground(Color.white);
                rlArmorMaxLabel.setText(Integer.toString(maxArmor));
                break;
            }

        }
    }

    private void addAllListeners() {
        laArmorField.addKeyListener(this);
        raArmorField.addKeyListener(this);
        llArmorField.addKeyListener(this);
        rlArmorField.addKeyListener(this);
        ltArmorField.addKeyListener(this);
        rtArmorField.addKeyListener(this);
        ctArmorField.addKeyListener(this);
        hdArmorField.addKeyListener(this);
        rtrArmorField.addKeyListener(this);
        ltrArmorField.addKeyListener(this);
        ctrArmorField.addKeyListener(this);
    }

    private void removeAllListeners() {
        laArmorField.removeKeyListener(this);
        raArmorField.removeKeyListener(this);
        llArmorField.removeKeyListener(this);
        rlArmorField.removeKeyListener(this);
        ltArmorField.removeKeyListener(this);
        rtArmorField.removeKeyListener(this);
        ctArmorField.removeKeyListener(this);
        hdArmorField.removeKeyListener(this);
        rtrArmorField.removeKeyListener(this);
        ltrArmorField.removeKeyListener(this);
        ctrArmorField.removeKeyListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        addAllListeners();

        try {
            JTextField field = (JTextField) arg0.getSource();
            int location = Integer.parseInt(field.getName());
            int value = Integer.parseInt(field.getText());
            switch (location) {
            case Mech.LOC_CT:
                if (field.equals(ctrArmorField))
                    unit.initializeRearArmor(value, location);
                else
                    unit.initializeArmor(value, location);
                break;
            case Mech.LOC_RT:
                if (field.equals(rtrArmorField))
                    unit.initializeRearArmor(value, location);
                else
                    unit.initializeArmor(value, location);
                break;
            case Mech.LOC_LT:
                if (field.equals(ltrArmorField))
                    unit.initializeRearArmor(value, location);
                else
                    unit.initializeArmor(value, location);
                break;
            default:
                unit.initializeArmor(value, location);
                break;
            }
            refresh.refreshStatus();
            refresh();
        } catch (Exception ex) {
        }

        removeAllListeners();
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void allocateArmor(int percent) {
        for (int location = 0; location <= Mech.LOC_LLEG; location++) {
            int IS = (unit.getInternal(location) * 2);
            double maxArmor = (double)IS * (double)percent / 100;

            switch (location) {
            case Mech.LOC_HEAD:
                unit.initializeArmor(9, location);
                break;
            case Mech.LOC_CT:
            case Mech.LOC_LT:
            case Mech.LOC_RT:
                double rear = Math.floor(maxArmor * .25);
                double front = Math.ceil(maxArmor * .75);
                unit.initializeArmor((int)front, location);
                unit.initializeRearArmor((int)rear, location);
                break;
            default:
                unit.initializeArmor((int)maxArmor, location);
                break;
            }
        }

        removeAllListeners();
        refresh();
        addAllListeners();
        refresh.refreshStatus();
    }
}