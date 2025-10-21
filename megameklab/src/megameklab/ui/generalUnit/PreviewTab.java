/*
 * Copyright (c) 2008 - jtighe (torren@users.sourceforge.net)
 * Copyright (C) 2013-2025 The MegaMek Team. All Rights Reserved.
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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import javax.swing.JScrollPane;

import megamek.client.ui.clientGUI.GUIPreferences;
import megamek.client.ui.clientGUI.calculationReport.FlexibleCalculationReport;
import megamek.client.ui.dialogs.unitSelectorDialogs.AvailabilityPanel;
import megamek.client.ui.dialogs.unitSelectorDialogs.ConfigurableMekViewPanel;
import megamek.client.ui.dialogs.unitSelectorDialogs.EntityReadoutPanel;
import megamek.client.ui.panels.alphaStrike.ConfigurableASCardPanel;
import megamek.client.ui.util.ViewFormatting;
import megamek.common.alphaStrike.conversion.ASConverter;
import megamek.common.templates.TROView;
import megamek.common.ui.EnhancedTabbedPane;
import megamek.common.ui.EnhancedTabbedPane.TabStateListener;
import megamek.common.units.Entity;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;
import megameklab.util.CConfig;

public class PreviewTab extends ITab {
    private static final MMLogger LOGGER = MMLogger.create(PreviewTab.class);

    private final ConfigurableMekViewPanel panelMekView = new ConfigurableMekViewPanel();
    private final EntityReadoutPanel panelTROView = new EntityReadoutPanel();
    private final ConfigurableASCardPanel cardPanel = new ConfigurableASCardPanel(null);
    private final RecordSheetPreviewPanel rsPanel = new RecordSheetPreviewPanel();
    private final AvailabilityPanel factionPanel = new AvailabilityPanel();
    private final String tabIndexSettingName = "PreviewTab.panPreview.selectedIndex";
    private final EnhancedTabbedPane panPreview;

    public PreviewTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        panelMekView.setMinimumSize(new Dimension(400, panelMekView.getMinimumSize().height));
        panelTROView.setMinimumSize(new Dimension(400, panelTROView.getMinimumSize().height));
        rsPanel.setMinZoom(1.0f);
        rsPanel.setFullAsyncMode(false);

        // Create scroll panes for each panel
        JScrollPane mekViewScroll = new JScrollPane(panelMekView);
        mekViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mekViewScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mekViewScroll.setBorder(null); // Remove border if desired

        JScrollPane troViewScroll = new JScrollPane(panelTROView);
        troViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        troViewScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        troViewScroll.setBorder(null);

        panPreview = new EnhancedTabbedPane(true, true);
        panPreview.addTab("Summary", mekViewScroll);
        panPreview.addTab("TRO", troViewScroll);
        panPreview.addTab("Factions", factionPanel.getPanel());
        panPreview.addTab("AS Card", cardPanel);
        panPreview.addTab("Record Sheet", rsPanel);
        List<String> tabsOrder = GUIPreferences.getInstance().getTabOrder(this.getClass().getName());
        panPreview.setTabOrder(tabsOrder);
        add(panPreview, BorderLayout.CENTER);

        panPreview.addChangeListener(e -> {
            final int selectedIndex = panPreview.getSelectedIndex();
            CConfig.setParam(tabIndexSettingName, String.valueOf(selectedIndex));
        });
        final int savedTabIndex = CConfig.getIntParam(tabIndexSettingName, 0);
        if (savedTabIndex >= 0 && savedTabIndex < panPreview.getTabCount()) {
            panPreview.setSelectedIndex(savedTabIndex);
        }

        // Add a resize listener to detect container width changes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Get the actual available width
                int availableWidth = getWidth();
                if (availableWidth > 0) {
                    setPreferredWidth(availableWidth);
                }
            }
        });

        panPreview.addTabStateListener(new TabStateListener() {
            @Override
            public void onTabMoved(int oldIndex, int newIndex, Component component) {
                List<String> tabsOrder = panPreview.getTabOrder();
                GUIPreferences.getInstance().setTabOrder(PreviewTab.this.getClass().getName(), tabsOrder);
            }
        });

        addComponentListener(refreshOnShow);
    }

    /**
     * Sets the preferred width of this component and its child components
     *
     * @param width The desired width
     */
    public void setPreferredWidth(int width) {
        // Don't change the component's preferred size, just update panel widths
        int panelWidth = Math.max(400, width - 40);

        panelMekView.setPreferredSize(new Dimension(panelWidth, panelMekView.getPreferredSize().height));
        panelTROView.setPreferredSize(new Dimension(panelWidth, panelTROView.getPreferredSize().height));
        cardPanel.setPreferredSize(new Dimension(panelWidth, cardPanel.getPreferredSize().height));
        rsPanel.setPreferredSize(new Dimension(panelWidth, rsPanel.getPreferredSize().height));

        // Force a refresh to ensure content uses the new width
        revalidate();
        repaint();
    }

    public void update() {
        boolean populateTextFields = true;
        final Entity selectedUnit = eSource.getEntity();
        selectedUnit.recalculateTechAdvancement();
        TROView troView = null;
        try {
            troView = TROView.createView(selectedUnit, ViewFormatting.HTML);
        } catch (Exception e) {
            LOGGER.error("", e);
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields) {
            panelMekView.setEntity(selectedUnit);
            panelTROView.showEntity(selectedUnit, troView);
            if (ASConverter.canConvert(selectedUnit)) {
                cardPanel.setASElement(ASConverter.convertInMML(selectedUnit, new FlexibleCalculationReport()));
            } else {
                cardPanel.setASElement(null);
            }
            rsPanel.setEntity(selectedUnit);
            factionPanel.setUnit(selectedUnit.getModel(), selectedUnit.getFullChassis());
        } else {
            panelMekView.reset();
            panelTROView.reset();
            cardPanel.setASElement(null);
            rsPanel.setEntity(null);
            factionPanel.reset();
        }
    }

    public void refresh() {
        // This active refresh is needed for the few cases where the unit can be changed
        // when the preview is
        // active, e.g. setting the fluff image.
        if (isVisible()) {
            update();
        }
    }

    @Override
    public void removeNotify() {
        panPreview.reattachAllTabs();
        super.removeNotify();
    }

    ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent e) {
            update();
        }
    };
}
