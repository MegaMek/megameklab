/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.infantry;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.StringJoiner;
import javax.swing.JComponent;
import javax.swing.JTextPane;

import com.formdev.flatlaf.ui.FlatTextBorder;
import megamek.client.ui.util.DisplayTextField;
import megamek.common.SimpleTechLevel;
import megamek.common.enums.ProstheticEnhancementType;
import megamek.common.equipment.EquipmentType;
import megamek.common.options.IOption;
import megamek.common.options.OptionsConstants;
import megamek.common.options.PilotOptions;
import megamek.common.units.Infantry;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BuildView;
import megameklab.ui.generalUnit.StandardBuildLabel;
import megameklab.ui.util.IView;
import megameklab.ui.util.WidthControlComponent;

public class CIAdvancedView extends IView {

    private static final String LIST_ITEM_SEPARATOR = ";";

    private final DisplayTextField txtArmor = new DisplayTextField("None", WidthControlComponent.TEXT_FIELD_COLUMNS);
    private final JTextPane txtSpecializations = new OptionsListTextPane(txtArmor);
    private final JTextPane txtAugmentations = new OptionsListTextPane(txtArmor);

    public CIAdvancedView(EntitySource eSource, CIStructureTab structureTab) {
        super(eSource);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = BuildView.STANDARD_INSETS;
        gbc.gridy = 0;
        add(new StandardBuildLabel("Armor:"), gbc);
        add(txtArmor, gbc);
        txtArmor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showArmorChoiceTable();
            }
        });

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.NORTH;
        add(new StandardBuildLabel("Specializations:"), gbc);
        add(txtSpecializations, gbc);
        txtSpecializations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showSpecializationChoiceTable();
            }
        });

        gbc.gridy++;
        add(new StandardBuildLabel("Augmentations:"), gbc);
        add(txtAugmentations, gbc);
        txtAugmentations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                structureTab.showAugmentationChoiceTable();
            }
        });
    }

    public void setFromEntity() {
        EquipmentType armor = getInfantry().getArmorKit();
        if (null != armor) {
            txtArmor.setText(armor.getName());
        } else {
            String desc = getInfantry().getArmorDesc();
            txtArmor.setText(desc.equals("1.0") ? "None" : desc);
        }
        updateSpecializations();
        updateAugments();
    }

    void updateSpecializations() {
        if (getInfantry().getSpecializations() == 0) {
            txtSpecializations.setText("None");
        } else {
            StringJoiner sj = new StringJoiner(LIST_ITEM_SEPARATOR + "<br>");
            for (int i = 0; i < Infantry.NUM_SPECIALIZATIONS; i++) {
                if (getInfantry().hasSpecialization(1 << i)) {
                    sj.add(Infantry.getSpecializationName(1 << i));
                }
            }
            txtSpecializations.setText(sj.toString());
        }
    }

    private void updateAugments() {
        StringJoiner sj = new StringJoiner(LIST_ITEM_SEPARATOR + "<br>");
        Infantry infantry = getInfantry();
        for (Enumeration<IOption> e = infantry.getCrew().getOptions(PilotOptions.MD_ADVANTAGES);
              e.hasMoreElements(); ) {
            IOption opt = e.nextElement();
            if (infantry.getCrew().getOptions().booleanOption(opt.getName())) {
                String displayText = sanitizeAugmentationName(opt.getDisplayableName());

                // Append prosthetic enhancement details for Enhanced/Improved Enhanced options
                if (OptionsConstants.MD_PL_ENHANCED.equals(opt.getName())
                      || OptionsConstants.MD_PL_I_ENHANCED.equals(opt.getName())) {
                    String details = getRegularProstheticDetails(infantry);
                    if (!details.isEmpty()) {
                        displayText += " (" + details + ")";
                    }
                }

                // Append extraneous limb details for Extraneous Limbs option
                if (OptionsConstants.MD_PL_EXTRA_LIMBS.equals(opt.getName())) {
                    String details = getExtraneousLimbDetails(infantry);
                    if (!details.isEmpty()) {
                        displayText += " (" + details + ")";
                    }
                }

                sj.add(displayText);
            }
        }
        txtAugmentations.setText((sj.length() > 0) ? sj.toString() : "None");
    }

    /**
     * Gets regular prosthetic enhancement details (slot 1 and 2 only).
     */
    private String getRegularProstheticDetails(Infantry infantry) {
        StringBuilder details = new StringBuilder();
        if (infantry.hasProstheticEnhancement1()) {
            ProstheticEnhancementType type1 = infantry.getProstheticEnhancement1();
            details.append(type1.getDisplayName()).append(" x").append(infantry.getProstheticEnhancement1Count());
        }
        if (infantry.hasProstheticEnhancement2()) {
            if (details.length() > 0) {
                details.append(", ");
            }
            ProstheticEnhancementType type2 = infantry.getProstheticEnhancement2();
            details.append(type2.getDisplayName()).append(" x").append(infantry.getProstheticEnhancement2Count());
        }
        return details.toString();
    }

    /**
     * Gets extraneous limb details (pair 1 and 2 only).
     */
    private String getExtraneousLimbDetails(Infantry infantry) {
        StringBuilder details = new StringBuilder();
        if (infantry.hasExtraneousPair1()) {
            ProstheticEnhancementType pair1Type = infantry.getExtraneousPair1();
            details.append(pair1Type.getDisplayName()).append(" x2");
        }
        if (infantry.hasExtraneousPair2()) {
            if (details.length() > 0) {
                details.append(", ");
            }
            ProstheticEnhancementType pair2Type = infantry.getExtraneousPair2();
            details.append(pair2Type.getDisplayName()).append(" x2");
        }
        return details.toString();
    }

    private String sanitizeAugmentationName(String originalName) {
        return originalName.replace(" (Not Implemented)", "");
    }

    void enableTabs(SimpleTechLevel level) {
        if (level.ordinal() >= SimpleTechLevel.ADVANCED.ordinal()) {
            txtArmor.setEnabled(true);
            txtSpecializations.setEnabled(true);
            // Experimental level
            txtAugmentations.setEnabled(level.ordinal() >= SimpleTechLevel.EXPERIMENTAL.ordinal());
        } else {
            txtArmor.setEnabled(false);
            txtSpecializations.setEnabled(false);
            txtAugmentations.setEnabled(false);
        }
    }

    /**
     * A very specialized text pane to display a list of specializations/augmentations. It is non-editable and
     * width-controlled, set to html and given a border.
     */
    static class OptionsListTextPane extends JTextPane {

        private final JComponent widthPrototype;

        /**
         * @param widthPrototype A JComponent; the width of this is used as preferred width of the text pane
         */
        OptionsListTextPane(JComponent widthPrototype) {
            this.widthPrototype = widthPrototype;
            setEditable(false);
            setContentType("text/html");
            setFocusable(false);
            setBorder(new FlatTextBorder());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(widthPrototype.getPreferredSize().width, super.getPreferredSize().height);
        }
    }
}
