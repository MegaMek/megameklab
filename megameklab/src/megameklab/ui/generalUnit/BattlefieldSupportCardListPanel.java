/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import megamek.MMConstants;
import megamek.client.ui.WrapLayout;
import megamek.client.ui.panels.battlefieldSupport.BFSCardPanel;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.cardDrawer.BattlefieldSupportCard;
import megameklab.printing.RecordSheetOptions;
import megameklab.util.CConfig;

/**
 * A scrollable preview showing every selected unit's Battlefield Support Asset card at once, used by the unit selector
 * in multi-select (Force Builder / Print Queue) mode — the BFS equivalent of the multi-record-sheet preview. Cards are
 * laid out in a wrapping grid and styled with the user's configured record-sheet font and color mode (matching the
 * editor's card preview and the print output).
 */
public class BattlefieldSupportCardListPanel extends JPanel {

    private static final ResourceBundle I18N = ResourceBundle.getBundle("megameklab.resources.Views");
    /** Preview scale for each card (cards are natively 1050x750). */
    private static final float CARD_SCALE = 0.5f;

    private final JPanel cardContainer = new JPanel(new WrapLayout(FlowLayout.LEFT, 10, 10));
    private final JLabel emptyLabel = new JLabel(I18N.getString("BattlefieldSupportCardListPanel.empty.text"),
          SwingConstants.CENTER);

    public BattlefieldSupportCardListPanel() {
        setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(cardContainer);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
        setAssets(List.of());
    }

    /**
     * Replaces the displayed cards with one per given asset. Passing an empty list shows a placeholder message.
     *
     * @param assets the assets to display (may be empty)
     */
    public void setAssets(List<BattlefieldSupportAsset> assets) {
        cardContainer.removeAll();
        if (assets.isEmpty()) {
            cardContainer.add(emptyLabel);
        } else {
            Font font = resolveCardFont();
            BattlefieldSupportCard.ColorMode colorMode = resolveColorMode();
            Color damageColor = new RecordSheetOptions().getDamageColorAwt();
            for (BattlefieldSupportAsset asset : assets) {
                BFSCardPanel cardPanel = new BFSCardPanel();
                cardPanel.setColorMode(colorMode);
                cardPanel.setDamageColor(damageColor);
                cardPanel.setCardFont(font);
                cardPanel.setScale(CARD_SCALE);
                cardPanel.setAsset(asset);
                cardContainer.add(cardPanel);
            }
        }
        cardContainer.revalidate();
        cardContainer.repaint();
    }

    /** @return the user's configured record-sheet font, or a default sans-serif font when none is configured. */
    private static Font resolveCardFont() {
        String family = CConfig.getParam(CConfig.RS_FONT);
        String resolved = ((family == null) || family.isBlank()) ? MMConstants.FONT_SANS_SERIF : family;
        return new Font(resolved, Font.PLAIN, 14);
    }

    /** @return the BFS card color mode corresponding to the current record-sheet color setting. */
    private static BattlefieldSupportCard.ColorMode resolveColorMode() {
        return switch (new RecordSheetOptions().useColor()) {
            case ALL -> BattlefieldSupportCard.ColorMode.ALL;
            case LOGO_ONLY -> BattlefieldSupportCard.ColorMode.LOGO_ONLY;
            case NONE -> BattlefieldSupportCard.ColorMode.NONE;
        };
    }
}
