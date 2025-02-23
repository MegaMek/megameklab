package megameklab.ui.handheldWeapon;

import megamek.common.Entity;
import megamek.common.HandheldWeapon;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.listeners.HHWBuildListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HHWChassisView extends BuildView implements ChangeListener {
    List<HHWBuildListener> listeners = new CopyOnWriteArrayList<>();
    public void addListener(HHWBuildListener l) {
        listeners.add(l);
    }
    public void removeListener(HHWBuildListener l) {
        listeners.remove(l);
    }

    private final SpinnerNumberModel tonnageModel = new SpinnerNumberModel(1, 0.5, 100, 0.5);
    private final JSpinner spnTonnage = new JSpinner(tonnageModel);

    private final SpinnerNumberModel armorModel = new SpinnerNumberModel(0, 0, 1600, 1);
    private final JSpinner spnArmor = new JSpinner(armorModel);
    private final SpinnerNumberModel armorWeightModel = new SpinnerNumberModel(0, 0, 100, 0.5);
    private final JSpinner spnArmorWeight = new JSpinner(armorWeightModel);

    public HHWChassisView() {
        // todo Use Resource Bundle
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Weight"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(spnTonnage, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Armor (Tons)"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(spnArmor, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(spnArmorWeight, gbc);

        spnTonnage.addChangeListener(this);
        spnArmor.addChangeListener(this);
        spnArmorWeight.addChangeListener(this);
    }

    public void setFromEntity(Entity hhw) {
        spnTonnage.setValue(hhw.getWeight());
        spnArmor.setValue(hhw.getOArmor(HandheldWeapon.LOC_GUN));
        spnArmorWeight.setValue(hhw.getArmorWeight());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == spnTonnage) {
            listeners.forEach(l -> l.weightChanged(tonnageModel.getNumber().doubleValue()));
        } else if (e.getSource() == spnArmor) {
            listeners.forEach(l -> l.armorChanged(armorModel.getNumber().intValue()));
        } else if (e.getSource() == spnArmorWeight) {
            listeners.forEach(l -> l.armorChanged((int) (armorWeightModel.getNumber().doubleValue() * 16)));
        }
    }
}
