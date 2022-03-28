/*
 * Copyright (c) 2019-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MekHQ.
 *
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MekHQ. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.supportVehicle;

import megamek.common.*;
import megamek.common.util.EncodeControl;
import megamek.common.verifier.TestSupportVehicle;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.SVBuildListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for adding crew seating and optional quarters
 */
public class SVCrewView extends BuildView implements ChangeListener {

    private final List<SVBuildListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(SVBuildListener l) {
        listeners.add(l);
    }

    public void removeListener(SVBuildListener l) {
        listeners.remove(l);
    }

    private final JLabel txtReqBaseCrew = new JLabel();
    private final JLabel txtReqGunners = new JLabel();
    private final JLabel txtReqOther = new JLabel();
    private final JLabel txtReqOfficers = new JLabel();
    private final JLabel txtTotalMinCrew = new JLabel();
    private final JLabel lblSeatingHeader = new JLabel();
    private final JLabel lblPodSeating = new JLabel();
    private final JLabel lblPodQuarters = new JLabel();

    private final JSpinner spnStandardSeats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnStandardSeatsPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtStdSeatWeight = new JLabel("", SwingConstants.CENTER);
    private final JSpinner spnPillionSeats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnPillionSeatsPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtPillionWeight = new JLabel("", SwingConstants.CENTER);
    private final JSpinner spnEjectionSeats = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnEjectionSeatsPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtEjectionWeight = new JLabel("", SwingConstants.CENTER);

    private final JSpinner spnFirstClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnFirstClassPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtFirstClassWeight = new JLabel("", SwingConstants.CENTER);
    private final JLabel txtFirstClassSlots = new JLabel("", SwingConstants.CENTER);
    private final JSpinner spnSecondClass = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnSecondClassPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtSecondClassWeight = new JLabel("", SwingConstants.CENTER);
    private final JLabel txtSecondClassSlots = new JLabel("", SwingConstants.CENTER);
    private final JSpinner spnCrewQuarters = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnCrewQuartersPod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtCrewQuartersWeight = new JLabel("", SwingConstants.CENTER);
    private final JLabel txtCrewQuartersSlots = new JLabel("", SwingConstants.CENTER);
    private final JSpinner spnSteerage = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JSpinner spnSteeragePod = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
    private final JLabel txtSteerageWeight = new JLabel("", SwingConstants.CENTER);
    private final JLabel txtSteerageSlots = new JLabel("", SwingConstants.CENTER);

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views", new EncodeControl());

    public SVCrewView() {
        initUi();
    }

