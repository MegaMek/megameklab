/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import megamek.common.annotations.Nullable;

/**
 * Panel used to set armor value for a single location. Optionally used for rear location as well, and can be used to
 * set the armor type for units with patchwork armor.
 *
 * @author Neoancient
 */
public class ArmorLocationView extends BuildView implements ChangeListener {
    public interface ArmorLocationListener {
        void armorPointsChanged(int location, int front, int rear);
    }

    private final List<ArmorLocationListener> listeners = new CopyOnWriteArrayList<>();

    public void addListener(ArmorLocationListener l) {
        listeners.add(l);
    }

    public void removeListener(ArmorLocationListener l) {
        listeners.remove(l);
    }

    private final SpinnerNumberModel spnPointsModel = new SpinnerNumberModel(0, 0, null, 1);
    private final SpinnerNumberModel spnPointsRearModel = new SpinnerNumberModel(0, 0, null, 1);
    private final JSpinner spnPoints = new JSpinner(spnPointsModel);
    private final JSpinner spnPointsRear = new JSpinner(spnPointsRearModel);
    private final JLabel lblRear = new JLabel();
    private final JLabel lblMaxPoints = new JLabel();

    private final int location;
    private final String maxFormat;
    private Integer maxPoints;
    private boolean hasRear = false;

    ArmorLocationView(int location) {
        this.location = location;

        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Views");
        lblRear.setText(resourceMap.getString("ArmorLocationView.lblRear.text"));
        maxFormat = resourceMap.getString("ArmorLocationView.lblMax.format");
        setBorder(BorderFactory.createTitledBorder(
              null, "",
              TitledBorder.TOP,
              TitledBorder.DEFAULT_POSITION));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(spnPoints, gbc);
        gbc.gridy++;
        add(lblRear, gbc);
        gbc.gridy++;
        add(spnPointsRear, gbc);
        gbc.gridy++;
        gbc.weighty = 1.0;
        add(lblMaxPoints, gbc);
    }

    /**
     * Changes the location name in the title and whether it has a rear armor location.
     *
     */
    public void updateLocation(String locName, boolean rear) {
        ((TitledBorder) getBorder()).setTitle(locName);
        hasRear = rear;
        lblRear.setVisible(rear);
        spnPointsRear.setVisible(rear);
        if (!rear) {
            spnPointsRear.setValue(0);
        }
    }

    /**
     * @return The index (LOC_* constant) of the location managed by this view.
     */
    public int getLocationIndex() {
        return location;
    }

    /**
     * Sets the maximum number of armor points that can be assigned to this location. A value of null indicates that
     * there is no maximum.
     *
     */
    public void setMaxPoints(@Nullable Integer max) {
        maxPoints = max;
        spnPointsModel.setMaximum(max);
        spnPointsRearModel.setMaximum(max);
        if (null == max) {
            lblMaxPoints.setVisible(false);
        } else {
            lblMaxPoints.setVisible(true);
            lblMaxPoints.setText(String.format(maxFormat, max));
        }
    }

    public void setMinimum(int minimum) {
        spnPointsModel.setMinimum(minimum);
        if (getPoints() < minimum) {
            spnPointsModel.setValue(minimum);
        }
    }

    /**
     * Sets the number of points for this location. If the location has rear armor, this sets only the front.
     *
     */
    public void setPoints(int points) {
        spnPoints.removeChangeListener(this);
        if (null == maxPoints) {
            spnPoints.setValue(points);
        } else {
            spnPoints.setValue(Math.min(points, maxPoints));
            if (hasRear && (getPoints() + getPointsRear() > maxPoints)) {
                spnPointsRearModel.setValue(maxPoints - getPoints());
            }
        }
        spnPoints.addChangeListener(this);
    }

    /**
     * @return The number of points of armor for this location (front).
     */
    public int getPoints() {
        return spnPointsModel.getNumber().intValue();
    }

    /**
     * Sets the number of points of armor for this location in the rear.
     *
     */
    public void setPointsRear(int points) {
        spnPointsRear.removeChangeListener(this);
        if (null == maxPoints) {
            spnPointsRear.setValue(points);
        } else {
            spnPointsRear.setValue(Math.min(points, maxPoints));
            if (getPoints() + getPointsRear() > maxPoints) {
                spnPointsModel.setValue(maxPoints - getPointsRear());
            }
        }
        spnPointsRear.addChangeListener(this);
    }

    /**
     * @return The number of points of rear armor in this location.
     */
    public int getPointsRear() {
        return spnPointsRearModel.getNumber().intValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        listeners.forEach(l -> l.armorPointsChanged(location, getPoints(), getPointsRear()));
    }

}
