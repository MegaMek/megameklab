/*
 * Copyright (c) 2008 - jtighe (torren@users.sourceforge.net)
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import megamek.client.ui.panes.ConfigurableMekViewPanel;
import megamek.client.ui.swing.MekViewPanel;
import megamek.client.ui.swing.alphaStrike.ConfigurableASCardPanel;
import megamek.client.ui.swing.calculationReport.FlexibleCalculationReport;
import megamek.common.Entity;
import megamek.common.ViewFormatting;
import megamek.common.alphaStrike.conversion.ASConverter;
import megamek.common.templates.TROView;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.util.ITab;

public class PreviewTab extends ITab {
    private static final MMLogger logger = MMLogger.create(PreviewTab.class);

    private final ConfigurableMekViewPanel panelMekView = new ConfigurableMekViewPanel();
    private final MekViewPanel panelTROView = new MekViewPanel();
    private final ConfigurableASCardPanel cardPanel = new ConfigurableASCardPanel(null);
    private final RecordSheetPreviewPanel rsPanel = new RecordSheetPreviewPanel();
    private JTabbedPane panPreview;

    public PreviewTab(EntitySource eSource) {
        super(eSource);
        setLayout(new BorderLayout());
        panelMekView.setMinimumSize(new Dimension(400, panelMekView.getMinimumSize().height));
        panelTROView.setMinimumSize(new Dimension(400, panelTROView.getMinimumSize().height));
        
        // Create scroll panes for each panel
        JScrollPane mekViewScroll = new JScrollPane(panelMekView);
        mekViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mekViewScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mekViewScroll.setBorder(null); // Remove border if desired
        
        JScrollPane troViewScroll = new JScrollPane(panelTROView);
        troViewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        troViewScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        troViewScroll.setBorder(null);

        panPreview = new JTabbedPane();
        panPreview.addTab("Summary", mekViewScroll);
        panPreview.addTab("TRO", troViewScroll);
        panPreview.addTab("AS Card", cardPanel);
        panPreview.addTab("Record Sheet", rsPanel);
        add(panPreview, BorderLayout.CENTER);

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

        addComponentListener(refreshOnShow);
    }

    /**
     * Sets the preferred width of this component and its child components
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
            logger.error("", e);
            // error unit didn't load right. this is bad news.
            populateTextFields = false;
        }
        if (populateTextFields) {
            panelMekView.setEntity(selectedUnit);
            panelTROView.setMek(selectedUnit, troView);
            if (ASConverter.canConvert(selectedUnit)) {
                cardPanel.setASElement(ASConverter.convertInMML(selectedUnit, new FlexibleCalculationReport()));
            } else {
                cardPanel.setASElement(null);
            }
            rsPanel.setEntity(selectedUnit);
        } else {
            panelMekView.reset();
            panelTROView.reset();
            cardPanel.setASElement(null);
            rsPanel.setEntity(null);
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

    ComponentListener refreshOnShow = new ComponentAdapter() {
        @Override
        public void componentShown(ComponentEvent e) {
            update();
        }
    };
}
