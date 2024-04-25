/*
 * Copyright (c) 2023 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.client.ui.GBC;
import megamek.client.ui.swing.DialogOptionComponent;
import megamek.client.ui.swing.DialogOptionListener;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.options.IOption;
import megamek.common.options.IOptionGroup;
import megamek.common.options.Quirks;
import megamek.common.options.WeaponQuirks;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

public class QuirksTab extends ITab implements DialogOptionListener {

    private final HashMap<Integer, ArrayList<DialogOptionComponent>> wpnQuirkComps = new HashMap<>();
    private final HashMap<Integer, WeaponQuirks> wpnQuirks = new HashMap<>();

    public QuirksTab(EntitySource eSource) {
        super(eSource);
        setLayout(new GridBagLayout());
        refreshQuirks();
    }

    public void refreshQuirks() {
        wpnQuirks.clear();
        for (Mounted m : getEntity().getWeaponList()) {
            wpnQuirks.put(getEntity().getEquipmentNum(m), m.getQuirks());
        }
        // Also need to consider melee weapons
        for (Mounted m : getEntity().getMisc()) {
            if (m.getType().hasFlag(MiscType.F_CLUB)) {
                wpnQuirks.put(getEntity().getEquipmentNum(m), m.getQuirks());
            }
        }
        removeAll();
        for (Integer eqNum : wpnQuirks.keySet()) {
            wpnQuirkComps.put(eqNum, new ArrayList<>());
        }

        for (Enumeration<IOptionGroup> i = getEntity().getQuirks().getGroups(); i.hasMoreElements(); ) {
            IOptionGroup group = i.nextElement();
            add(new JLabel(group.getDisplayableName()), GBC.eol());

            for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements(); ) {
                IOption option = j.nextElement();

                if (null == option || !Quirks.isQuirkLegalFor(option, getEntity())) {
                    continue;
                }

                addQuirk(option);
            }
        }

        // now for weapon quirks
        for (int key : wpnQuirks.keySet()) {
            Mounted m = getEntity().getEquipment(key);
            WeaponQuirks wpnQuirks = m.getQuirks();
            JLabel labWpn = new JLabel(m.getName() + " ("
                    + getEntity().getLocationName(m.getLocation()) + ")");
            add(labWpn, GBC.eol());
            for (Enumeration<IOptionGroup> i = wpnQuirks.getGroups(); i.hasMoreElements(); ) {
                IOptionGroup group = i.nextElement();
                for (Enumeration<IOption> j = group.getSortedOptions(); j.hasMoreElements(); ) {
                    IOption option = j.nextElement();
                    if (!WeaponQuirks.isQuirkLegalFor(option, getEntity(), m.getType())) {
                        continue;
                    }
                    addWeaponQuirk(key, option);
                }
            }
        }
        validate();
    }

    private void addQuirk(IOption option) {
        DialogOptionComponent optionComp = new DialogOptionComponent(this, option, true);
        add(optionComp, GBC.eol());
    }

    private void addWeaponQuirk(int key, IOption option) {
        DialogOptionComponent optionComp = new DialogOptionComponent(this, option, true);
        add(optionComp, GBC.eol());
        wpnQuirkComps.get(key).add(optionComp);
    }

    @Override
    public void optionClicked(DialogOptionComponent comp, IOption option, boolean state) {
        option.setValue(state);
    }

    @Override
    public void optionSwitched(DialogOptionComponent comp, IOption option, int i) {

    }

    public ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent e) {
            refreshQuirks();
        }
    };
}