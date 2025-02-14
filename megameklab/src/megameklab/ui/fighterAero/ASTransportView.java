/*
 * Copyright (c) 2018-2025 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.fighterAero;

import megamek.common.verifier.BayData;
import megameklab.ui.generalUnit.TransportView;
import megameklab.ui.listeners.AeroBuildListener;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.listeners.CVBuildListener;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Panel for combat vehicle cargo and troop space.
 *
 * @author Neoancient
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
        if ((e.getSource() == spnFixedTroop)
            || (e.getSource() == spnPodTroop)) {
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
