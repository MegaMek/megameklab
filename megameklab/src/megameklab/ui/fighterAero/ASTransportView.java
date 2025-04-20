/*
 * Copyright (C) 2018-2025 The MegaMek Team. All Rights Reserved.
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
 */
package megameklab.ui.fighterAero;

import java.awt.Component;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.event.ChangeEvent;

import megamek.common.verifier.BayData;
import megameklab.ui.generalUnit.TransportView;
import megameklab.ui.listeners.AeroBuildListener;
import megameklab.ui.listeners.BuildListener;

/**
 * Panel for aero cargo and troop space.
 */
public class ASTransportView extends TransportView {
    List<AeroBuildListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addListener(BuildListener l) {
        if (l instanceof AeroBuildListener listener) {
            listeners.add(listener);
        }
    }

    @Override
    public void removeListener(BuildListener l) {
        if (l instanceof AeroBuildListener listener) {
            listeners.remove(listener);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if ((e.getSource() == spnFixedTroop) || (e.getSource() == spnPodTroop)) {
            listeners.forEach(l -> l.troopSpaceChanged(spnFixedTroopModel.getNumber().doubleValue(),
                  spnPodTroopModel.getNumber().doubleValue()));
        } else if (e.getSource() instanceof Component) {
            BayData bayType = null;
            for (BayData bay : BayData.values()) {
                if (bay.toString().equals(((Component) e.getSource()).getName())) {
                    bayType = bay;
                    break;
                }
            }
            if (null != bayType) {
                for (AeroBuildListener l : listeners) {
                    l.cargoSpaceChanged(bayType,
                          fixedSpinnerModels.get(bayType).getNumber().doubleValue(),
                          podSpinnerModels.get(bayType).getNumber().doubleValue());
                }
            }
        }
    }
}