    private void initUi() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(createLabel(resourceMap, "lblMinimumCrew", "SVCrewView.lblMinimumCrew.text", labelSize), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblReqBaseCrew", "SVCrewView.txtReqBaseCrew.text",
                "SVCrewView.txtReqBaseCrew.tooltip", labelSize), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        setFieldSize(txtReqBaseCrew, spinnerSize);
        add(txtReqBaseCrew, gbc);
        txtReqBaseCrew.setToolTipText(resourceMap.getString("SVCrewView.txtReqBaseCrew.tooltip"));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblReqGunners", "SVCrewView.txtReqGunners.text",
                "SVCrewView.txtReqGunners.tooltip", labelSize), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        setFieldSize(txtReqGunners, spinnerSize);
        add(txtReqGunners, gbc);
        txtReqGunners.setToolTipText(resourceMap.getString("SVCrewView.txtReqGunners.tooltip"));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblReqOther", "SVCrewView.txtReqOther.text",
                "SVCrewView.txtReqOther.tooltip", labelSize), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        setFieldSize(txtReqOther, spinnerSize);
        add(txtReqOther, gbc);
        txtReqOther.setToolTipText(resourceMap.getString("SVCrewView.txtReqOther.tooltip"));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblReqOfficers", "SVCrewView.txtReqOfficers.text",
                "SVCrewView.txtReqOfficers.tooltip", labelSize), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        setFieldSize(txtReqOfficers, spinnerSize);
        add(txtReqOfficers, gbc);
        txtReqOfficers.setToolTipText(resourceMap.getString("SVCrewView.txtReqOfficers.tooltip"));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblTotalMinCrew", "SVCrewView.txtTotalMinCrew.text", labelSize), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        setFieldSize(txtTotalMinCrew, spinnerSize);
        add(txtTotalMinCrew, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(createLabel(resourceMap, "lblSeatingHeader", "SVCrewView.lblSeatingHeader.extraSeats", labelSizeLg), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        lblPodSeating.setText(resourceMap.getString("SVCrewView.lblPodQuarters.text"));
        add(lblPodSeating, gbc);
        gbc.gridx = 3;
        add(createLabel(resourceMap, "lblSeatingWeight", "SVCrewView.lblSeatingWeight.text", labelSize), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblStandardSeats", "SVCrewView.spnStandardSeats.text",
                "SVCrewView.spnStandardSeats.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnStandardSeats, spinnerSize);
        spnStandardSeats.setToolTipText(resourceMap.getString("SVCrewView.spnStandardSeats.tooltip"));
        add(spnStandardSeats, gbc);
        spnStandardSeats.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnStandardSeatsPod, spinnerSize);
        spnStandardSeatsPod.setToolTipText(resourceMap.getString("SVCrewView.spnStandardSeats.tooltip"));
        add(spnStandardSeatsPod, gbc);
        spnStandardSeatsPod.addChangeListener(this);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        setFieldSize(txtStdSeatWeight, spinnerSize);
        add(txtStdSeatWeight, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblPillionSeats", "SVCrewView.spnPillionSeats.text",
                "SVCrewView.spnPillionSeats.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnPillionSeats, spinnerSize);
        spnPillionSeats.setToolTipText(resourceMap.getString("SVCrewView.spnPillionSeats.tooltip"));
        add(spnPillionSeats, gbc);
        spnPillionSeats.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnPillionSeatsPod, spinnerSize);
        spnPillionSeatsPod.setToolTipText(resourceMap.getString("SVCrewView.spnPillionSeats.tooltip"));
        add(spnPillionSeatsPod, gbc);
        spnPillionSeatsPod.addChangeListener(this);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        setFieldSize(txtPillionWeight, spinnerSize);
        add(txtPillionWeight, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblEjectionSeats", "SVCrewView.spnEjectionSeats.text",
                "SVCrewView.spnEjectionSeats.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnEjectionSeats, spinnerSize);
        spnEjectionSeats.setToolTipText(resourceMap.getString("SVCrewView.spnEjectionSeats.tooltip"));
        add(spnEjectionSeats, gbc);
        spnEjectionSeats.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnEjectionSeatsPod, spinnerSize);
        spnEjectionSeatsPod.setToolTipText(resourceMap.getString("SVCrewView.spnEjectionSeats.tooltip"));
        add(spnEjectionSeatsPod, gbc);
        spnEjectionSeatsPod.addChangeListener(this);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        setFieldSize(txtEjectionWeight, spinnerSize);
        add(txtEjectionWeight, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblQuarters", "SVCrewView.lblQuarters.text", labelSizeLg), gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        lblPodQuarters.setText(resourceMap.getString("SVCrewView.lblPodQuarters.text"));
        add(lblPodQuarters, gbc);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel lbl = new JLabel(resourceMap.getString("SVCrewView.lblQuartersWeight.text"), SwingConstants.CENTER);
        add(lbl, gbc);
        gbc.gridx = 4;
        lbl = new JLabel(resourceMap.getString("SVCrewView.lblQuartersSlots.text"), SwingConstants.CENTER);
        add(lbl, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblFirstClass", "SVCrewView.spnFirstClass.text",
                "SVCrewView.spnFirstClass.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnFirstClass, spinnerSize);
        spnFirstClass.setToolTipText(resourceMap.getString("SVCrewView.spnFirstClass.tooltip"));
        add(spnFirstClass, gbc);
        spnFirstClass.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnFirstClassPod, spinnerSize);
        spnFirstClassPod.setToolTipText(resourceMap.getString("SVCrewView.spnFirstClass.tooltip"));
        add(spnFirstClassPod, gbc);
        spnFirstClassPod.addChangeListener(this);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        setFieldSize(txtFirstClassWeight, spinnerSize);
        add(txtFirstClassWeight, gbc);
        gbc.gridx = 4;
        setFieldSize(txtFirstClassSlots, spinnerSize);
        add(txtFirstClassSlots, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblSecondClass", "SVCrewView.spnSecondClass.text",
                "SVCrewView.spnSecondClass.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnSecondClass, spinnerSize);
        spnSecondClass.setToolTipText(resourceMap.getString("SVCrewView.spnSecondClass.tooltip"));
        add(spnSecondClass, gbc);
        spnSecondClass.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnSecondClassPod, spinnerSize);
        spnSecondClassPod.setToolTipText(resourceMap.getString("SVCrewView.spnSecondClass.tooltip"));
        add(spnSecondClassPod, gbc);
        spnSecondClassPod.addChangeListener(this);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        setFieldSize(txtSecondClassWeight, spinnerSize);
        add(txtSecondClassWeight, gbc);
        gbc.gridx = 4;
        setFieldSize(txtSecondClassSlots, spinnerSize);
        add(txtSecondClassSlots, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblCrewQuarters", "SVCrewView.spnCrewQuarters.text",
                "SVCrewView.spnCrewQuarters.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnCrewQuarters, spinnerSize);
        spnCrewQuarters.setToolTipText(resourceMap.getString("SVCrewView.spnCrewQuarters.tooltip"));
        add(spnCrewQuarters, gbc);
        spnCrewQuarters.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnCrewQuartersPod, spinnerSize);
        spnCrewQuartersPod.setToolTipText(resourceMap.getString("SVCrewView.spnCrewQuarters.tooltip"));
        add(spnCrewQuartersPod, gbc);
        spnCrewQuartersPod.addChangeListener(this);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        setFieldSize(txtCrewQuartersWeight, spinnerSize);
        add(txtCrewQuartersWeight, gbc);
        gbc.gridx = 4;
        setFieldSize(txtCrewQuartersSlots, spinnerSize);
        add(txtCrewQuartersSlots, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(createLabel(resourceMap, "lblSteerage", "SVCrewView.spnSteerage.text",
                "SVCrewView.spnSteerage.tooltip", labelSizeLg), gbc);
        gbc.gridx = 1;
        setFieldSize(spnSteerage, spinnerSize);
        spnSteerage.setToolTipText(resourceMap.getString("SVCrewView.spnSteerage.tooltip"));
        add(spnSteerage, gbc);
        spnSteerage.addChangeListener(this);
        gbc.gridx = 2;
        setFieldSize(spnSteeragePod, spinnerSize);
        spnSteeragePod.setToolTipText(resourceMap.getString("SVCrewView.spnSteerage.tooltip"));
        add(spnSteeragePod, gbc);
        spnSteeragePod.addChangeListener(this);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 3;
        setFieldSize(txtSteerageWeight, spinnerSize);
        add(txtSteerageWeight, gbc);
        gbc.gridx = 4;
        setFieldSize(txtSteerageSlots, spinnerSize);
        add(txtSteerageSlots, gbc);
    }

    public void setFromEntity(Entity entity) {
        final int total = Compute.getFullCrewSize(entity);
        final int base = Compute.getSVBaseCrewNeeds(entity);
        final int gunners = Compute.getSupportVehicleGunnerNeeds(entity);
        final int other = Compute.getAdditionalNonGunner(entity);
        txtReqBaseCrew.setText(String.valueOf(base));
        txtReqGunners.setText(String.valueOf(gunners));
        txtReqOther.setText(String.valueOf(other));
        txtReqOfficers.setText(String.valueOf(total - base - gunners - other));
        txtTotalMinCrew.setText(String.valueOf(total));

        int stdSeats = 0;
        int pillion = 0;
        int ejection = 0;
        int podStdSeats = 0;
        int podPillion = 0;
        int podEjection = 0;
        int firstClass = 0;
        int secondClass = 0;
        int crewQuarters = 0;
        int steerage = 0;
        int podFirstClass = 0;
        int podSecondClass = 0;
        int podCrewQuarters = 0;
        int podSteerage = 0;
        double firstClassWeight = 0.0;
        double secondClassWeight = 0.0;
        double crewQuartersWeight = 0.0;
        double steerageWeight = 0.0;
        for (Transporter t : entity.getTransports()) {
            if (t instanceof PillionSeatCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podPillion += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    pillion += (int) Math.round(((Bay) t).getCapacity());
                }
            } else if (t instanceof EjectionSeatCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podEjection += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    ejection += (int) Math.round(((Bay) t).getCapacity());
                }
            } else if (t instanceof StandardSeatCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podStdSeats += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    stdSeats += (int) Math.round(((Bay) t).getCapacity());
                }
            } else if (t instanceof FirstClassQuartersCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podFirstClass += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    firstClass += (int) Math.round(((Bay) t).getCapacity());
                }
                firstClassWeight += ((Bay) t).getWeight();
            } else if (t instanceof SecondClassQuartersCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podSecondClass += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    secondClass += (int) Math.round(((Bay) t).getCapacity());
                }
                secondClassWeight += ((Bay) t).getWeight();
            } else if (t instanceof CrewQuartersCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podCrewQuarters += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    crewQuarters += (int) Math.round(((Bay) t).getCapacity());
                }
                crewQuartersWeight += ((Bay) t).getWeight();
            } else if (t instanceof SteerageQuartersCargoBay) {
                if (entity.isPodMountedTransport(t)) {
                    podSteerage += (int) Math.round(((Bay) t).getCapacity());
                } else {
                    steerage += (int) Math.round(((Bay) t).getCapacity());
                }
                steerageWeight += ((Bay) t).getWeight();
            }
        }

        if ((entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)
                || entity.getMovementMode().isVTOL()
                || entity.isAero()) {
            spnEjectionSeats.setEnabled(true);
            spnEjectionSeatsPod.setEnabled(true);
        } else {
            ejection = 0;
            spnEjectionSeats.setEnabled(false);
            spnEjectionSeatsPod.setEnabled(false);
        }

        if (entity.isOmni()) {
            lblPodSeating.setVisible(true);
            spnStandardSeatsPod.setVisible(true);
            spnPillionSeatsPod.setVisible(true);
            spnEjectionSeatsPod.setVisible(true);
            lblPodQuarters.setVisible(true);
            spnFirstClassPod.setVisible(true);
            spnSecondClassPod.setVisible(true);
            spnCrewQuartersPod.setVisible(true);
            spnSteeragePod.setVisible(true);
        } else {
            podStdSeats = 0;
            podPillion = 0;
            podEjection = 0;
            podFirstClass = 0;
            podSecondClass = 0;
            podCrewQuarters = 0;
            podSteerage = 0;
            lblPodSeating.setVisible(false);
            spnStandardSeatsPod.setVisible(false);
            spnPillionSeatsPod.setVisible(false);
            spnEjectionSeatsPod.setVisible(false);
            lblPodQuarters.setVisible(false);
            spnFirstClassPod.setVisible(false);
            spnSecondClassPod.setVisible(false);
            spnCrewQuartersPod.setVisible(false);
            spnSteeragePod.setVisible(false);
        }
        spnStandardSeats.removeChangeListener(this);
        spnPillionSeats.removeChangeListener(this);
        spnEjectionSeats.removeChangeListener(this);
        spnFirstClass.removeChangeListener(this);
        spnSecondClass.removeChangeListener(this);
        spnCrewQuarters.removeChangeListener(this);
        spnSteerage.removeChangeListener(this);
        spnStandardSeats.setValue(stdSeats);
        spnStandardSeatsPod.setValue(podStdSeats);
        spnPillionSeats.setValue(pillion);
        spnPillionSeatsPod.setValue(podPillion);
        spnEjectionSeats.setValue(ejection);
        spnEjectionSeatsPod.setValue(podEjection);
        spnFirstClass.setValue(firstClass);
        spnFirstClassPod.setValue(podFirstClass);
        spnSecondClass.setValue(secondClass);
        spnSecondClassPod.setValue(podSecondClass);
        spnCrewQuarters.setValue(crewQuarters);
        spnCrewQuartersPod.setValue(podCrewQuarters);
        spnSteerage.setValue(steerage);
        spnSteeragePod.setValue(podSteerage);
        spnStandardSeats.addChangeListener(this);
        spnPillionSeats.addChangeListener(this);
        spnEjectionSeats.addChangeListener(this);
        spnFirstClass.addChangeListener(this);
        spnSecondClass.addChangeListener(this);
        spnCrewQuarters.addChangeListener(this);
        spnSteerage.addChangeListener(this);
        // If the current seating is not enough to meet minimum crew requirements, add standard combat seats
        if ((entity.getWeightClass() == EntityWeightClass.WEIGHT_SMALL_SUPPORT)
                && (stdSeats + pillion + ejection < total)) {
            stdSeats  = total - pillion - ejection;
            spnStandardSeats.setValue(stdSeats);
            lblSeatingHeader.setText(resourceMap.getString("SVCrewView.lblSeatingHeader.allSeats"));
        } else {
            lblSeatingHeader.setText(resourceMap.getString("SVCrewView.lblSeatingHeader.extraSeats"));
        }

        txtStdSeatWeight.setText(String.valueOf((stdSeats + podStdSeats) * 75));
        txtPillionWeight.setText(String.valueOf((pillion + podPillion) * 25));
        txtEjectionWeight.setText(String.valueOf((ejection + podEjection) * 100));
        txtFirstClassWeight.setText(String.valueOf(firstClassWeight));
        txtSecondClassWeight.setText(String.valueOf(secondClassWeight));
        txtCrewQuartersWeight.setText(String.valueOf(crewQuartersWeight));
        txtSteerageWeight.setText(String.valueOf(steerageWeight));
        int[] extraQuarters = TestSupportVehicle.extraCrewQuartersCount(entity);
        txtFirstClassSlots.setText(String.valueOf((int) Math.ceil(extraQuarters[TestSupportVehicle.INDEX_FIRST_CLASS] / 5.0)));
        txtSecondClassSlots.setText(String.valueOf((int) Math.ceil(extraQuarters[TestSupportVehicle.INDEX_SECOND_CLASS] / 20.0)));
        txtCrewQuartersSlots.setText(String.valueOf((int) Math.ceil(extraQuarters[TestSupportVehicle.INDEX_STD_CREW] / 20.0)));
        txtSteerageSlots.setText(String.valueOf((int) Math.ceil(extraQuarters[TestSupportVehicle.INDEX_STEERAGE] / 50.0)));
    }

    /**
     * Convenience method to get the value of a spinner for integers which returns 0 if the value
     * is null.
     *
     * @param spinner A spinner for integer values
     * @return        The spinner's value, or 0 if the value is null.
     * @throws ClassCastException If the spinner's value is not an {@link Integer}
     */
    private int getSpinnerValue(JSpinner spinner) {
        Integer val = (Integer) spinner.getValue();
        return Objects.requireNonNullElse(val, 0);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource() == spnStandardSeats)
                || (e.getSource() == spnStandardSeatsPod)
                || (e.getSource() == spnPillionSeats)
                || (e.getSource() == spnPillionSeatsPod)
                || (e.getSource() == spnEjectionSeats)
                || (e.getSource() == spnEjectionSeatsPod)) {
            listeners.forEach(l -> l.setSeating(getSpinnerValue(spnStandardSeats),
                    getSpinnerValue(spnStandardSeatsPod), getSpinnerValue(spnPillionSeats),
                    getSpinnerValue(spnPillionSeatsPod), getSpinnerValue(spnEjectionSeats),
                    getSpinnerValue(spnEjectionSeatsPod)));
        } else if ((e.getSource() == spnFirstClass)
                || (e.getSource() == spnFirstClassPod)
                || (e.getSource() == spnSecondClass)
                || (e.getSource() == spnSecondClassPod)
                || (e.getSource() == spnCrewQuarters)
                || (e.getSource() == spnCrewQuartersPod)
                || (e.getSource() == spnSteerage)
                || (e.getSource() == spnSteeragePod)) {
            listeners.forEach(l -> l.setQuarters(getSpinnerValue(spnFirstClass),
                    getSpinnerValue(spnFirstClassPod), getSpinnerValue(spnSecondClass),
                    getSpinnerValue(spnSecondClassPod), getSpinnerValue(spnCrewQuarters),
                    getSpinnerValue(spnCrewQuartersPod), getSpinnerValue(spnSteerage),
                    getSpinnerValue(spnSteeragePod)));
        }
    }
}
